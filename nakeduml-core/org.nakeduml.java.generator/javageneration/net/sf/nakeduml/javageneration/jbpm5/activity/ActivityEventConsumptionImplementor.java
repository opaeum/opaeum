package net.sf.nakeduml.javageneration.jbpm5.activity;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ObjectNodeExpressor;
import net.sf.nakeduml.javageneration.jbpm5.AbstractEventHandlerInserter;
import net.sf.nakeduml.javageneration.jbpm5.FromNode;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.jbpm5.WaitForEventElements;
import net.sf.nakeduml.javageneration.jbpm5.actions.Jbpm5ActionBuilder;
import net.sf.nakeduml.javageneration.jbpm5.actions.Jbpm5ObjectNodeExpressor;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.bpm.INakedAcceptDeadlineAction;
import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTriggerEvent;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.name.NameWrapper;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

@StepDependency(phase = JavaTransformationPhase.class,requires = ActivityProcessImplementor.class,after = ActivityProcessImplementor.class)
public class ActivityEventHandlerInserter extends AbstractEventHandlerInserter{
	private Jbpm5ActionBuilder<INakedActivityNode> actionBuilder;
	@VisitBefore(matchSubclasses = true)
	public void visitActivity(INakedActivity activity){
		if(activity.isProcess()){
			OJAnnotatedClass activityClass = findJavaClass(activity);
			super.implementEventHandling(activityClass, activity, getEventActions(activity));
		}
	}
	protected void implementEventHandlerBody(WaitForEventElements eventActions,INakedElement event,OJAnnotatedOperation listener,OJIfStatement ifProcessActive){
		if(event instanceof INakedDeadline){
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
				implementEventConsumption(listener, node, ifTaskTokenFound);
			}
		}else{
			for(FromNode fromNode:eventActions.getWaitingNodes()){
				INakedAcceptEventAction action = (INakedAcceptEventAction) fromNode.getWaitingElement();
				if(action.getAllEffectiveIncoming().size() > 0){
					if(eventActions.getEvent() instanceof INakedEvent){
						consumeNonMessageEvent(listener, ifProcessActive, fromNode);
					}else{
						consumeMessageEvent(listener, ifProcessActive, fromNode);
					}
				}else{
					listener.getOwner().addToImports(NODE_INSTANCE_CONTAINER);
					String literalExpression = listener.getOwner().getName() + "State." + Jbpm5Util.stepLiteralName(action);
					OJAnnotatedField nodeInstanceContainer = new OJAnnotatedField("nodeInstanceContainer", NODE_INSTANCE_CONTAINER);
					nodeInstanceContainer.setInitExp("null");
					ifProcessActive.getThenPart().addToLocals(nodeInstanceContainer);
					ifProcessActive.getThenPart().addToStatements(NODE.toJavaString() + " node=getNodeForStep(" +literalExpression +")");
					if(action.getOwnerElement() instanceof INakedActivity){
						ifProcessActive.getThenPart().addToStatements("nodeInstanceContainer=(NodeInstanceContainer)getProcessInstance()");
					}else{
						OJForStatement forAllNodeInstances = new OJForStatement("ni", Jbpm5Util.getNodeInstance(), "getNodeInstancesRecursively()");
						ifProcessActive.getThenPart().addToStatements(forAllNodeInstances);
						OJIfStatement ifMatch = new OJIfStatement("ni.getNode().equals(node.getNodeContainer())", "nodeInstanceContainer=(NodeInstanceContainer)ni");
						ifMatch.getThenPart().addToStatements("break");
						forAllNodeInstances.getBody().addToStatements(ifMatch);
					}
					ifProcessActive.getThenPart().addToStatements(
							"waitingNode=(UmlNodeInstance)" + nodeInstanceContainer.getName() + ".getNodeInstance(node)");
					ifProcessActive.getThenPart().addToStatements("waitingNode.trigger(null, org.jbpm.workflow.core.Node.CONNECTION_DEFAULT_TYPE)");
					implementEventConsumption(listener, fromNode, ifProcessActive);
				}
			}
		}
	}
	@Override
	protected void implementEventConsumption(OJOperation operationContext,FromNode fromNode,OJIfStatement ifTokenFound){
		OJBlock block = ifTokenFound.getThenPart();
		INakedAcceptEventAction node = (INakedAcceptEventAction) fromNode.getWaitingElement();
		storeArguments(ifTokenFound, node);
		block = checkWeight(operationContext, block, node);
		if(node.isImplicitFork()){
			block.addToStatements("consumed=true");
			block.addToStatements("waitingNode.flowToNode(\"" + Jbpm5Util.getArtificialForkName(node) + "\")");
		}else if(node.isImplicitDecision()){
			block.addToStatements("consumed=true");
			block.addToStatements("waitingNode.flowToNode(\"" + Jbpm5Util.getArtificialChoiceName(node) + "\")");
		}else{
			GuardedFlow flow = fromNode.getDefaultTransition();
			if(flow != null){
				// default flow/transition
				block.addToStatements("consumed=true");
				getActionBuilder().flowTo(block, ((INakedActivityEdge) flow).getEffectiveTarget());
			}
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
		Jbpm5ObjectNodeExpressor expressor=new Jbpm5ObjectNodeExpressor(getLibrary());
		OJAnnotatedField context = new OJAnnotatedField("context", new OJPathName("org.drools.spi.ProcessContext"));
		context.setInitExp("new org.drools.spi.ProcessContext(org.nakeduml.runtime.environment.Environment.getInstance().getComponent(StatefulKnowledgeSession.class))");
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
				}else if(aea.getTrigger().getEvent() instanceof INakedTimeEvent){
					param = "triggerDate";
				}
				ifTokenFound.getThenPart().addToStatements(expressor.storeResults(pinMap, param,false));
			}else{
				NakedStructuralFeatureMap parmMap= OJUtil.buildStructuralFeatureMap(argument.getActivity(), parm);
				expressor.storeResults(pinMap, parmMap.fieldname(),parmMap.isMany());
			}
		}
	}
	private Collection<WaitForEventElements> getEventActions(INakedActivity activity){
		Map<INakedElement,WaitForEventElements> results = new HashMap<INakedElement,WaitForEventElements>();
		for(INakedActivityNode node:activity.getActivityNodesRecursively()){
			if(node instanceof INakedAcceptEventAction){
				INakedAcceptEventAction action = (INakedAcceptEventAction) node;
				if(action.getTrigger() != null && action.getTrigger().getEvent() != null){
					WaitForEventElements eventActions = results.get(action.getTrigger().getEvent());
					if(eventActions == null){
						eventActions = new WaitForEventElements(action.getTrigger().getEvent());
						results.put(action.getTrigger().getEvent(), eventActions);
					}
					for(INakedActivityEdge flow:action.getAllEffectiveOutgoing()){
						eventActions.addWaitingNode(action, flow, true);
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
