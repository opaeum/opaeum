package org.opaeum.javageneration.bpm;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.MessageEvent;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.EmfEventUtil;
import org.opaeum.eclipse.EmfTimeUtil;
import org.opaeum.emf.workspace.DefaultOpaeumComparator;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.maps.IMessageMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.textmetamodel.TextWorkspace;

public abstract class AbstractJavaProcessVisitor extends AbstractBehaviorVisitor{
	protected OperationAnnotator operationAnnotator = new OperationAnnotator();
	@Override
	public void initialize(OJWorkspace pac,OpaeumConfig config,TextWorkspace textWorkspace,EmfWorkspace workspace,OJUtil ojUtil){
		super.initialize(pac, config, textWorkspace, workspace, ojUtil);
		operationAnnotator.initialize(pac, config, textWorkspace, workspace, ojUtil);
	}
	protected OJOperation implementExecute(OJAnnotatedClass ojClass,Classifier oc){
		OJOperation execute = new OJAnnotatedOperation("execute");
		ojClass.addToOperations(execute);
		// add executedOn property for sorting purposes
		OJAnnotatedField f = OJUtil.addPersistentProperty(ojClass, "executedOn", new OJPathName(Date.class.getName()), true);
		if(isPersistent(oc)){
			OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
			column.putAttribute(new OJAnnotationAttributeValue("name", "executed_on"));
			f.putAnnotation(column);
			OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName("javax.persistence.Temporal"), new OJEnumValue(new OJPathName(
					"javax.persistence.TemporalType"), "TIMESTAMP"));
			f.putAnnotation(temporal);
		}
		execute.getBody().addToStatements("setExecutedOn(new Date())");
		if(oc instanceof Behavior && ((Behavior) oc).getPreconditions().size() > 0){
			execute.getBody().addToStatements("evaluatePreConditions()");
			OJUtil.addFailedConstraints(execute);
		}
		return execute;
	}
	protected OJAnnotatedOperation createEventConsumerSignature(Behavior behavior,OJAnnotatedClass activityClass,Event event){
		if(event instanceof MessageEvent){
			IMessageMap map1;
			if(event instanceof CallEvent){
				map1 = ojUtil.buildOperationMap(((CallEvent) event).getOperation());
			}else{
				map1 = ojUtil.buildSignalMap(((SignalEvent) event).getSignal());
			}
			OJAnnotatedOperation listener = operationAnnotator.findOrCreateEventConsumer(behavior, activityClass, map1);
			return listener;
		}else{
			String methodName = eventUtil.getEventConsumerName(event);
			OJAnnotatedOperation listener = (OJAnnotatedOperation) activityClass.getUniqueOperation(methodName);
			if(listener == null){
				listener = new OJAnnotatedOperation(methodName);
				listener.setReturnType(new OJPathName("boolean"));
				activityClass.addToOperations(listener);
				if(event instanceof TimeEvent && EmfTimeUtil.isDeadline((Event) event)){
					Element origin = EmfEventUtil.getDeadlineOrigin((TimeEvent) event);
					OJPathName pn = null;
					if(origin instanceof Operation){
						pn = ojUtil.classifierPathname(getLibrary().getMessageStructure(((Operation) origin)));
					}else if(origin instanceof OpaqueAction && EmfActionUtil.isSingleScreenTask((ActivityNode) origin)){
						pn = ojUtil.classifierPathname(getLibrary().getMessageStructure(((OpaqueAction) origin)));
					}else{
						pn = ojUtil.classifierPathname((CallBehaviorAction) origin);
					}
					listener.addParam("triggerDate", ojUtil.buildClassifierMap(workspace.getOpaeumLibrary().getDateType(), (CollectionKind) null)
							.javaTypePath());
					listener.addParam("source", pn);
				}else if(event instanceof ChangeEvent){
					listener.addParam("callingToken", BpmUtil.ITOKEN);
				}else if(event instanceof TimeEvent){
					listener.addParam("callingToken", BpmUtil.ITOKEN);
					listener.addParam("triggerDate", ojUtil.buildClassifierMap(workspace.getOpaeumLibrary().getDateTimeType(), (CollectionKind) null)
							.javaTypePath());
				}
				listener.initializeResultVariable("false");
			}
			return listener;
		}
	}
	protected void implementIProcess(Behavior behavior,OJAnnotatedClass ojStateMachine){
		OJAnnotatedOperation isStepActive = new OJAnnotatedOperation("isStepActive", new OJPathName("boolean"));
		OJPathName type = new OJPathName("java.lang.Class");
		type.addToElementTypes(new OJPathName("? extends " + BpmUtil.IEXECUTION_ELEMENT.getLast()));
		isStepActive.addParam("clss", type);
		ojStateMachine.addToOperations(isStepActive);
		OJForStatement forEachToken = new OJForStatement("token", BpmUtil.ITOKEN, "getTokens()");
		isStepActive.getBody().addToStatements(forEachToken);
		OJIfStatement ifInstance = new OJIfStatement("clss.isInstance(token.getCurrentExecutionElement())", "return true");
		forEachToken.getBody().addToStatements(ifInstance);
		isStepActive.initializeResultVariable("false");
		ojStateMachine.addToOperations(isStepActive);
		// TODO find another place for this
		if(EmfBehaviorUtil.isClassifierBehavior(behavior)){
			OJAnnotatedClass context = findJavaClass(behavior.getContext());
			context.addToImports(BpmUtil.IEXECUTION_ELEMENT);
			OJOperation copy = new OJAnnotatedOperation(isStepActive.getName(), new OJPathName("boolean"));
			copy.addParam("clss", type);
			String getter2 = ojUtil.buildStructuralFeatureMap(getLibrary().getEndToComposite(behavior).getOtherEnd()).getter();
			copy.getBody().addToStatements("return " + getter2 + "()!=null && " + getter2 + "()." + copy.getName() + "(clss)");
			context.addToOperations(copy);
		}
		OJAnnotatedOperation getInnermostNonParallelStep = new OJAnnotatedOperation("getInnermostNonParallelStep", BpmUtil.IPROCESS_STEP);
		ojStateMachine.addToOperations(getInnermostNonParallelStep);
		getInnermostNonParallelStep.initializeResultVariable("null");
		OJForStatement forEachToken2 = new OJForStatement("token", BpmUtil.ITOKEN, "getTokens()");
		getInnermostNonParallelStep.getBody().addToStatements(forEachToken2);
		OJIfStatement ifIsParentNull = new OJIfStatement("token.getParentToken()==null", "return ("+BpmUtil.IPROCESS_STEP.getLast()+")token.getInnermostNonParallelToken().getCurrentExecutionElement()");
		forEachToken2.getBody().addToStatements(ifIsParentNull);
	}
	protected void implementIBehaviorExecution(Behavior behavior,OJAnnotatedClass ojStateMachine,OJPathName tokenSuperClass){
		buildGetExecutionElements(behavior, ojStateMachine);
		OJPathName tokenPathName = ojUtil.tokenPathName(behavior);
		OJAnnotatedClass tokenClass = new OJAnnotatedClass(tokenPathName.getLast());
		findOrCreatePackage(tokenPathName.getHead()).addToClasses(tokenClass);
		createTextPath(tokenClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		tokenPathName.addToElementTypes(ojUtil.classifierPathname(behavior));
		OJUtil.addMetaInfo(tokenClass, behavior);
		if(behavior.getGenerals().size() > 0 && behavior.getGenerals().get(0) instanceof StateMachine){
			tokenClass.setSuperclass(ojUtil.tokenPathName((Behavior) behavior.getGenerals().get(0)).getCopy());
			if(behavior.isAbstract() || EmfClassifierUtil.getAllConcreteSubClassifiers(behavior, getModelInScope()).size() > 0){
				tokenClass.addGenericTypeParam("SME extends " + ojStateMachine.getName());
				tokenClass.getSuperclass().addToElementTypes(new OJPathName("SME"));
			}else{
				tokenClass.getSuperclass().addToElementTypes(ojUtil.classifierPathname(behavior));
			}
			OJConstructor c = new OJConstructor();
			tokenClass.addToConstructors(c);
			c.addParam("parentToken", tokenClass.getPathName());
			c.setDelegateConstructor("super(parentToken)");
		}else{
			tokenClass.setSuperclass(tokenSuperClass.getCopy());
			OJPathName iTokenPath = BpmUtil.ITOKEN.getCopy();
			if(behavior.isAbstract() || EmfClassifierUtil.getAllConcreteSubClassifiers(behavior, getModelInScope()).size() > 0){
				tokenClass.addGenericTypeParam("SME extends " + ojStateMachine.getName());
				tokenClass.getSuperclass().addToElementTypes(new OJPathName("SME"));
				iTokenPath.addToElementTypes(new OJPathName("SME"));
				tokenClass.addToFields(new OJAnnotatedField("stateMachineExecution", ojUtil.classifierPathname(behavior)));
				OJAnnotatedOperation setStateMachineExecution = new OJAnnotatedOperation("setStateMachineExecution");
				setStateMachineExecution.addParam("stateMachineExecution", ojUtil.classifierPathname(behavior));
				setStateMachineExecution.getBody().addToStatements("this.stateMachineExecution=stateMachineExecution");
				tokenClass.addToOperations(setStateMachineExecution);
				OJAnnotatedOperation getStateMachineExecution = new OJAnnotatedOperation("getStateMachineExecution", new OJPathName("SME"));
				tokenClass.addToOperations(getStateMachineExecution);
				getStateMachineExecution.initializeResultVariable("(SME)stateMachineExecution");
			}else{
				tokenClass.getSuperclass().addToElementTypes(ojUtil.classifierPathname(behavior));
				iTokenPath.addToElementTypes(ojUtil.classifierPathname(behavior));
				OJUtil.addPersistentProperty(tokenClass, "stateMachineExecution", ojUtil.classifierPathname(behavior), true);
			}
			OJConstructor c = new OJConstructor();
			tokenClass.addToConstructors(c);
			c.addParam("parentToken", tokenClass.getPathName());
			c.getBody().addToStatements("this.parentToken=parentToken");
			addParentAndChildTokens(tokenClass, iTokenPath);
			addGetTokens(behavior, ojStateMachine);
		}
		addCreateAndRemoveToken(ojStateMachine, tokenClass);
	}
	private void addCreateAndRemoveToken(OJAnnotatedClass ojStateMachine,OJAnnotatedClass tokenClass){
		OJAnnotatedOperation removeToken = new OJAnnotatedOperation("removeToken");
		ojStateMachine.addToOperations(removeToken);
		removeToken.addParam("smToken", BpmUtil.ITOKEN);
		removeToken.getBody().addToStatements("tokens.remove((" + tokenClass.getName() + ")smToken)");
		OJAnnotatedOperation createToken = new OJAnnotatedOperation("createToken", BpmUtil.ITOKEN);
		ojStateMachine.addToOperations(createToken);
		createToken.addParam("smToken", BpmUtil.ITOKEN);
		createToken.initializeResultVariable("new " + tokenClass.getName() + "((" + tokenClass.getName() + ")smToken)");
		createToken.getBody().addToStatements("tokens.add((" + tokenClass.getName() + ")result)");
	}
	private void addParentAndChildTokens(OJAnnotatedClass tokenClass,OJPathName iTokenPath){
		OJUtil.addPersistentProperty(tokenClass, "parentToken", iTokenPath, true);
		OJPathName setOfITokens = new OJPathName("java.util.Set");
		setOfITokens.addToElementTypes(iTokenPath);
		OJAnnotatedField childTokens = OJUtil.addPersistentProperty(tokenClass, "childTokens", setOfITokens, true);
		OJAnnotationValue childTokensOneToMany = new OJAnnotationValue(new OJPathName("javax.persistence.OneToMany"));
		childTokens.addAnnotationIfNew(childTokensOneToMany);
		childTokensOneToMany.putAttribute("mappedBy", "parentToken");
	}
	private void addGetTokens(Behavior behavior,OJAnnotatedClass ojStateMachine){
		OJPathName setOfMyTokens = new OJPathName("java.util.Set");
		OJPathName copy = ojUtil.tokenPathName(behavior).getCopy();
		setOfMyTokens.addToElementTypes(copy);
		OJAnnotatedField tokens = new OJAnnotatedField("tokens", setOfMyTokens);
		tokens.setVisibility(OJVisibilityKind.PROTECTED);
		ojStateMachine.addToFields(tokens);
		OJPathName setOfTokens = new OJPathName("java.util.Set");
		setOfTokens.addToElementTypes(BpmUtil.ITOKEN);
		OJAnnotatedOperation getTokens = new OJAnnotatedOperation("getTokens", setOfTokens);
		ojStateMachine.addToOperations(getTokens);
		getTokens.initializeResultVariable("new HashSet<IToken>(tokens)");
		OJAnnotationValue tokensOneToMany = new OJAnnotationValue(new OJPathName("javax.persistence.OneToMany"));
		tokens.addAnnotationIfNew(tokensOneToMany);
		tokensOneToMany.putAttribute("mappedBy", "stateMachineExecution");
	}
	private void buildGetExecutionElements(Behavior behavior,OJAnnotatedClass ojStateMachine){
		OJPathName map = new OJPathName("java.util.Map");
		map.addToElementTypes(new OJPathName("String"));
		map.addToElementTypes(BpmUtil.IEXECUTION_ELEMENT);
		ojStateMachine.addToFields(new OJAnnotatedField("executionElements", map));
		OJAnnotatedOperation getExecutionElement = new OJAnnotatedOperation("getExecutionElements", map);
		getExecutionElement.initializeResultVariable("executionElements");
		OJIfStatement ifNull = new OJIfStatement("executionElements==null", "result=executionElements=new HashMap<String,"
				+ BpmUtil.IEXECUTION_ELEMENT + ">()");
		getExecutionElement.getBody().addToStatements(ifNull);
		initializeExecutionElements(behavior, ojStateMachine, ifNull);
		ojStateMachine.addToOperations(getExecutionElement);
	}
	protected void implementIProcessStep(OJAnnotatedClass ojStep, NamedElement ne, Collection<Trigger> methodTriggers){
		OJAnnotatedOperation getHumanName = new OJAnnotatedOperation("getHumanName", new OJPathName("String"));
		ojStep.addToOperations(getHumanName);
		getHumanName.initializeResultVariable("\""+EmfElementUtil.getHumanName(ne) +"\"");
		ojStep.addToImports(BpmUtil.TRIGGER_METHOD);
		OJAnnotatedOperation op = new OJAnnotatedOperation("getTriggerMethods", new OJPathName("TriggerMethod[]"));
		ojStep.addToOperations(op);
		SortedSet<Trigger> sortedSet = new TreeSet<Trigger>(new DefaultOpaeumComparator());
		sortedSet.addAll(methodTriggers);
		StringBuilder sb = new StringBuilder("new TriggerMethod[]{");
		Iterator<Trigger> iter = sortedSet.iterator();
		while(iter.hasNext()){
			Trigger t = iter.next();
			sb.append("new TriggerMethod(");
			sb.append(EmfBehaviorUtil.isHumanTrigger(t));
			sb.append(",\"");
			sb.append( NameConverter.separateWords(NameConverter.capitalize(t.getEvent().getName())));
			sb.append("\",\"");
			sb.append(t.getEvent().getName());
			sb.append("\")");
			if(iter.hasNext()){
				sb.append(',');
			}
		}
		sb.append('}');
		op.initializeResultVariable(sb.toString());
	}

	protected void initializeExecutionElements(Behavior behavior,OJAnnotatedClass ojStateMachine,OJIfStatement ifNull){
		// TODO Auto-generated method stub
	}
}