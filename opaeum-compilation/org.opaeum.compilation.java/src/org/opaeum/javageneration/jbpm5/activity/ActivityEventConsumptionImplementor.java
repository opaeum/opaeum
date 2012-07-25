package org.opaeum.javageneration.jbpm5.activity;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.MessageEvent;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.basicjava.SpecificationImplementor;
import org.opaeum.javageneration.jbpm5.AbstractEventConsumptionImplementor;
import org.opaeum.javageneration.jbpm5.ElementsWaitingForEvent;
import org.opaeum.javageneration.jbpm5.EventUtil;
import org.opaeum.javageneration.jbpm5.FromNode;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.jbpm5.actions.Jbpm5ActionBuilder;
import org.opaeum.javageneration.jbpm5.actions.Jbpm5ObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedOperationMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.name.NameConverter;

@StepDependency(phase = JavaTransformationPhase.class,requires = {ActivityProcessImplementor.class,SpecificationImplementor.class},after = {
		ActivityProcessImplementor.class,SpecificationImplementor.class})
public class ActivityEventConsumptionImplementor extends AbstractEventConsumptionImplementor{
	private Jbpm5ActionBuilder<ActivityNode> actionBuilder;
	@VisitBefore(matchSubclasses = true)
	public void visitActivity(Activity activity){
		if(EmfBehaviorUtil.isProcess( activity)){
			OJAnnotatedClass activityClass = findJavaClass(activity);
			super.implementEventConsumption(activityClass, activity, getEventActions(activity));
			for(ActivityNode n:EmfActivityUtil.getActivityNodesRecursively( activity)){
				if(n instanceof AcceptCallAction){
					AcceptCallAction acc = (AcceptCallAction) n;
					Operation no = EmfActionUtil.getOperation( acc);
					NakedOperationMap map = OJUtil.buildOperationMap(no);
					OJAnnotatedOperation oper = OperationAnnotator.findOrCreateOperation(activity, activityClass, map, true);
					if(oper.getBody().findLocal("consumed") == null){
						OJAnnotatedField consumed = new OJAnnotatedField("consumed", new OJPathName("boolean"));
						consumed.setInitExp("false");
						oper.getBody().addToLocals(consumed);
					}
					
					OJIfStatement ifProcessActive = addIfProcessActiveAndRemoveReturnStatementIfNecessary(activityClass, oper);
					OJIfStatement ifTokenFound = addIfTokenFound(oper, ifProcessActive, acc);
					if(EmfBehaviorUtil.hasExecutionInstance(no)){
						NakedStructuralFeatureMap actionMap = OJUtil.buildStructuralFeatureMap(acc, getLibrary());
						final String EXECUTE_STATEMENT = "executeStatement";
						oper.getBody().removeFromStatements(oper.getBody().findStatementRecursive(EXECUTE_STATEMENT));
						ifTokenFound.getThenPart().addToStatements(actionMap.setter() + "(result)");
					}else{
						for(OutputPin pin:acc.getResults()){
							NakedStructuralFeatureMap pinMap = OJUtil.buildStructuralFeatureMap(activity, pin, true);
							NakedStructuralFeatureMap parameterMap = OJUtil.buildStructuralFeatureMap(activity, EmfActionUtil.getLinkedTypedElement( pin));
							ifTokenFound.getThenPart().addToStatements(pinMap.setter() + "(" + parameterMap.fieldname() + ")");
						}
					}
					checkWaitAndFlowToNextNodes(oper, ifTokenFound.getThenPart(), acc);
					if(!EmfBehaviorUtil.hasExecutionInstance(no)){
						for(InputPin pin:EmfBehaviorUtil.getReplyAction( acc).getReplyValues()){
							if(((Parameter)  EmfActionUtil.getLinkedTypedElement( pin)).getDirection()==ParameterDirectionKind.RETURN_LITERAL){
								Jbpm5ObjectNodeExpressor expressor = new Jbpm5ObjectNodeExpressor(getLibrary());
								ifTokenFound.getThenPart().addToStatements(
										"result=" + expressor.expressInputPinOrOutParamOrExpansionNode(ifTokenFound.getThenPart(), pin));
							}
						}
					}
					if(!activity.conformsTo((Classifier) no.getOwner()) && activity.getContext() != null && activity.getContext().conformsTo((Classifier) no.getOwner())){
						// TODO find the right activity and delegate to it
					}
				}else if(EmfActionUtil.isEmbeddedTask(n)){
					Action t = (Action) n;
					String stereotypeName=n instanceof OpaqueAction?StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK:StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK;
					for(Deadline d:EmfActionUtil. getTaskDefinition( t,stereotypeName).getDeadlines()){
						OJPathName date = OJUtil.classifierPathname(getLibrary().getDateType());
						OJPathName task = OJUtil.classifierPathname(getLibrary().getMessageStructure( t));
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
		if(eventActions.getEvent() instanceof Deadline){
			OJIfStatement ifTaskTokenFound = new OJIfStatement();
			ifProcessActive.getThenPart().addToStatements(ifTaskTokenFound);
			ifTaskTokenFound.setCondition("(waitingNode=(UmlNodeInstance)findNodeInstanceByUniqueId(source.getNodeInstanceUniqueId()))"
					+ "!=null");
			for(FromNode node:eventActions.getWaitingNodes()){
				listener.getOwner().addToImports(NODE_INSTANCE_CONTAINER);
				String targetNodeName = NameConverter.decapitalize(node.getWaitingElement().getName());
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
								+ ".getId())).trigger(null, NodeImpl.CONNECTION_DEFAULT_TYPE)");
				consumeEvent(listener, node, ifTaskTokenFound);
			}
		}else{
			for(FromNode fromNode:eventActions.getWaitingNodes()){
				AcceptEventAction action = (AcceptEventAction) fromNode.getWaitingElement();
				if(EmfActivityUtil.getAllEffectiveIncoming(action).size() > 0){
					if(eventActions.getEvent() instanceof MessageEvent){
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
					if(EmfActivityUtil.getContainingActivity(action) != null){
						ifProcessActive.getThenPart().addToStatements("nodeInstanceContainer=(NodeInstanceContainer)getProcessInstance()");
					}else{
						OJForStatement forAllNodeInstances = new OJForStatement("ni", Jbpm5Util.getNodeInstance(), "getNodeInstancesRecursively()");
						ifProcessActive.getThenPart().addToStatements(forAllNodeInstances);
						OJIfStatement ifMatch = new OJIfStatement("ni.getNode().equals(node.getNodeContainer())",
								"nodeInstanceContainer=(NodeInstanceContainer)ni");
						ifMatch.getThenPart().addToStatements("break");
						forAllNodeInstances.getBody().addToStatements(ifMatch);
					}
					ifProcessActive.getThenPart().addToStatements(
							"waitingNode=(UmlNodeInstance)" + nodeInstanceContainer.getName() + ".getNodeInstance(node)");
					ifProcessActive.getThenPart().addToStatements("waitingNode.trigger(null, org.jbpm.workflow.core.Node.CONNECTION_DEFAULT_TYPE)");
					consumeEvent(listener, fromNode, ifProcessActive);
				}
			}
		}
	}
	@Override
	protected void consumeEvent(OJOperation operationContext,FromNode fromNode,OJIfStatement ifTokenFound){
		OJBlock block = ifTokenFound.getThenPart();
		AcceptEventAction node = (AcceptEventAction) fromNode.getWaitingElement();
		storeArguments(ifTokenFound, node);
		checkWaitAndFlowToNextNodes(operationContext, block, node);
	}
	protected void checkWaitAndFlowToNextNodes(OJOperation operationContext,OJBlock block,AcceptEventAction node){
		block = checkWeight(operationContext, block, node);
		block.addToStatements("processDirty=consumed=true");
		if(EmfActivityUtil.isImplicitFork( node)){
			block.addToStatements("waitingNode.flowToNode(\"" + Jbpm5Util.getArtificialForkName(node) + "\")");
		}else if(EmfActivityUtil.isImplicitDecision( node)){
			block.addToStatements("waitingNode.flowToNode(\"" + Jbpm5Util.getArtificialChoiceName(node) + "\")");
		}else if(EmfActivityUtil.getDefaultOutgoing(node).size() == 1){
		 ActivityEdge flow = EmfActivityUtil.getDefaultOutgoing( node).iterator().next();
			// default flow/transition
			getActionBuilder().flowTo(block, EmfActivityUtil.getEffectiveTarget(((ActivityEdge) flow)));
		}
	}
	private OJBlock checkWeight(OJOperation operationContext,OJBlock block,ActivityNode node){
		StringBuilder sb = new StringBuilder();
		// Check if all weights have been satisfied
		// NB!! the weight logic only makes sense on AcceptEventActions. This is the only place where outputpin value count equates to
		// weight. Everywhere else it is impossible to determine weight. In other places it could also lead to stuck contractedProcesses
		// TODO implement validation
		for(ActivityEdge edge:EmfActivityUtil.getDefaultOutgoing( node)){
			if(edge.getSource() instanceof OutputPin){
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(EmfActivityUtil.getContainingActivity(edge),
						(OutputPin) edge.getSource(), true);
				if(edge.getWeight() != null){
					if(map.isCollection()){
						Classifier integerType = getLibrary().getIntegerType();
						if(edge.getWeight() != null){
						}
						String weight = valueSpecificationUtil.expressValue(operationContext, edge.getWeight(),
								EmfActivityUtil.getContainingActivity(edge.getSource()), integerType);
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
	private void storeArguments(OJIfStatement ifTokenFound,AcceptEventAction aea){
		List<OutputPin> result = aea.getResults();
		Jbpm5ObjectNodeExpressor expressor = new Jbpm5ObjectNodeExpressor(getLibrary());
		OJAnnotatedField context = new OJAnnotatedField("context", Jbpm5Util.getProcessContext());
		context
				.setInitExp("new org.drools.spi.ProcessContext(org.opaeum.runtime.environment.Environment.getInstance().getComponent(StatefulKnowledgeSession.class))");
		ifTokenFound.getThenPart().addToLocals(context);
		ifTokenFound.getThenPart().addToStatements("((org.drools.spi.ProcessContext)context).setNodeInstance(waitingNode)");
		for(int i = 0;i < result.size();i++){
			OutputPin argument = result.get(i);
			NakedStructuralFeatureMap pinMap = OJUtil.buildStructuralFeatureMap(EmfActivityUtil.getContainingActivity(argument), argument, true);
			TypedElement parm = EmfActionUtil.getLinkedTypedElement(argument);
			if(parm == null){
				String param = "unknown";
				if(aea instanceof AcceptDeadlineAction){
					param = i == 1 ? "source.getTaskRequest()" : "triggerDate";
				}else if(aea.containsTriggerType(TimeEvent.class)){
					param = "triggerDate";
				}
				ifTokenFound.getThenPart().addToStatements(expressor.storeResults(pinMap, param, false));
			}else{
				NakedStructuralFeatureMap parmMap = OJUtil.buildStructuralFeatureMap(EmfActivityUtil.getContainingActivity(argument), parm);
				String expression = parmMap.fieldname();
				if(parm instanceof Property){
					// signal
					expression = "signal." + parmMap.getter() + "()";
				}
				ifTokenFound.getThenPart().addToStatements(expressor.storeResults(pinMap, expression, parmMap.isMany()));
			}
		}
	}
	private Collection<ElementsWaitingForEvent> getEventActions(Activity activity){
		Map<Element,ElementsWaitingForEvent> results = new HashMap<Element,ElementsWaitingForEvent>();
		for(ActivityNode node:EmfActivityUtil.getActivityNodesRecursively(activity)){
			if(node instanceof AcceptEventAction && !(node instanceof AcceptCallAction)){
				AcceptEventAction action = (AcceptEventAction) node;
				for(Trigger trigger:action.getTriggers()){
					if(trigger.getEvent() != null){
						ElementsWaitingForEvent eventActions = results.get(trigger.getEvent());
						if(eventActions == null){
							eventActions = new ElementsWaitingForEvent(trigger.getEvent());
							results.put(trigger.getEvent(), eventActions);
						}
						for(ActivityEdge flow:EmfActivityUtil.getAllEffectiveOutgoing(action)){
							eventActions.addWaitingNode(action, flow, true);
						}
					}
				}
			}
		}
		return results.values();
	}
	@Override
	public void release(){
		super.release();
		actionBuilder = null;
	}
	private Jbpm5ActionBuilder<ActivityNode> getActionBuilder(){
		if(actionBuilder == null){
			actionBuilder = new Jbpm5ActionBuilder<ActivityNode>(getLibrary(), null){
				@Override
				public void implementActionOn(OJAnnotatedOperation oper){
				}
			};
		}
		return actionBuilder;
	}
}
