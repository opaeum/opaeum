package org.opeum.javageneration.jbpm5.activity;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.OperationAnnotator;
import org.opeum.javageneration.basicjava.SpecificationImplementor;
import org.opeum.javageneration.jbpm5.AbstractEventConsumptionImplementor;
import org.opeum.javageneration.jbpm5.ElementsWaitingForEvent;
import org.opeum.javageneration.jbpm5.EventUtil;
import org.opeum.javageneration.jbpm5.FromNode;
import org.opeum.javageneration.jbpm5.Jbpm5Util;
import org.opeum.javageneration.jbpm5.actions.Jbpm5ActionBuilder;
import org.opeum.javageneration.jbpm5.actions.Jbpm5ObjectNodeExpressor;
import org.opeum.javageneration.maps.NakedOperationMap;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.BehaviorUtil;
import org.opeum.metamodel.actions.INakedAcceptCallAction;
import org.opeum.metamodel.actions.INakedAcceptEventAction;
import org.opeum.metamodel.activities.INakedActivity;
import org.opeum.metamodel.activities.INakedActivityEdge;
import org.opeum.metamodel.activities.INakedActivityNode;
import org.opeum.metamodel.activities.INakedInputPin;
import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.bpm.INakedAcceptDeadlineAction;
import org.opeum.metamodel.bpm.INakedDeadline;
import org.opeum.metamodel.bpm.INakedEmbeddedTask;
import org.opeum.metamodel.commonbehaviors.GuardedFlow;
import org.opeum.metamodel.commonbehaviors.INakedMessageEvent;
import org.opeum.metamodel.commonbehaviors.INakedTimeEvent;
import org.opeum.metamodel.commonbehaviors.INakedTrigger;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedOperation;
import org.opeum.metamodel.core.INakedParameter;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedTypedElement;
import org.opeum.metamodel.name.NameWrapper;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJForStatement;
import org.opeum.java.metamodel.OJIfStatement;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.OJSimpleStatement;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		ActivityProcessImplementor.class,SpecificationImplementor.class
},after = {
		ActivityProcessImplementor.class,SpecificationImplementor.class
})
public class ActivityEventConsumptionImplementor extends AbstractEventConsumptionImplementor{
	private Jbpm5ActionBuilder<INakedActivityNode> actionBuilder;
	@VisitBefore(matchSubclasses = true)
	public void visitActivity(INakedActivity activity){
		if(activity.isProcess()){
			OJAnnotatedClass activityClass = findJavaClass(activity);
			super.implementEventConsumption(activityClass, activity, getEventActions(activity));
			for(INakedActivityNode n:activity.getActivityNodesRecursively()){
				if(n instanceof INakedAcceptCallAction){
					INakedAcceptCallAction acc = (INakedAcceptCallAction) n;
					INakedOperation no = acc.getOperation();
					NakedOperationMap map = new NakedOperationMap(no);
					OJAnnotatedOperation oper = OperationAnnotator.findOrCreateOperation(activity, activityClass, map, true);
					if(oper.getBody().findLocal("consumed") == null){
						OJAnnotatedField consumed = new OJAnnotatedField("consumed", new OJPathName("boolean"));
						consumed.setInitExp("false");
						oper.getBody().addToLocals(consumed);
					}
					
					OJIfStatement ifProcessActive = addIfProcessActiveAndRemoveReturnStatementIfNecessary(activityClass, oper);
					OJIfStatement ifTokenFound = addIfTokenFound(oper, ifProcessActive, acc);
					if(BehaviorUtil.hasExecutionInstance(no)){
						NakedStructuralFeatureMap actionMap = OJUtil.buildStructuralFeatureMap(acc, getLibrary());
						final String EXECUTE_STATEMENT = "executeStatement";
						oper.getBody().removeFromStatements(oper.getBody().findStatementRecursive(EXECUTE_STATEMENT));
						ifTokenFound.getThenPart().addToStatements(actionMap.setter() + "(result)");
					}else{
						for(INakedOutputPin pin:acc.getResult()){
							NakedStructuralFeatureMap pinMap = OJUtil.buildStructuralFeatureMap(pin.getActivity(), pin, true);
							NakedStructuralFeatureMap parameterMap = OJUtil.buildStructuralFeatureMap(pin.getActivity(), pin.getLinkedTypedElement());
							ifTokenFound.getThenPart().addToStatements(pinMap.setter() + "(" + parameterMap.umlName() + ")");
						}
					}
					checkWaitAndFlowToNextNodes(oper, ifTokenFound.getThenPart(), acc);
					if(!BehaviorUtil.hasExecutionInstance(no)){
						for(INakedInputPin pin:acc.getReplyAction().getReplyValues()){
							if(((INakedParameter) pin.getLinkedTypedElement()).isReturn()){
								Jbpm5ObjectNodeExpressor expressor = new Jbpm5ObjectNodeExpressor(getLibrary());
								ifTokenFound.getThenPart().addToStatements(
										"result=" + expressor.expressInputPinOrOutParamOrExpansionNode(ifTokenFound.getThenPart(), pin));
							}
						}
					}
					if(!activity.conformsTo(no.getOwner()) && activity.getContext() != null && activity.getContext().conformsTo(no.getOwner())){
						// TODO find the right activity and delegate to it
					}
				}else if(n instanceof INakedEmbeddedTask){
					INakedEmbeddedTask t = (INakedEmbeddedTask) n;
					for(INakedDeadline d:t.getTaskDefinition().getDeadlines()){
						OJPathName date = OJUtil.classifierPathname(getLibrary().getDateType());
						OJPathName task = OJUtil.classifierPathname(t.getMessageStructure());
						String consumerName = EventUtil.getEventConsumerName(d);
						OJOperation findOperation = activityClass.findOperation(consumerName, Arrays.asList(date, task));
						if(findOperation == null){
							findOperation = new OJAnnotatedOperation(consumerName, new OJPathName("boolean"));
							activityClass.addToOperations(findOperation);
							findOperation.addParam("date", date);
							findOperation.addParam("task", task);
							findOperation.getBody().addToStatements("return false");
						}
					}
				}
			}
		}
	}
	protected void implementEventConsumerBody(ElementsWaitingForEvent eventActions,OJAnnotatedOperation listener,OJIfStatement ifProcessActive){
		if(eventActions.getEvent() instanceof INakedDeadline){
			OJIfStatement ifTaskTokenFound = new OJIfStatement();
			ifProcessActive.getThenPart().addToStatements(ifTaskTokenFound);
			ifTaskTokenFound.setCondition("(waitingNode=(UmlNodeInstance)findNodeInstanceByUniqueId(source.getNodeInstanceUniqueId()))" + "!=null");
			for(FromNode node:eventActions.getWaitingNodes()){
				listener.getOwner().addToImports(NODE_INSTANCE_CONTAINER);
				NameWrapper targetNodeName = node.getWaitingElement().getMappingInfo().getJavaName().getDecapped();
				OJAnnotatedField nodeInstanceContainer = new OJAnnotatedField(targetNodeName + "NIC", NODE_INSTANCE_CONTAINER);
				ifTaskTokenFound.getThenPart().addToLocals(nodeInstanceContainer);
				nodeInstanceContainer.setInitExp("(NodeInstanceContainer) waitingNode.getNodeInstanceContainer()");
				listener.getOwner().addToImports(NODE_CONTAINER);
				OJAnnotatedField nodeContainer = new OJAnnotatedField(targetNodeName + "NC", NODE_CONTAINER);
				ifTaskTokenFound.getThenPart().addToLocals(nodeContainer);
				nodeContainer.setInitExp("(NodeContainer) " + nodeInstanceContainer.getName() + ".getNodeContainer()");
				String literalExpression = listener.getOwner().getName() + "State." + Jbpm5Util.stepLiteralName(node.getWaitingElement());
				ifTaskTokenFound.getThenPart().addToStatements(
						nodeInstanceContainer.getName() + ".getNodeInstance(" + nodeContainer.getName() + ".getNode(" + literalExpression
								+ ".getId())).trigger(null, Node.CONNECTION_DEFAULT_TYPE)");
				consumeEvent(listener, node, ifTaskTokenFound);
			}
		}else{
			for(FromNode fromNode:eventActions.getWaitingNodes()){
				INakedAcceptEventAction action = (INakedAcceptEventAction) fromNode.getWaitingElement();
				if(action.getAllEffectiveIncoming().size() > 0){
					if(eventActions.getEvent() instanceof INakedMessageEvent){
						consumeEventWithoutSourceNodeInstanceUniqueId(listener, ifProcessActive, fromNode);
					}else{
						consumeEventWithSourceNodeInstanceUniqueId(listener, ifProcessActive, fromNode);
					}
				}else{
					listener.getOwner().addToImports(NODE_INSTANCE_CONTAINER);
					String literalExpression = listener.getOwner().getName() + "State." + Jbpm5Util.stepLiteralName(action);
					OJAnnotatedField nodeInstanceContainer = new OJAnnotatedField("nodeInstanceContainer", NODE_INSTANCE_CONTAINER);
					nodeInstanceContainer.setInitExp("null");
					ifProcessActive.getThenPart().addToLocals(nodeInstanceContainer);
					ifProcessActive.getThenPart().addToStatements(NODE.toJavaString() + " node=getNodeForStep(" + literalExpression + ")");
					if(action.getOwnerElement() instanceof INakedActivity){
						ifProcessActive.getThenPart().addToStatements("nodeInstanceContainer=(NodeInstanceContainer)getProcessInstance()");
					}else{
						OJForStatement forAllNodeInstances = new OJForStatement("ni", Jbpm5Util.getNodeInstance(), "getNodeInstancesRecursively()");
						ifProcessActive.getThenPart().addToStatements(forAllNodeInstances);
						OJIfStatement ifMatch = new OJIfStatement("ni.getNode().equals(node.getNodeContainer())", "nodeInstanceContainer=(NodeInstanceContainer)ni");
						ifMatch.getThenPart().addToStatements("break");
						forAllNodeInstances.getBody().addToStatements(ifMatch);
					}
					ifProcessActive.getThenPart().addToStatements("waitingNode=(UmlNodeInstance)" + nodeInstanceContainer.getName() + ".getNodeInstance(node)");
					ifProcessActive.getThenPart().addToStatements("waitingNode.trigger(null, org.jbpm.workflow.core.Node.CONNECTION_DEFAULT_TYPE)");
					consumeEvent(listener, fromNode, ifProcessActive);
				}
			}
		}
	}
	@Override
	protected void consumeEvent(OJOperation operationContext,FromNode fromNode,OJIfStatement ifTokenFound){
		OJBlock block = ifTokenFound.getThenPart();
		INakedAcceptEventAction node = (INakedAcceptEventAction) fromNode.getWaitingElement();
		storeArguments(ifTokenFound, node);
		checkWaitAndFlowToNextNodes(operationContext, block, node);
	}
	protected void checkWaitAndFlowToNextNodes(OJOperation operationContext,OJBlock block,INakedAcceptEventAction node){
		block = checkWeight(operationContext, block, node);
		block.addToStatements("processDirty=consumed=true");
		if(node.isImplicitFork()){
			block.addToStatements("waitingNode.flowToNode(\"" + Jbpm5Util.getArtificialForkName(node) + "\")");
		}else if(node.isImplicitDecision()){
			block.addToStatements("waitingNode.flowToNode(\"" + Jbpm5Util.getArtificialChoiceName(node) + "\")");
		}else if(node.getDefaultOutgoing().size() == 1){
			GuardedFlow flow = node.getDefaultOutgoing().iterator().next();
			// default flow/transition
			getActionBuilder().flowTo(block, ((INakedActivityEdge) flow).getEffectiveTarget());
		}
	}
	private OJBlock checkWeight(OJOperation operationContext,OJBlock block,INakedActivityNode node){
		StringBuilder sb = new StringBuilder();
		// Check if all weights have been satisfied
		// NB!! the weight logic only makes sense on AcceptEventActions. This is the only place where outputpin value count equates to
		// weight. Everywhere else it is impossible to determine weight. In other places it could also lead to stuck contractedProcesses
		// TODO implement validation
		for(INakedActivityEdge edge:node.getDefaultOutgoing()){
			if(edge.getSource() instanceof INakedOutputPin){
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(edge.getActivity(), (INakedOutputPin) edge.getSource());
				if(edge.getWeight() != null){
					if(map.isCollection()){
						IClassifier integerType = getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName);
						if(edge.getWeight() != null){
						}
						String weight = ValueSpecificationUtil.expressValue(operationContext, edge.getWeight(), edge.getSource().getActivity(), integerType);
						if(sb.length() > 0){
							sb.append(" && ");
						}
						sb.append(map.getter() + "().size()>=" + weight);
					}else{
						// would not make sense - ignore
					}
				}
			}
		}
		if(sb.length() > 0){
			OJIfStatement ifStatement = new OJIfStatement();
			block.addToStatements(ifStatement);
			ifStatement.setCondition(sb.toString());
			block = ifStatement.getThenPart();
		}
		return block;
	}
	private void storeArguments(OJIfStatement ifTokenFound,INakedAcceptEventAction aea){
		List<INakedOutputPin> result = aea.getResult();
		Jbpm5ObjectNodeExpressor expressor = new Jbpm5ObjectNodeExpressor(getLibrary());
		OJAnnotatedField context = new OJAnnotatedField("context", new OJPathName("org.drools.spi.ProcessContext"));
		context.setInitExp("new org.drools.spi.ProcessContext(org.opeum.runtime.environment.Environment.getInstance().getComponent(StatefulKnowledgeSession.class))");
		ifTokenFound.getThenPart().addToLocals(context);
		ifTokenFound.getThenPart().addToStatements("((org.drools.spi.ProcessContext)context).setNodeInstance(waitingNode)");
		for(int i = 0;i < result.size();i++){
			INakedOutputPin argument = result.get(i);
			NakedStructuralFeatureMap pinMap = OJUtil.buildStructuralFeatureMap(argument.getActivity(), argument);
			INakedTypedElement parm = argument.getLinkedTypedElement();
			if(parm == null){
				String param = "unknown";
				if(aea instanceof INakedAcceptDeadlineAction){
					param = i == 1 ? "source.getTaskRequest()" : "triggerDate";
				}else if(aea.containsTriggerType(INakedTimeEvent.class)){
					param = "triggerDate";
				}
				ifTokenFound.getThenPart().addToStatements(expressor.storeResults(pinMap, param, false));
			}else{
				NakedStructuralFeatureMap parmMap = OJUtil.buildStructuralFeatureMap(argument.getActivity(), parm);
				String expression = parmMap.fieldname();
				if(parm instanceof INakedProperty){
					// signal
					expression = "signal." + parmMap.getter() + "()";
				}
				ifTokenFound.getThenPart().addToStatements(expressor.storeResults(pinMap, expression, parmMap.isMany()));
			}
		}
	}
	private Collection<ElementsWaitingForEvent> getEventActions(INakedActivity activity){
		Map<INakedElement,ElementsWaitingForEvent> results = new HashMap<INakedElement,ElementsWaitingForEvent>();
		for(INakedActivityNode node:activity.getActivityNodesRecursively()){
			if(node instanceof INakedAcceptEventAction && !(node instanceof INakedAcceptCallAction)){
				INakedAcceptEventAction action = (INakedAcceptEventAction) node;
				for(INakedTrigger trigger:action.getTriggers()){
					if(trigger.getEvent() != null){
						ElementsWaitingForEvent eventActions = results.get(trigger.getEvent());
						if(eventActions == null){
							eventActions = new ElementsWaitingForEvent(trigger.getEvent());
							results.put(trigger.getEvent(), eventActions);
						}
						for(INakedActivityEdge flow:action.getAllEffectiveOutgoing()){
							eventActions.addWaitingNode(action, flow, true);
						}
					}
				}
			}
		}
		return results.values();
	}
	private Jbpm5ActionBuilder<INakedActivityNode> getActionBuilder(){
		if(actionBuilder == null){
			actionBuilder = new Jbpm5ActionBuilder<INakedActivityNode>(getLibrary(), null){
				@Override
				public void implementActionOn(OJAnnotatedOperation oper){
				}
			};
		}
		return actionBuilder;
	}
}
