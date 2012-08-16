package org.opaeum.javageneration.basicjava;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.Type;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.EmfEventUtil;
import org.opaeum.eclipse.EmfOperationUtil;
import org.opaeum.eclipse.EmfReceptionUtil;
import org.opaeum.emf.workspace.DefaultOpaeumComparator;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedElement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotatedParameter;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.bpm.BpmUtil;
import org.opaeum.javageneration.maps.IMessageMap;
import org.opaeum.javageneration.maps.SignalMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = JavaTransformationPhase.class,requires = {AttributeImplementor.class,SuperTypeGenerator.class},after = {
		AttributeImplementor.class,SuperTypeGenerator.class})
public class OperationAnnotator extends StereotypeAnnotator{
	@VisitBefore(matchSubclasses = true)
	public void visitBehaviors(Behavior o){
		if(o.getContext() == null){
			processBehavior(o);
		}
	}
	private void processBehavior(Behavior o){
		if(ojUtil.hasOJClass(o.getContext()) && !EmfBehaviorUtil.isClassifierBehavior(o) && o.getOwner() instanceof Classifier){
			// DO not do effects, state actions or classifier behavior - will be
			// invoked elsewhere
			if(o.getSpecification() == null || !o.getName().equals(o.getSpecification().getName())
					|| !o.getContext().equals(o.getSpecification().getOwner())){
				// if the specification has a different name to to behavior or the specificatoin is in a
				// superclass/interface,
				// we need to create a matching OJOperation
				OJAnnotatedOperation oper = createOperation(o.getContext(), o, findJavaClass(o.getContext()));
				if(EmfBehaviorUtil.hasExecutionInstance(o)){
					ClassifierMap cmap = ojUtil.buildClassifierMap(o, (CollectionKind) null);
					oper.setReturnType(cmap.javaTypePath());
				}
			}
		}
		if(EmfBehaviorUtil.isProcess(o) && o.getSpecification() == null){
			createCallbackListener(o, o);
		}
		if(o instanceof Activity){
			Activity a = (Activity) o;
			for(ActivityNode n:EmfActivityUtil.getActivityNodesRecursively(a)){
				if(EmfActionUtil.isSingleScreenTask(n)){
					visitClass(getLibrary().getMessageStructure((OpaqueAction) n));
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitClass(Classifier c){
		if(ojUtil.hasOJClass(c)){
			OJAnnotatedClass ojClass = findJavaClass(c);
			Collection<Operation> directlyImplementedOperations = EmfOperationUtil.getDirectlyImplementedOperations(c);
			for(Operation o:directlyImplementedOperations){
				if(o.getOwner() == c){
					if(EmfBehaviorUtil.hasExecutionInstance(o)){
						OJAnnotatedOperation getter = (OJAnnotatedOperation) findJavaClass(getLibrary().getMessageStructure(o)).getUniqueOperation(
								"getSelf");
						getter.setVisibility(OJVisibilityKind.PRIVATE);
						getter.initializeResultVariable("getContextObject()");
						visitClass(getLibrary().getMessageStructure(o));// why???
					}
					createCallbackListener(o, getLibrary().getMessageStructure(o));
				}
				OperationMap operationMap = ojUtil.buildOperationMap(o);
				OJAnnotatedOperation oper1 = findOrCreateOperation(c, ojClass, operationMap, operationMap.isLongRunning());
				applyStereotypesAsAnnotations((o), oper1);
				if(!o.isQuery()){
					findOrCreateCallEventConsumer(c, ojClass, operationMap);
					findOrCreateEventGenerator(c, ojClass, operationMap);
				}
			}
			if(c instanceof BehavioredClassifier){
				BehavioredClassifier nbc = (BehavioredClassifier) c;
				SortedSet<Signal> signals = new TreeSet<Signal>(new DefaultOpaeumComparator());
				for(Reception o:EmfReceptionUtil.getDirectlyImplementedReceptions(nbc)){
					signals.add(o.getSignal());
				}
				for(Event e:EmfEventUtil.getEventsInScopeForClassAsContext( nbc)){
					if(e instanceof SignalEvent){
						signals.add(((SignalEvent) e).getSignal());
					}
				}
				for(Signal s:signals){
					SignalMap map = ojUtil.buildSignalMap(s);
					ojClass.addToImplementedInterfaces(map.receiverContractTypePath());
					findOrCreateJavaReception(ojClass, map);
					findOrCreateSignalEventConsumer(nbc, ojClass, map);
					findOrCreateEventGenerator(nbc, ojClass, map);
				}
				for(Behavior b:nbc.getOwnedBehaviors()){
					processBehavior(b);
				}
			}
		}
	}
	public OJAnnotatedOperation findOrCreateEventGenerator(Classifier c,OJAnnotatedClass ojClass,OperationMap operationMap){
		OJAnnotatedOperation eventGenerator = (OJAnnotatedOperation) ojClass.findOperation(operationMap.eventGeratorMethodName(),
				operationMap.javaParamTypePaths());
		if(eventGenerator == null){
			eventGenerator = new OJAnnotatedOperation(operationMap.eventGeratorMethodName());
			ojClass.addToOperations(eventGenerator);
			addParameters(c, eventGenerator, operationMap.getArgumentParameters());
		}
		return eventGenerator;
	}
	private  OJAnnotatedOperation findOrCreateCallEventConsumer(Classifier c,OJAnnotatedClass ojClass,OperationMap map){
		OJAnnotatedOperation oper = (OJAnnotatedOperation) ojClass.findOperation(map.eventConsumerMethodName(), map.javaParamTypePaths());
		if(oper == null){
			oper = new OJAnnotatedOperation(map.eventConsumerMethodName(), new OJPathName("boolean"));
			oper.initializeResultVariable("false");
			ojClass.addToOperations(oper);
			addParameters(c, oper, map.getArgumentParameters());
			if(map.getNamedElement() instanceof Operation){
				Operation no = (Operation) map.getNamedElement();
				if(c != null && c.getGenerals().size() > 0 && c.getGenerals().get(0).conformsTo((Classifier) no.getOwner())){
					oper.getBody().addToStatements("result=super." + oper.getName() + "(" + delegateParameters(oper) + ")");
				}
			}
		}
		return oper;
	}
	private static OJAnnotatedOperation findOrCreateSignalEventConsumer(Classifier c,OJAnnotatedClass ojClass,SignalMap map){
		OJAnnotatedOperation oper = (OJAnnotatedOperation) ojClass.findOperation(map.eventConsumerMethodName(),
				Arrays.asList(map.javaTypePath()));
		if(oper == null){
			oper = new OJAnnotatedOperation(map.eventConsumerMethodName(), new OJPathName("boolean"));
			ojClass.addToOperations(oper);
			oper.initializeResultVariable("false");
			oper.addParam("signal", map.javaTypePath());
			if(c.getGenerals().size()>0 && c.getGenerals().get(0) instanceof BehavioredClassifier){
				BehavioredClassifier gbc = (BehavioredClassifier) c.getGenerals().get(0);
				if(EmfEventUtil.hasReceptionOrTriggerFor(gbc,map.getSignal())){
					oper.getBody().addToStatements("result=super." + oper.getName() + "(" + delegateParameters(oper) + ")");
				}
			}
		}
		return oper;
	}
	public static OJAnnotatedOperation findOrCreateJavaReception(OJAnnotatedClass ojContext,SignalMap map){
		OJAnnotatedOperation oper = (OJAnnotatedOperation) ojContext.findOperation(map.receiveMethodName(), Arrays.asList(map.javaTypePath()));
		if(oper == null){
			oper = new OJAnnotatedOperation(map.receiveMethodName());
			oper.addParam("signal", map.javaTypePath());
			ojContext.addToOperations(oper);
			oper.getBody().addToStatements(map.eventGeratorMethodName() + "(signal)");
		}
		return oper;
	}
	private OJAnnotatedOperation createOperation(Classifier context,NamedElement o,OJAnnotatedClass owner){
		OperationMap operationMap = ojUtil.buildOperationMap(o);
		OJAnnotatedOperation oper = findOrCreateOperation(context, owner, operationMap, operationMap.isLongRunning());
		applyStereotypesAsAnnotations((o), oper);
		List<Parameter> argumentParameters = operationMap.getArgumentParameters();
		for(int i = 0;i < argumentParameters.size();i++){
			if(operationMap.isLongRunning()){
				// Include return info
				applyStereotypesAsAnnotations(argumentParameters.get(i), (OJAnnotatedElement) oper.getParameters().get(i + 1));
			}else{
				applyStereotypesAsAnnotations(argumentParameters.get(i), (OJAnnotatedElement) oper.getParameters().get(i));
			}
		}
		return oper;
	}
	public OJAnnotatedOperation findOrCreateOperation(Classifier context,OJAnnotatedClass owner,OperationMap map,
			boolean withReturnInfo){
		OJAnnotatedOperation oper = (OJAnnotatedOperation) owner.findOperation(map.javaOperName(), map.javaParamTypePathsWithReturnInfo());
		if(oper == null){
			oper = new OJAnnotatedOperation(map.javaOperName());
			owner.addToOperations(oper);
			if(map.hasMessageStructure()){
				oper.setReturnType(map.messageStructurePath());
			}else if(map.getReturnParameter() != null){
				oper.setReturnType(map.javaReturnTypePath());
				owner.addToImports(map.javaReturnTypePath());
			}
			List<Parameter> argumentParameters = map.getArgumentParameters();
			if(withReturnInfo){
				oper.addParam("returnInfo", BpmUtil.ITOKEN);
			}
			addParameters(context, oper, argumentParameters);
			NamedElement o = map.getNamedElement();
			if(!(owner instanceof OJAnnotatedInterface)){
				if(map.hasMessageStructure()){
					oper.initializeResultVariable("new " + map.messageStructurePath().getLast() + "(this)");
					oper.getResultVariable().setType(map.messageStructurePath());
					List<? extends Parameter> args = map.getArgumentParameters();
					for(Parameter arg:args){
						PropertyMap argMap = ojUtil.buildStructuralFeatureMap(arg);
						oper.getBody().addToStatements("result." + argMap.setter() + "(" + argMap.fieldname() + ")");
					}
					if(withReturnInfo){
						oper.getBody().addToStatements("result.setReturnInfo(returnInfo)");
					}
					if(map.isLongRunning()){
						OJSimpleStatement executeStatement = new OJSimpleStatement("result.execute()");
						final String EXECUTE_STATEMENT = "executeStatement";
						executeStatement.setName(EXECUTE_STATEMENT);
						oper.getBody().addToStatements(executeStatement);
					}
					if(o instanceof Operation && !((Operation) o).isQuery()){
						oper.getBody().addToStatements(map.eventGeratorMethodName() + "(" + delegateParameters(oper) + ")");
					}
				}else if(map.getReturnParameter() != null){
					owner.addToImports(map.javaReturnDefaultTypePath());
					oper.initializeResultVariable(map.javaReturnDefaultValue());
					if(o instanceof Operation && !((Operation) o).isQuery()){
						oper.getBody().addToStatements(map.eventGeratorMethodName() + "(" + delegateParameters(oper) + ")");
					}
				}else if(o instanceof Operation && !((Operation) o).isQuery()){
					oper.getBody().addToStatements(map.eventGeratorMethodName() + "(" + delegateParameters(oper) + ")");
				}
			}
			oper.setVisibility(map.javaVisibility());
			OJUtil.addMetaInfo(oper, o);
		}
		return oper;
	}
	private  void addParameters(Classifier context,OJAnnotatedOperation oper,List<? extends Parameter> argumentParameters){
		for(Parameter elem:argumentParameters){
			PropertyMap pMap = ojUtil.buildStructuralFeatureMap(elem);
			OJAnnotatedParameter param = new OJAnnotatedParameter(pMap.fieldname(),pMap.javaTypePath());
			oper.addToParameters(param);
			OJAnnotationValue ap = new OJAnnotationValue(new OJPathName("org.opaeum.annotation.ParameterMetaInfo"));
			param.putAnnotation(ap);
			ap.putAttribute("uuid", EmfWorkspace.getId(elem));
			ap.putAttribute("opaeumId", EmfWorkspace.getOpaeumId(elem));
			ap.putAttribute("name", elem.getName());
			if(EmfElementUtil.getDocumentation(elem) != null){
				ap.putAttribute("shortDescripion", EmfElementUtil.getDocumentation(elem));
			}
			if(EmfClassifierUtil.isSimpleType(pMap.getBaseType())){
				DataType e = (DataType) pMap.getBaseType();
				AbstractStrategyFactory sf = EmfClassifierUtil.getStrategyFactory(e);
				if(sf!=null && sf.getRuntimeStrategyFactory() != null){
					ap.putAttribute("strategyFactory", new OJPathName(sf.getRuntimeStrategyFactory()));
				}
			}
			oper.getOwner().addToImports(pMap.javaTypePath());
		}
	}
	public static String delegateParameters(OJOperation ojOperation){
		StringBuilder statement = new StringBuilder();
		Iterator<OJParameter> parms = ojOperation.getParameters().iterator();
		while(parms.hasNext()){
			OJParameter parm = parms.next();
			if(!parm.getType().equals(BpmUtil.ITOKEN)){
				statement.append(parm.getName());
				if(parms.hasNext()){
					statement.append(",");
				}
			}
		}
		return statement.toString();
	}
	public static OJAnnotatedOperation findOrCreateEventGenerator(BehavioredClassifier context,OJAnnotatedClass ojClass,SignalMap map){
		OJAnnotatedOperation eventGenerator = (OJAnnotatedOperation) ojClass.findOperation(map.eventGeratorMethodName(),
				Arrays.asList(map.javaTypePath()));
		if(eventGenerator == null){
			eventGenerator = new OJAnnotatedOperation(map.eventGeratorMethodName());
			eventGenerator.addParam("signal", map.javaDefaultTypePath());
			ojClass.addToOperations(eventGenerator);
		}
		return eventGenerator;
	}
	public OJAnnotatedOperation findOrCreateEventConsumer(BehavioredClassifier context,OJAnnotatedClass ojContext,IMessageMap map1){
		if(map1 instanceof SignalMap){
			return findOrCreateSignalEventConsumer(context, ojContext, (SignalMap) map1);
		}else{
			return findOrCreateCallEventConsumer(context, ojContext, (OperationMap) map1);
		}
	}
	private void createCallbackListener(NamedElement no,Classifier message){
		OperationMap map = ojUtil.buildOperationMap(no);
		if(map.isLongRunning()){
			if(!map.hasContract()){
				OJAnnotatedInterface listener = new OJAnnotatedInterface(map.callbackListener());
				OJPackage pack = findOrCreatePackage(map.callbackListenerPath().getHead());
				listener.setMyPackage(pack);
				OJAnnotatedOperation callBackOper = new OJAnnotatedOperation(map.callbackOperName());
				callBackOper.addParam("callingToken", BpmUtil.ITOKEN);
				callBackOper.addParam("completedProcess", map.messageStructurePath());
				listener.addToOperations(callBackOper);
				List<? extends Parameter> exceptionParameters = map.getExceptionParameters();
				for(Parameter e:exceptionParameters){
					OJAnnotatedOperation exceptionOper = new OJAnnotatedOperation(map.exceptionOperName(e));
					exceptionOper.addParam("callingToken", BpmUtil.ITOKEN);
					exceptionOper.addParam("failedProcess", map.messageStructurePath());
					listener.addToOperations(exceptionOper);
				}
				if(no instanceof Operation){
					Collection<Type> raisedExceptions = ((Operation) no).getRaisedExceptions();
					for(Type e:raisedExceptions){
						OJAnnotatedOperation exceptionOper = new OJAnnotatedOperation(map.exceptionOperName(e));
						exceptionOper.addParam("callingToken", BpmUtil.ITOKEN);
						exceptionOper.addParam("exception", e.getName());
						listener.addToImports(ojUtil.classifierPathname((Classifier) e));
						exceptionOper.addParam("failedProcess", map.messageStructurePath());
						listener.addToOperations(exceptionOper);
					}
				}
				OJAnnotatedOperation unhandledExceptionHandler = new OJAnnotatedOperation(map.unhandledExceptionOperName());
				unhandledExceptionHandler.addParam("callingToken", BpmUtil.ITOKEN);
				unhandledExceptionHandler.addParam("exception", new OJPathName("Object"));
				unhandledExceptionHandler.addParam("completedProcess", map.messageStructurePath());
				listener.addToOperations(unhandledExceptionHandler);
				super.createTextPath(listener, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			}
		}
	}
}
