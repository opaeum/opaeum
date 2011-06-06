package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
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
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.core.internal.ArtificialProperty;
import net.sf.nakeduml.metamodel.name.NameWrapper;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
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
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.runtime.domain.AbstractSignal;

public abstract class AbstractEventHandlerInserter extends AbstractJavaProducingVisitor{
	private static final OJPathName NODE_INSTANCE_CONTAINER = new OJPathName("org.jbpm.workflow.instance.NodeInstanceContainer");
	private static final OJPathName NODE_CONTAINER = new OJPathName("org.jbpm.workflow.core.NodeContainer");
	private static final OJPathName NODE = new OJPathName("org.jbpm.workflow.core.Node");
	public static final OJPathName UML_NODE_INSTANCE = new OJPathName("org.nakeduml.runtime.domain.UmlNodeInstance");
	protected abstract void implementEventConsumption(OJOperation operationContext,FromNode node,OJIfStatement ifTokenFound);
	/**
	 * Inserts a call to the eventListener as the last line of code in the body of the triggering operation
	 */
	private void insertCallToOperationHandler(INakedBehavior behavior,INakedOperation nakedOperation){
		OJAnnotatedClass ojOperationOwner = null;
		boolean operationBelongsToContext = behavior.getContext() != null && behavior.getContext().conformsTo(nakedOperation.getOwner());
		if(operationBelongsToContext){
			ojOperationOwner = findJavaClass(behavior.getContext());
		}else if(behavior.conformsTo(nakedOperation.getOwner())){
			ojOperationOwner = findJavaClass(behavior);
		}
		NakedOperationMap map = new NakedOperationMap(nakedOperation);
		OJOperation ojOperation = ojOperationOwner.findOperation(map.javaOperName(), map.javaParamTypePaths());
		if(ojOperation == null){
			// In superclass, but not overridden
			ojOperation = implementInheritedEventMethod(nakedOperation, ojOperationOwner, map);
		}
		OJStatement statement = null;
		if(operationBelongsToContext){
			statement = buildCallFromContextToEventHandlerOnBehavior(behavior, nakedOperation, ojOperation);
		}else{
			// Operation belongs to behavior directly
			statement = new OJSimpleStatement(callToEventHandler(nakedOperation, ojOperation));
		}
		List<OJStatement> statements = ojOperation.getBody().getStatements();
		if(nakedOperation.hasReturnParameter()){
			statements.add(statements.size() - 1, statement);
		}else{
			statements.add(statement);
		}
	}
	private OJOperation implementInheritedEventMethod(INakedOperation nakedOperation,OJAnnotatedClass myOwner,NakedOperationMap map){
		OJOperation ojOperation;
		ojOperation = new OJAnnotatedOperation(map.javaOperName(), map.javaReturnTypePath());
		myOwner.addToOperations(ojOperation);
		StringBuilder sb = new StringBuilder("super." + map.javaOperName() + "(");
		Iterator<? extends INakedParameter> pIter = nakedOperation.getArgumentParameters().iterator();
		if(nakedOperation.isLongRunning()){
			sb.append("context");
			if(pIter.hasNext()){
				sb.append(",");
			}
		}
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
	private OJStatement buildCallFromContextToEventHandlerOnBehavior(INakedBehavior behavior,INakedElement nakedOperation,OJOperation ojOperation){
		OJStatement statement;
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new ArtificialProperty(behavior, getOclEngine().getOclLibrary()));
		if(behavior.isClassifierBehavior()){
			statement = new OJSimpleStatement(map.getter()+ "()." + callToEventHandler(nakedOperation, ojOperation));
		}else{
			OJForStatement forEach = new OJForStatement("behavior", map.javaBaseTypePath(), map.umlName());
			forEach.getBody().addToStatements("behavior." + callToEventHandler(nakedOperation, ojOperation));
			statement = forEach;
		}
		return statement;
	}
	private String callToEventHandler(INakedElement element,OJOperation ojOperation){
		StringBuilder statement = new StringBuilder("on");
		statement.append(element.getMappingInfo().getJavaName().getCapped());
		statement.append("(");
		if(element instanceof INakedOperation && ((INakedOperation) element).isLongRunning()){
			statement.append("_" + element.getMappingInfo().getJavaName());
			// See SpecificationImplementor.visitOperation
		}else{
			Iterator<OJParameter> parms = ojOperation.getParameters().iterator();
			while(parms.hasNext()){
				OJParameter parm = parms.next();
				statement.append(parm.getName());
				if(parms.hasNext()){
					statement.append(",");
				}
			}
		}
		statement.append(")");
		return statement.toString();
	}
	/**
	 * Implements the processSignal method for signals and injects the event listening code for operations
	 * 
	 * @param behaviorClass
	 * @param behavior
	 */
	private void insertEventHandlerCalls(OJAnnotatedClass behaviorClass,INakedTriggerContainer behavior){
		OJAnnotatedOperation processSignal = ensureProcessSignalPresent(behaviorClass);
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
			}else{
				List<OJStatement> toStatements = parentProcessSignal.getBody().getStatements();
				List<OJStatement> fromStatements = processSignal.getBody().getStatements();
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
			processSignal.addParam("signal", new OJPathName(AbstractSignal.class.getName()));
			behaviorClass.addToOperations(processSignal);
		}
		processSignal.setBody(new OJBlock());
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
			String signalReception = "on" + signal.getMappingInfo().getJavaName().getCapped();
			OJOperation ojOperation = OJUtil.findOperation(myOwner, signalReception);
			if(ojOperation == null){
				ojOperation = new OJAnnotatedOperation();
				ojOperation.setName(signalReception);
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
	private void implementEventListener(OJClass activityClass,WaitForEventElements eventActions){
		//TODO split this up for NakedEvents, Deadlines and MessageEvents
		INakedElement event = eventActions.getEvent();
		String methodName = null;
		if(event instanceof INakedEvent){
			methodName = EventUtil.getCallbackMethodName((INakedEvent) event);
		}else{
			methodName = "on" + event.getMappingInfo().getJavaName().getCapped();
		}
		OJAnnotatedOperation listener = new OJAnnotatedOperation();
		listener.putAnnotation(new OJAnnotationValue(new OJPathName("org.nakeduml.annotation.PersistentName"), EventUtil.getEventMethodPersistentName(event)));
		listener.setName(methodName);
		activityClass.addToOperations(listener);
		listener.setReturnType(new OJPathName("boolean"));
		OJAnnotatedField processed = new OJAnnotatedField();
		processed.setName("consumed");
		processed.setType(new OJPathName("boolean"));
		processed.setInitExp("false");
		listener.getBody().addToLocals(processed);
		OJIfStatement ifProcessActive=new OJIfStatement("getProcessInstance().getState() != ProcessInstance.STATE_ACTIVE");
		listener.getBody().addToStatements(ifProcessActive);
		for(INakedTypedElement param:(List<? extends INakedTypedElement>) eventActions.getArguments()){
			ClassifierMap map = new NakedClassifierMap(param.getType());
			listener.addParam(param.getMappingInfo().getJavaName().toString(), map.javaTypePath());
		}
		if(event instanceof INakedEvent){
			if(event instanceof INakedDeadline){
				INakedDefinedResponsibility origin = ((INakedDeadline) event).getOrigin();
				OJPathName pn = null;
				if(origin instanceof INakedOperation){
					pn = OJUtil.classifierPathname(((INakedOperation) origin).getMessageStructure(getOclEngine().getOclLibrary()));
				}else if(origin instanceof INakedEmbeddedSingleScreenTask){
					pn = OJUtil.classifierPathname(((INakedEmbeddedSingleScreenTask) origin).getMessageStructure(getOclEngine().getOclLibrary()));
				}else{
					pn = OJUtil.classifierPathname((INakedEmbeddedScreenFlowTask) origin);
				}
				listener.addParam("source", pn);
				listener.addParam("triggerDate", new NakedClassifierMap(workspace.getMappedTypes().getDateType()).javaTypePath());
			}else{
				listener.addParam("nodeInstanceUniqueId", new OJPathName("String"));
				if(event instanceof INakedTimeEvent){
					listener.addParam("triggerDate", new NakedClassifierMap(workspace.getMappedTypes().getDateType()).javaTypePath());
				}
			}
		}
		OJAnnotatedField nodes = new OJAnnotatedField();
		nodes.setName("waitingNode");
		activityClass.addToImports(UML_NODE_INSTANCE);
		nodes.setType(UML_NODE_INSTANCE);
		ifProcessActive.getThenPart().addToLocals(nodes);
		if(event instanceof INakedDeadline){
			OJIfStatement ifTaskTokenFound = new OJIfStatement();
			ifProcessActive.getThenPart().addToStatements(ifTaskTokenFound);
			ifTaskTokenFound.setCondition("(waitingNode=(UmlNodeInstance)findNodeInstanceByUniqueId(source.getNodeInstanceUniqueId())" + "!=null");
			for(FromNode node:eventActions.getWaitingNodes()){
				listener.getOwner().addToImports(NODE_INSTANCE_CONTAINER);
				NameWrapper targetNodeName = node.getWaitingElement().getMappingInfo().getJavaName().getDecapped();
				OJAnnotatedField nodeInstanceContainer = new OJAnnotatedField( targetNodeName+ "NIC", NODE_INSTANCE_CONTAINER);
				ifTaskTokenFound.getThenPart().addToLocals(nodeInstanceContainer);
				nodeInstanceContainer.setInitExp("(NodeInstanceContainer) waitingNode.getNodeInstanceContainer()");
				listener.getOwner().addToImports(NODE_CONTAINER);
				OJAnnotatedField nodeContainer = new OJAnnotatedField(targetNodeName+"NC", NODE_CONTAINER);
				ifTaskTokenFound.getThenPart().addToLocals(nodeContainer);
				nodeInstanceContainer.setInitExp("(NodeInstanceContainer) "+targetNodeName+"NIC.getNodeInstanceContainer()");
				String literalExpression = listener.getOwner().getName() + "State." + Jbpm5Util.stepLiteralName(node.getWaitingElement());

				listener.getOwner().addToImports(NODE);
				ifTaskTokenFound.getThenPart().addToStatements("nodeInstanceContainer.getNodeInstance(nodeContainer.getNode("+literalExpression+".getId())).trigger(null, Node.CONNECTION_DEFAULT_TYPE)");
			
				implementEventConsumption(listener, node, ifTaskTokenFound);
			}
		}else if(event instanceof INakedEvent){
			for(FromNode node:eventActions.getWaitingNodes()){
				consumeEventIfNodeInstanceIsWaiting(listener, node, ifProcessActive.getThenPart());
			}
		}else{
			for(FromNode node:eventActions.getWaitingNodes()){
				consumeEventIfNodeIsWaiting(listener, node, ifProcessActive.getThenPart());
			}
		}
		listener.getBody().addToStatements("return consumed");
	}
	private void consumeEventIfNodeInstanceIsWaiting(OJAnnotatedOperation listener,FromNode node,OJBlock body){
		OJIfStatement ifTokenFound = new OJIfStatement();
		body.addToStatements(ifTokenFound);
		ifTokenFound.setCondition("consumed==false && (waitingNode=(UmlNodeInstance)findNodeInstanceByUniqueId(nodeInstanceUniqueId)" + "!=null");
		implementEventConsumption(listener, node, ifTokenFound);
	}
	protected void implementEventHandling(OJAnnotatedClass ojBehavior,INakedTriggerContainer behavior,Collection<WaitForEventElements> ea){
		addFindWaitingNode(ojBehavior);
		for(WaitForEventElements eventActions:ea){
			implementEventListener(ojBehavior, eventActions);
		}
		insertEventHandlerCalls(ojBehavior, behavior);
	}
	private void addFindWaitingNode(OJClass activityClass){
		activityClass.addToImports(Jbpm5Util.getNodeInstance());
		OJOperation findWaitingNodeByNodeId = new OJAnnotatedOperation();
		activityClass.addToOperations(findWaitingNodeByNodeId);
		findWaitingNodeByNodeId.setName("findWaitingNodeByNodeId");
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
	private void consumeEventIfNodeIsWaiting(OJOperation operationContext,FromNode node,OJBlock body){
		OJIfStatement ifTokenFound = new OJIfStatement();
		body.addToStatements(ifTokenFound);
		String literalExpression = operationContext.getOwner().getName() + "State." + Jbpm5Util.stepLiteralName(node.getWaitingElement());
		if(node.isRestingNode()){
			ifTokenFound.setCondition("consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(" + literalExpression + ".getId()))" + "!=null");
		}else{
			ifTokenFound.setCondition("consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId("
					+ node.getWaitingElement().getMappingInfo().getNakedUmlId() + "l))" + "!=null");
		}
		implementEventConsumption(operationContext, node, ifTokenFound);
	}
}
