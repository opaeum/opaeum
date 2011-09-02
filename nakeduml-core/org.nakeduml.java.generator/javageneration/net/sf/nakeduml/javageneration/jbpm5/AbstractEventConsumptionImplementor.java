package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.bpm.INakedDefinedResponsibility;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTriggerContainer;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.name.NameWrapper;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.OJStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.runtime.domain.IActiveObject;
import org.nakeduml.runtime.domain.ISignal;

public abstract class AbstractEventHandlerInserter extends StereotypeAnnotator{
	protected static final OJPathName NODE_INSTANCE_CONTAINER = new OJPathName("org.jbpm.workflow.instance.NodeInstanceContainer");
	protected static final OJPathName NODE_CONTAINER = new OJPathName("org.jbpm.workflow.core.NodeContainer");
	protected static final OJPathName NODE = new OJPathName("org.jbpm.workflow.core.Node");
	public static final OJPathName UML_NODE_INSTANCE = new OJPathName("org.nakeduml.runtime.domain.UmlNodeInstance");
	protected abstract void implementEventConsumption(OJOperation operationContext,FromNode node,OJIfStatement ifTokenFound);
	/**
	 * Inserts a call to the eventListener as the last line of code in the body of the triggering operation
	 */
	private void insertCallToOperationHandler(INakedBehavior behavior,INakedOperation nakedOperation){
		// TODO this code assumes that the owner of the operation is the only
		// class listening to the event - what about subclasses?
		OJAnnotatedClass ojOperationOwner = null;
		boolean operationBelongsToContext = behavior.getContext() != null && behavior.getContext().conformsTo(nakedOperation.getOwner());
		if(operationBelongsToContext){
			ojOperationOwner = findJavaClass(behavior.getContext());
		}else if(behavior.conformsTo(nakedOperation.getOwner())){
			ojOperationOwner = findJavaClass(behavior);
		}
		NakedOperationMap map = new NakedOperationMap(nakedOperation);
		OJAnnotatedOperation ojOperation = (OJAnnotatedOperation) ojOperationOwner.findOperation(map.javaOperName(), map.javaParamTypePaths());
		if(ojOperation == null){
			// In superclass, but not overridden
			ojOperation = implementInheritedEventMethod(nakedOperation, ojOperationOwner, map);
		}
		if(operationBelongsToContext){
			OJAnnotatedOperation eventOper=(OJAnnotatedOperation) ojOperationOwner.findOperation(map.eventOperName(), map.javaParamTypePaths());
			if(eventOper==null){
				eventOper=new OJAnnotatedOperation(map.eventOperName());
				ojOperationOwner.addToOperations(eventOper);
				addParameters(nakedOperation.getOwner(), eventOper, nakedOperation.getArgumentParameters());
				eventOper.getBody().addToStatements("return consumed");
			}
			eventOper.getBody().addToStatements(eventOper.getBody().getStatements().size()-1,buildCallFromContextToEventHandlerOnBehavior(behavior, nakedOperation, eventOper));
		}
		OJPathName handlerPathName = EventUtil.handlerPathName(nakedOperation);
		EventUtil.addOutgoingEventManagement(ojOperationOwner);
		ojOperationOwner.addToImports(handlerPathName);
		StringBuilder s = new StringBuilder("getOutgoingEvents().put(this,new ");
		s.append(handlerPathName.getLast());
		s.append("(");
		delegateParameters(ojOperation, s);
		s.append(",true))");
		OJSimpleStatement statement = new OJSimpleStatement(s.toString());
		List<OJStatement> statements = ojOperation.getBody().getStatements();
		if(nakedOperation.hasReturnParameter()){
			statements.add(statements.size() - 1, statement);
		}else{
			statements.add(statement);
		}
	}
	private void addParameters(INakedClassifier context,OJAnnotatedOperation oper,List<? extends INakedParameter> argumentParameters){
		for(INakedParameter elem:argumentParameters){
			OJParameter param = new OJParameter();
			NakedStructuralFeatureMap pMap = OJUtil.buildStructuralFeatureMap(context, elem);
			param.setName(pMap.umlName());
			param.setType(pMap.javaTypePath());
			oper.addToParameters(param);
			applyStereotypesAsAnnotations(((INakedElement) elem), param);
			oper.getOwner().addToImports(pMap.javaTypePath());
		}
	}

	private OJAnnotatedOperation implementInheritedEventMethod(INakedOperation nakedOperation,OJAnnotatedClass myOwner,NakedOperationMap map){
		OJAnnotatedOperation ojOperation = new OJAnnotatedOperation(map.javaOperName(), map.javaReturnTypePath());
		myOwner.addToOperations(ojOperation);
		StringBuilder sb = new StringBuilder("super." + map.javaOperName() + "(");
		Iterator<? extends INakedParameter> pIter = nakedOperation.getArgumentParameters().iterator();
		if(nakedOperation.isLongRunning()){
			ojOperation.addParam("context", Jbpm5Util.getProcessContext());
			sb.append("context");
			if(pIter.hasNext()){
				sb.append(",");
			}
		}
		addParameters(nakedOperation.getOwner(), ojOperation, nakedOperation.getArgumentParameters());
		for(INakedParameter p = null;pIter.hasNext();){
			
			p = pIter.next();
			ojOperation.addParam(p.getName(), map.javaParamTypePath(p));
			sb.append(p.getName());
			if(pIter.hasNext()){
				sb.append(',');
			}
		}
		sb.append(")");
		if(nakedOperation.hasReturnParameter() || nakedOperation.isLongRunning()){
			OJAnnotatedField result = new OJAnnotatedField("result", map.javaReturnTypePath());
			result.setInitExp(sb.toString());
			ojOperation.getBody().addToLocals(result);
			ojOperation.getBody().addToStatements("return result");
		}else{
			ojOperation.getBody().addToStatements(sb.toString());
		}
		return ojOperation;
	}
	private OJStatement buildCallFromContextToEventHandlerOnBehavior(INakedBehavior behavior,INakedElement nakedOperation,OJAnnotatedOperation ojOperation){
		OJStatement statement;
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(behavior.getEndToComposite().getOtherEnd());
		if(behavior.isClassifierBehavior()){
			statement = new OJSimpleStatement("getClassifierBehavior()." + callToEventHandler(nakedOperation, ojOperation));
		}else{
			addConsumedFieldIfNecessary(ojOperation);
			OJIfStatement ifNotConsumed = new OJIfStatement("!consumed");
			OJForStatement forEach = new OJForStatement("behavior", map.javaBaseTypePath(), map.getter() + "()");
			ifNotConsumed.getThenPart().addToStatements(forEach);
			//TODO resolve correllation properties here
			forEach.getBody().addToStatements("consumed=behavior." + callToEventHandler(nakedOperation, ojOperation));
			OJIfStatement ifConsumedNow = new OJIfStatement("consumed");
			forEach.getBody().addToStatements(ifConsumedNow);
			ifConsumedNow.getThenPart().addToStatements("break");
			statement = ifNotConsumed;
		}
		return statement;
	}
	private String callToEventHandler(INakedElement element,OJOperation ojOperation){
		StringBuilder statement = new StringBuilder(EventUtil.getEventHandlerName(element));
		statement.append("(");
		if(element instanceof INakedOperation && ((INakedOperation) element).isLongRunning()){
			statement.append("_" + element.getMappingInfo().getJavaName());
			// See SpecificationImplementor.visitOperation
		}else{
			delegateParameters(ojOperation, statement);
		}
		statement.append(")");
		return statement.toString();
	}
	public void delegateParameters(OJOperation ojOperation,StringBuilder statement){
		Iterator<OJParameter> parms = ojOperation.getParameters().iterator();
		while(parms.hasNext()){
			OJParameter parm = parms.next();
			statement.append(parm.getName());
			if(parms.hasNext()){
				statement.append(",");
			}
		}
	}
	/**
	 * Implements the processSignal method for signals and injects the event listening code for operations
	 * 
	 * @param behaviorClass
	 * @param behavior
	 */
	private void insertEventHandlerCalls(OJAnnotatedClass behaviorClass,INakedTriggerContainer behavior){
		OJAnnotatedOperation processSignal = ensureProcessSignalPresent(behaviorClass);
		List<OJStatement> statements = processSignal.getBody().getStatements();
		if(statements.size()>0 && statements.get(statements.size()-1) instanceof OJSimpleStatement){
			//Append event handling. Remember this behavior could ba context for another thus resulting in 
			// the presence of a populated processSignal
			OJSimpleStatement ojSimpleStatement = (OJSimpleStatement)statements.get(statements.size()-1);
			if(ojSimpleStatement.getExpression().equals("return false")){
				processSignal.getBody().removeFromStatements(ojSimpleStatement);
			}
		}
		for(INakedElement element:behavior.getAllMessageTriggers()){
			if(element instanceof INakedSignal){
				INakedSignal signal = (INakedSignal) element;
				insertSignalCallInProcessSignal(processSignal, signal);
				insertCallToSignalHandler(behavior, signal);
			}else if(element instanceof INakedOperation){
				INakedOperation no = (INakedOperation) element;
				insertCallToOperationHandler(behavior, no);
			}
		}
		if(behavior.getContext() != null){
			OJAnnotatedClass context = findJavaClass(behavior.getContext());
			OJOperation parentProcessSignal = OJUtil.findOperation(context, "processSignal");
			if(parentProcessSignal == null){
				OJOperation copy = processSignal.getDeepCopy();
				context.addToOperations(copy);
				copy.getBody().addToStatements("return false");
				context.addToImplementedInterfaces(new OJPathName(IActiveObject.class.getName()));
			}else{
				List<OJStatement> toStatements = parentProcessSignal.getBody().getStatements();
				List<OJStatement> fromStatements = statements;
				for(OJStatement ojStatement:fromStatements){
					toStatements.add(toStatements.size() - 1, ojStatement);
				}
			}
		}
		processSignal.getBody().addToStatements("return false");
	}
	public static OJAnnotatedOperation ensureProcessSignalPresent(OJAnnotatedClass behaviorClass){
		OJAnnotatedOperation processSignal = (OJAnnotatedOperation) OJUtil.findOperation(behaviorClass, "processSignal");
		if(processSignal == null){
			processSignal = new OJAnnotatedOperation("processSignal", new OJPathName("boolean"));
			processSignal.addParam("signal", new OJPathName(ISignal.class.getName()));
			behaviorClass.addToOperations(processSignal);
			behaviorClass.addToImplementedInterfaces(new OJPathName(IActiveObject.class.getName()));
		}
		return processSignal;
	}
	private void insertSignalCallInProcessSignal(OJAnnotatedOperation processSignal,INakedSignal signal){
		NakedClassifierMap map = new NakedClassifierMap(signal);
		OJIfStatement ifInstance = new OJIfStatement("signal instanceof " + map.javaType());
		processSignal.getOwner().addToImports(map.javaTypePath());
		processSignal.getBody().addToStatements(ifInstance);
		String signalFieldName = signal.getMappingInfo().getJavaName().getDecapped().toString();
		OJAnnotatedField signalField = new OJAnnotatedField(signalFieldName, map.javaTypePath());
		signalField.setInitExp("(" + map.javaType() + ")signal");
		Iterator<INakedProperty> iter = signal.getArgumentParameters().iterator();
		StringBuilder sb = new StringBuilder("return on" + signal.getMappingInfo().getJavaName().getCapped() + "(");
		while(iter.hasNext()){
			sb.append(signalFieldName);
			NakedStructuralFeatureMap featureMap = new NakedStructuralFeatureMap(iter.next());
			sb.append('.');
			sb.append(featureMap.getter());
			sb.append("()");
			if(iter.hasNext()){
				sb.append(',');
			}
		}
		sb.append(')');
		ifInstance.getThenPart().addToStatements(sb.toString());
		ifInstance.getThenPart().addToLocals(signalField);
	}
	private void insertCallToSignalHandler(INakedTriggerContainer behavior,INakedSignal signal){
		if(behavior.getContext() != null){
			// if context==null, the handler has already been implemented
			OJPathName path = OJUtil.classifierPathname(behavior.getContext());
			OJClass myOwner = (OJClass) this.javaModel.findIntfOrCls(path);
			myOwner.addToImports(signal.getMappingInfo().getQualifiedJavaName());
			String signalReception = EventUtil.getEventHandlerName(signal);
			OJAnnotatedOperation ojOperation = (OJAnnotatedOperation) OJUtil.findOperation(myOwner, signalReception);
			if(ojOperation == null){
				ojOperation = new OJAnnotatedOperation(signalReception);
				myOwner.addToOperations(ojOperation);
				for(INakedProperty p:signal.getArgumentParameters()){
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
					ojOperation.addParam(map.umlName(), map.javaTypePath());
				}
				ojOperation.setReturnType(new OJPathName("boolean"));
				ojOperation.getBody().addToStatements("return false");
			}
			OJStatement statement = buildCallFromContextToEventHandlerOnBehavior(behavior, signal, ojOperation);
			List<OJStatement> statements = ojOperation.getBody().getStatements();
			statements.add(statements.size() - 1, statement);
		}
	}
	/**
	 * Implements a method that is to be called when significant events occur. By convention it starts with the word "on" and returns a
	 * boolean indicating whether the event was consumed or not
	 */
	private void implementEventHandler(OJClass activityClass,WaitForEventElements eventActions){
		// TODO split this up for NakedEvents, Deadlines and MessageEvents
		INakedElement event = eventActions.getEvent();
		OJAnnotatedOperation listener = createEventHandlerSignature(activityClass, eventActions, event);
		OJIfStatement ifProcessActive = new OJIfStatement("getProcessInstance()!=null");
		listener.getBody().addToStatements(ifProcessActive);
		OJAnnotatedField nodes = new OJAnnotatedField("waitingNode",UML_NODE_INSTANCE);
		activityClass.addToImports(UML_NODE_INSTANCE);
		ifProcessActive.getThenPart().addToLocals(nodes);
		implementEventHandlerBody(eventActions, event, listener, ifProcessActive);
		listener.getBody().addToStatements("return consumed");
	}
	protected void implementEventHandlerBody(WaitForEventElements eventActions,INakedElement event,OJAnnotatedOperation listener,OJIfStatement ifProcessActive){
		if(event instanceof INakedEvent){
			//previously triggered from a known node
			for(FromNode node:eventActions.getWaitingNodes()){
				consumeNonMessageEvent(listener, ifProcessActive, node);
			}
		}else{
			//Message event
			for(FromNode node:eventActions.getWaitingNodes()){
				consumeMessageEvent(listener, ifProcessActive, node);
			}
		}
	}
	protected void consumeMessageEvent(OJAnnotatedOperation listener,OJIfStatement ifProcessActive,FromNode node){
		OJIfStatement ifTokenFound = new OJIfStatement();
		ifProcessActive.getThenPart().addToStatements(ifTokenFound);
		String literalExpression = listener.getOwner().getName() + "State." + Jbpm5Util.stepLiteralName(node.getWaitingElement());
		ifTokenFound.setCondition("consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(" + literalExpression + ".getId()))" + "!=null");
		implementEventConsumption(listener, node, ifTokenFound);
	}
	protected void consumeNonMessageEvent(OJAnnotatedOperation listener,OJIfStatement ifProcessActive,FromNode node){
		OJIfStatement ifTokenFound = new OJIfStatement();
		ifProcessActive.getThenPart().addToStatements(ifTokenFound);
		ifTokenFound.setCondition("consumed==false && (waitingNode=(UmlNodeInstance)findNodeInstanceByUniqueId(nodeInstanceUniqueId))!=null");
		implementEventConsumption(listener, node, ifTokenFound);
	}
	protected OJAnnotatedOperation createEventHandlerSignature(OJClass activityClass,WaitForEventElements eventActions,INakedElement event){
		String methodName = EventUtil.getEventHandlerName(event);
		OJAnnotatedOperation listener = new OJAnnotatedOperation(methodName);
		activityClass.addToOperations(listener);
		listener.setReturnType(new OJPathName("boolean"));
		OJAnnotatedField processed = new OJAnnotatedField("consumed",new OJPathName("boolean"));
		processed.setInitExp("false");
		listener.getBody().addToLocals(processed);
		for(INakedTypedElement param:(List<? extends INakedTypedElement>) eventActions.getArguments()){
			ClassifierMap map = new NakedClassifierMap(param.getType());
			listener.addParam(param.getMappingInfo().getJavaName().toString(), map.javaTypePath());
		}
		if(event instanceof INakedEvent){
			if(event instanceof INakedDeadline){
				INakedDefinedResponsibility origin = ((INakedDeadline) event).getOrigin();
				OJPathName pn = null;
				if(origin instanceof INakedOperation){
					pn = OJUtil.classifierPathname(((INakedOperation) origin).getMessageStructure());
				}else if(origin instanceof INakedEmbeddedSingleScreenTask){
					pn = OJUtil.classifierPathname(((INakedEmbeddedSingleScreenTask) origin).getMessageStructure());
				}else{
					pn = OJUtil.classifierPathname((INakedEmbeddedScreenFlowTask) origin);
				}
				listener.addParam("triggerDate", new NakedClassifierMap(workspace.getNakedUmlLibrary().getDateType()).javaTypePath());
				listener.addParam("source", pn);
			}else{
				listener.addParam("nodeInstanceUniqueId", new OJPathName("String"));
				if(event instanceof INakedTimeEvent){
					listener.addParam("triggerDate", new NakedClassifierMap(workspace.getNakedUmlLibrary().getDateType()).javaTypePath());
				}
			}
		}
		return listener;
	}
	private void addConsumedFieldIfNecessary(OJAnnotatedOperation listener){
		OJField consumedField = listener.getBody().findLocal("consumed");
		if(consumedField == null){
			consumedField = new OJAnnotatedField("consumed",new OJPathName("boolean"));
			consumedField.setInitExp("false");
			listener.getBody().addToLocals(consumedField);
		}
	}
	protected void implementEventHandling(OJAnnotatedClass ojBehavior,INakedTriggerContainer behavior,Collection<WaitForEventElements> ea){
		addFindWaitingNode(ojBehavior);
		for(WaitForEventElements eventActions:ea){
			implementEventHandler(ojBehavior, eventActions);
		}
		insertEventHandlerCalls(ojBehavior, behavior);
	}
	private void addFindWaitingNode(OJClass activityClass){
		activityClass.addToImports(Jbpm5Util.getNodeInstance());
		OJOperation findWaitingNodeByNodeId = new OJAnnotatedOperation("findWaitingNodeByNodeId");
		activityClass.addToOperations(findWaitingNodeByNodeId);
		findWaitingNodeByNodeId.addParam("step", new OJPathName("long"));
		findWaitingNodeByNodeId.setReturnType(Jbpm5Util.getNodeInstance());
		OJForStatement forNodeInstances = new OJForStatement();
		forNodeInstances.setBody(new OJBlock());
		forNodeInstances.setElemType(Jbpm5Util.getNodeInstance());
		forNodeInstances.setElemName("nodeInstance");
		activityClass.addToImports("java.util.Collection");
		forNodeInstances.setCollection("getNodeInstancesRecursively()");
		findWaitingNodeByNodeId.getBody().addToStatements(forNodeInstances);
		OJIfStatement ifNameEquals = new OJIfStatement("((" + Jbpm5Util.getNode().getLast() + ")nodeInstance.getNode()).getId()==step", "return nodeInstance");
		forNodeInstances.getBody().addToStatements(ifNameEquals);
		findWaitingNodeByNodeId.getBody().addToStatements("return null");
	}
}
