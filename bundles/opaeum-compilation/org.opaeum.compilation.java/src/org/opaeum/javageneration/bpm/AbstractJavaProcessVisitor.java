package org.opaeum.javageneration.bpm;

import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.ChangeEvent;
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
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.EmfEventUtil;
import org.opaeum.eclipse.EmfTimeUtil;
import org.opaeum.eclipse.PersistentNameUtil;
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
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.maps.IMessageMap;
import org.opaeum.javageneration.persistence.JpaUtil;
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
			if(context != null){
				context.addToImports(BpmUtil.IEXECUTION_ELEMENT);
				OJOperation copy = new OJAnnotatedOperation(isStepActive.getName(), new OJPathName("boolean"));
				copy.addParam("clss", type);
				String getter2 = ojUtil.buildStructuralFeatureMap(getLibrary().getEndToComposite(behavior).getOtherEnd()).getter();
				copy.getBody().addToStatements("return " + getter2 + "()!=null && " + getter2 + "()." + copy.getName() + "(clss)");
				context.addToOperations(copy);
			}
		}
		OJAnnotatedOperation getInnermostNonParallelStep = new OJAnnotatedOperation("getInnermostNonParallelStep", BpmUtil.IPROCESS_STEP);
		ojStateMachine.addToOperations(getInnermostNonParallelStep);
		getInnermostNonParallelStep.initializeResultVariable("null");
		OJForStatement forEachToken2 = new OJForStatement("token", BpmUtil.ITOKEN, "getTokens()");
		getInnermostNonParallelStep.getBody().addToStatements(forEachToken2);
		OJIfStatement ifIsParentNull = new OJIfStatement("token.getParentToken()==null", "return (" + BpmUtil.IPROCESS_STEP.getLast()
				+ ")token.getInnermostNonParallelToken().getCurrentExecutionElement()");
		forEachToken2.getBody().addToStatements(ifIsParentNull);
	}
	protected void implementIBehaviorExecution(Behavior behavior,OJAnnotatedClass ojStateMachine,OJPathName tokenSuperClass){
		buildGetExecutionElements(behavior, ojStateMachine);
		OJPathName tokenPathName = ojUtil.tokenPathName(behavior);
		OJAnnotatedClass tokenClass = new OJAnnotatedClass(tokenPathName.getLast());
		if(isPersistent(behavior)){
			JpaUtil.buildTableAnnotation(tokenClass, PersistentNameUtil.getPersistentName(behavior) + "_token", config,behavior);
			OJAnnotationValue entyt = new OJAnnotationValue(new OJPathName(Entity.class.getName()));
			tokenClass.addAnnotationIfNew(entyt);
			entyt.putAttribute("name", NameConverter.toJavaVariableName(behavior.getName()) + "Token");
			OJAnnotationValue inheritance = new OJAnnotationValue(new OJPathName(Inheritance.class.getName()));
			inheritance.putAttribute("strategy", new OJEnumValue(new OJPathName(InheritanceType.class.getName()), "SINGLE_TABLE"));
			tokenClass.addAnnotationIfNew(inheritance);
			OJAnnotationValue discriminatorColumn = new OJAnnotationValue(new OJPathName(DiscriminatorColumn.class.getName()));
			discriminatorColumn.putAttribute("discriminatorType", new OJEnumValue(new OJPathName(DiscriminatorType.class.getName()), "STRING"));
			tokenClass.addAnnotationIfNew(discriminatorColumn);
			if(behavior.getGenerals().size() > 0){
				tokenClass.addAnnotationIfNew(new OJAnnotationValue(new OJPathName(DiscriminatorValue.class.getName()), EmfWorkspace
						.getId(behavior)));
			}
		}
		findOrCreatePackage(tokenPathName.getHead()).addToClasses(tokenClass);
		createTextPath(tokenClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		tokenPathName.addToElementTypes(ojUtil.classifierPathname(behavior));
		OJUtil.addMetaInfo(tokenClass, behavior);
		if(behavior.getGenerals().size() > 0 && behavior.getGenerals().get(0) instanceof StateMachine){
			tokenClass.setSuperclass(ojUtil.tokenPathName((Behavior) behavior.getGenerals().get(0)).getCopy());
			tokenClass.addGenericTypeParam("SME extends " + ojStateMachine.getName());
			tokenClass.getSuperclass().addToElementTypes(new OJPathName("SME"));
			OJConstructor c = new OJConstructor();
			tokenClass.addToConstructors(c);
			c.addParam("parentToken", tokenClass.getPathName());
			c.setDelegateConstructor("super(parentToken)");
		}else{
			tokenClass.setSuperclass(tokenSuperClass.getCopy());
			OJPathName iTokenPath = BpmUtil.ITOKEN.getCopy();
			tokenClass.addGenericTypeParam("SME extends " + ojStateMachine.getName());
			tokenClass.getSuperclass().addToElementTypes(new OJPathName("SME"));
			iTokenPath.addToElementTypes(new OJPathName("SME"));
			OJAnnotatedField behaviorExecution = new OJAnnotatedField("behaviorExecution", ojUtil.classifierPathname(behavior));
			tokenClass.addToFields(behaviorExecution);
			if(isPersistent(behavior)){
				behaviorExecution.addAnnotationIfNew(new OJAnnotationValue(new OJPathName(ManyToOne.class.getName())));
				JpaUtil.addJoinColumn(behaviorExecution, "behavior_execution_id", false);
			}
			OJAnnotatedOperation setBehaviorExecution = new OJAnnotatedOperation("setBehaviorExecution");
			setBehaviorExecution.addParam("behaviorExecution", ojUtil.classifierPathname(behavior));
			setBehaviorExecution.getBody().addToStatements("this.behaviorExecution=behaviorExecution");
			tokenClass.addToOperations(setBehaviorExecution);
			OJAnnotatedOperation getBehaviorExecution = new OJAnnotatedOperation("getBehaviorExecution", new OJPathName("SME"));
			tokenClass.addToOperations(getBehaviorExecution);
			getBehaviorExecution.initializeResultVariable("(SME)behaviorExecution");
			OJConstructor c = new OJConstructor();
			tokenClass.addToConstructors(c);
			c.addParam("parentToken", tokenClass.getPathName());
			c.getBody().addToStatements("this.parentToken=parentToken");
			c.getBody().addToStatements(new OJIfStatement("parentToken!=null", "parentToken.getChildTokens().add(this)"));
			addParentAndChildTokens(tokenClass, iTokenPath);
			addGetTokens(behavior, ojStateMachine);
		}
		tokenClass.addToConstructors(new OJConstructor());
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
		createToken.getResultVariable().setType(tokenClass.getPathName());
		createToken.getBody().addToStatements("tokens.add(result)");
		createToken.getBody().addToStatements("result.setBehaviorExecution(this)");
	}
	private void addParentAndChildTokens(OJAnnotatedClass tokenClass,OJPathName iTokenPath){
		OJAnnotatedField parentToken = ojUtil.addPersistentProperty(tokenClass, "parentToken", iTokenPath, true);
		OJAnnotationValue manyToOne = new OJAnnotationValue(new OJPathName(ManyToOne.class.getName()));
		parentToken.putAnnotation(manyToOne);
		manyToOne.putAttribute("targetEntity", tokenClass.getPathName());
		JpaUtil.addJoinColumn(parentToken, "parent_token_id", false);
		OJPathName setOfITokens = new OJPathName("java.util.Set");
		setOfITokens.addToElementTypes(iTokenPath);
		OJAnnotatedField childTokens = ojUtil.addPersistentProperty(tokenClass, "childTokens", setOfITokens, true);
		childTokens.setInitExp("new HashSet<" + iTokenPath.getTypeNameWithTypeArguments() + ">()");
		tokenClass.addToImports("java.util.HashSet");
		OJAnnotationValue childTokensOneToMany = new OJAnnotationValue(new OJPathName("javax.persistence.OneToMany"));
		childTokens.addAnnotationIfNew(childTokensOneToMany);
		childTokensOneToMany.putAttribute("mappedBy", "parentToken");
		childTokensOneToMany.putAttribute("targetEntity", tokenClass.getPathName());
	}
	private void addGetTokens(Behavior behavior,OJAnnotatedClass ojStateMachine){
		OJPathName setOfMyTokens = new OJPathName("java.util.Set");
		OJPathName copy = ojUtil.tokenPathName(behavior).getCopy();
		setOfMyTokens.addToElementTypes(copy);
		OJAnnotatedField tokens = new OJAnnotatedField("tokens", setOfMyTokens);
		tokens.setVisibility(OJVisibilityKind.PROTECTED);
		ojStateMachine.addToFields(tokens);
		tokens.setInitExp("new HashSet<" + copy.getLast() + ">()");
		ojStateMachine.addToImports("java.util.HashSet");
		OJPathName setOfTokens = new OJPathName("java.util.Set");
		setOfTokens.addToElementTypes(BpmUtil.ITOKEN);
		OJAnnotatedOperation getTokens = new OJAnnotatedOperation("getTokens", setOfTokens);
		ojStateMachine.addToOperations(getTokens);
		getTokens.initializeResultVariable("new HashSet<IToken>(tokens)");
		OJAnnotationValue tokensOneToMany = new OJAnnotationValue(new OJPathName("javax.persistence.OneToMany"));
		tokens.addAnnotationIfNew(tokensOneToMany);
		tokensOneToMany.putAttribute("mappedBy", "behaviorExecution");
	}
	private void buildGetExecutionElements(Behavior behavior,OJAnnotatedClass ojStateMachine){
		OJPathName map = mapOfExecutionElements();
		OJAnnotatedField executionElements = new OJAnnotatedField("executionElements", map);
		executionElements.addAnnotationIfNew(new OJAnnotationValue(new OJPathName(Transient.class.getName())));
		ojStateMachine.addToFields(executionElements);
		OJAnnotatedOperation getExecutionElement = new OJAnnotatedOperation("getExecutionElements", map);
		getExecutionElement.initializeResultVariable("executionElements");
		String newExecutionElements = "result=executionElements=new HashMap<String," + BpmUtil.IEXECUTION_ELEMENT + ">()";
		OJIfStatement ifNull = new OJIfStatement("executionElements==null", newExecutionElements);
		getExecutionElement.getBody().addToStatements(ifNull);
		initializeExecutionElements(behavior, ojStateMachine, ifNull);
		if(EmfTimeUtil.getTimeObservations(behavior).size()+EmfTimeUtil.getDurationObservations(behavior).size()>0){
			ifNull.getThenPart().addToStatements("registerObservations(executionElements)");
		}
		ojStateMachine.addToImports(BpmUtil.IOBSERVER);
		OJIfStatement ifObserver = new OJIfStatement("getOwningObject() instanceof " + BpmUtil.IOBSERVER.getLast(), "(("
				+ BpmUtil.IOBSERVER.getLast() + ")getOwningObject()).registerObservations(executionElements)");
		ifNull.getThenPart().addToStatements(ifObserver);
		ojStateMachine.addToOperations(getExecutionElement);
	}
	protected void implementIProcessStep(OJAnnotatedClass ojStep,NamedElement ne,Collection<Trigger> methodTriggers){
		OJAnnotatedOperation getHumanName = new OJAnnotatedOperation("getHumanName", new OJPathName("String"));
		ojStep.addToOperations(getHumanName);
		getHumanName.initializeResultVariable("\"" + EmfElementUtil.getHumanName(ne) + "\"");
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
			sb.append(NameConverter.separateWords(NameConverter.capitalize(t.getEvent().getName())));
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