package org.opaeum.jbpm5;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.drools.drools._5._0.process.ActionNodeType;
import org.drools.drools._5._0.process.ConnectionsType;
import org.drools.drools._5._0.process.DocumentRoot;
import org.drools.drools._5._0.process.EndType;
import org.drools.drools._5._0.process.NodesType;
import org.drools.drools._5._0.process.OnEntryType;
import org.drools.drools._5._0.process.OnExitType;
import org.drools.drools._5._0.process.ProcessFactory;
import org.drools.drools._5._0.process.ProcessType;
import org.drools.drools._5._0.process.SplitType;
import org.drools.drools._5._0.process.StateType;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.javageneration.basicjava.simpleactions.ActivityNodeMap;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.metamodel.actions.INakedAcceptEventAction;
import org.opaeum.metamodel.actions.INakedCallAction;
import org.opaeum.metamodel.actions.INakedCallBehaviorAction;
import org.opaeum.metamodel.actions.INakedCallOperationAction;
import org.opaeum.metamodel.actions.INakedExceptionHandler;
import org.opaeum.metamodel.activities.ActivityKind;
import org.opaeum.metamodel.activities.ActivityNodeContainer;
import org.opaeum.metamodel.activities.ControlNodeType;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedControlNode;
import org.opaeum.metamodel.activities.INakedExpansionNode;
import org.opaeum.metamodel.activities.INakedExpansionRegion;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.INakedParameterNode;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.bpm.INakedEmbeddedTask;
import org.opaeum.metamodel.core.INakedElement;

@StepDependency(phase = FlowGenerationPhase.class)
public class ActivityFlowStep extends AbstractFlowStep{
	private static final int ARTIFICIAL_JOIN_ID = 100000;
	private static final int ARTIFICIAL_FORK_ID = 200000;
	private static final int ARTIFICIAL_START_NODE_ID = 300000;
	private static final int ARTIFICIAL_FINAL_NODE_ID = 400000;
	private static final int ARTIFICIAL_CHOICE_ID = 500000;
	private static final Integer INIT_NODE_ID = 600000;
	@VisitAfter(matchSubclasses = true)
	public void createRoot(INakedActivity a){
		if(a.getActivityKind() != ActivityKind.SIMPLE_SYNCHRONOUS_METHOD){
			populateContainer(a);
		}
	}
	private int insertArtificialJoin(NodesType nodes,ConnectionsType connections,int i,INakedActivityNode state){
		Long joinId = state.getMappingInfo().getOpaeumId() + ARTIFICIAL_JOIN_ID;
		addJoin(nodes, i, Jbpm5Util.getArtificialJoinName(state), joinId);
		createConnection(connections, joinId, state.getMappingInfo().getOpaeumId());
		i++;
		targetIdMap.peek().put(state, joinId);
		return i;
	}
	private final int addFinalNode(NodesType nodes,ConnectionsType connections,int i,INakedControlNode state){
		String name = state.getMappingInfo().getPersistentName().getAsIs();
		Long nakedUmlId = state.getMappingInfo().getOpaeumId();
		EndType addFinalNode = null;
		if((state.getOwnerElement() instanceof INakedStructuredActivityNode)){
			addFinalNode = addFinalNode(nodes, i, name, nakedUmlId);
		}else{
			addActionNode(nodes, i, state);
			i++;
			Long finalNodeId = nakedUmlId + ARTIFICIAL_FINAL_NODE_ID;
			addFinalNode = addFinalNode(nodes, i, state.getMappingInfo().getPersistentName() + "_end", finalNodeId);
			this.createConnection(connections, nakedUmlId, finalNodeId);
		}
		if(state.getControlNodeType().isActivityFinalNode()){
			addFinalNode.setTerminate("true");
		}
		return i;
	}
	private void populateContainer(ActivityNodeContainer container){
		DocumentRoot root = super.createRoot(container);
		ProcessType process = root.getProcess();
		sourceIdMap.push(new HashMap<INakedElement,Long>());
		targetIdMap.push(new HashMap<INakedElement,Long>());
		NodesType nodesType = process.getNodes().get(0);
		ConnectionsType connections = process.getConnections().get(0);
		int i = 1;
		HashMap<SplitType,INakedActivityNode> choiceNodes = new HashMap<SplitType,INakedActivityNode>();
		Collection<INakedActivityNode> activityNodes = new HashSet<INakedActivityNode>(container.getActivityNodes());
		Collection<INakedActivityNode> effectiveStartNodes = getEffectiveStartNodes(container);
		// Remove the now redundant initial nodes
		activityNodes.removeAll(container.getStartNodes());
		// Effective Startnodes will be treated separately
		activityNodes.removeAll(effectiveStartNodes);
		Long startNodeId = container.getMappingInfo().getOpaeumId() + ARTIFICIAL_START_NODE_ID;
		addInitialNode(nodesType, i, "artificial_start_for_" + container.getMappingInfo().getPersistentName().getAsIs(), startNodeId);
		i++;
		if(container instanceof INakedActivity && ((INakedActivity) container).isProcess()){
			ActionNodeType actionNode = ProcessFactory.eINSTANCE.createActionNodeType();
			Long initNodeId = container.getMappingInfo().getOpaeumId() + INIT_NODE_ID;
			setBounds(i, actionNode, initNodeId);
			createAction("init", actionNode.getAction(), true);
			actionNode.setName("init");
			nodesType.getActionNode().add(actionNode);
			i++;
			createConnection(connections, startNodeId, initNodeId);
			startNodeId = initNodeId;
		}
		if(effectiveStartNodes.size() > 1){
			// INsert artificial Fork;
			Long forkId = container.getMappingInfo().getOpaeumId() + ARTIFICIAL_FORK_ID;
			addFork(nodesType, i, Jbpm5Util.getArtificialForkName(container), forkId);
			createConnection(connections, startNodeId, forkId);
			i++;
			startNodeId = forkId;
		}
		// Add connections from fork/startNode
		for(INakedActivityNode effectiveStartNode:effectiveStartNodes){
			i = addNode(nodesType, connections, i, choiceNodes, effectiveStartNode);
			Long targetId = targetIdMap.peek().get(effectiveStartNode);
			createConnection(connections, startNodeId, targetId);
		}
		// Add Nodes
		for(INakedActivityNode node:activityNodes){
			i = addNode(nodesType, connections, i, choiceNodes, node);
		}
		// Add connections
		for(INakedActivityEdge t:container.getActivityEdges()){
			Long sourceId = sourceIdMap.peek().get(t.getEffectiveSource());
			Long targetId = targetIdMap.peek().get(t.getEffectiveTarget());
			if(sourceId != null && targetId != null){
				// Not all nodes manifest in jbpm nodes, e.g. initialNodes and
				// some ParameterNodes
				if(t.getSource() instanceof INakedOutputPin && ((INakedOutputPin) t.getSource()).isException()){
					// Bypass artificial forks and decisions
					createConnection(connections, t.getEffectiveSource().getMappingInfo().getOpaeumId(), targetId);
				}else{
					createConnection(connections, sourceId, targetId);
				}
			}
		}
		for(Map.Entry<SplitType,INakedActivityNode> entry:choiceNodes.entrySet()){
			this.addConstraintsToSplit(entry.getKey(), entry.getValue().getAllEffectiveOutgoing(), true);
		}
	}
	private boolean requiresArtificialFinalNode(INakedActivityNode node){
		boolean isFinalNode = node instanceof INakedControlNode && ((INakedControlNode) node).getControlNodeType().isFinalNode();
		boolean isOutputExpansionNode = node instanceof INakedExpansionNode && ((INakedExpansionNode) node).isOutputElement();
		boolean hasExceptionHandler = node instanceof INakedAction && ((INakedAction) node).getHandlers().size() > 0;
		return (node.getAllEffectiveOutgoing().isEmpty() && !isFinalNode && !hasExceptionHandler) || isOutputExpansionNode;
	}
	private Collection<INakedActivityNode> getEffectiveStartNodes(ActivityNodeContainer container){
		Set<INakedActivityNode> results = new HashSet<INakedActivityNode>();
		Collection<INakedActivityNode> startNodes = container.getStartNodes();
		for(INakedActivityNode sn:startNodes){
			if(isInitialNode(sn)){
				Set<INakedActivityEdge> outging = sn.getAllEffectiveOutgoing();
				for(INakedActivityEdge edge:outging){
					if(dependsOnInitialNode(edge)){
						results.add(edge.getEffectiveTarget());
					}
				}
			}else{
				results.add(sn);
			}
		}
		return results;
	}
	/**
	 * Returns true if this node is dependent on the preceding InitialNode for its activation
	 * 
	 * @param edge
	 * @return
	 */
	private boolean dependsOnInitialNode(INakedActivityEdge edge){
		if(edge.getEffectiveTarget() instanceof INakedControlNode){
			INakedControlNode node = (INakedControlNode) edge.getEffectiveTarget();
			if(node.getControlNodeType() == ControlNodeType.MERGE_NODE){
				return true;
			}
		}
		Set<INakedActivityEdge> in = edge.getEffectiveTarget().getAllEffectiveIncoming();
		for(INakedActivityEdge e:in){
			if(!isInitialNode(e.getEffectiveSource())){
				return false;
			}
		}
		return true;
	}
	private boolean isInitialNode(INakedActivityNode startNode){
		boolean isInitialNode = startNode instanceof INakedControlNode && ((INakedControlNode) startNode).getControlNodeType().isInitialNode();
		boolean isInputParameter = startNode instanceof INakedParameterNode && ((INakedParameterNode) startNode).getParameter().isArgument();
		boolean isInputExpansionNode = startNode instanceof INakedExpansionNode && ((INakedExpansionNode) startNode).isInputElement();
		return isInitialNode || isInputParameter || isInputExpansionNode;
	}
	private int addNode(NodesType nodesType,ConnectionsType connections,int i,HashMap<SplitType,INakedActivityNode> choiceNodes,INakedActivityNode node){
		if(!(node instanceof INakedPin)){
			if(node.isImplicitJoin()){
				i = insertArtificialJoin(nodesType, connections, i, node);
			}else{
				targetIdMap.peek().put(node, node.getMappingInfo().getOpaeumId());
			}
			if(node instanceof INakedControlNode){
				INakedControlNode controlNode = (INakedControlNode) node;
				if(controlNode.getControlNodeType().isFlowFinalNode()){
					i = addFinalNode(nodesType, connections, i, controlNode);
				}else if(controlNode.getControlNodeType().isActivityFinalNode()){
					i = addFinalNode(nodesType, connections, i, controlNode);
				}else if(controlNode.getControlNodeType().isForkNode()){
					addFork(nodesType, i, node.getMappingInfo().getPersistentName().toString(), node.getMappingInfo().getOpaeumId());
				}else if(controlNode.getControlNodeType().isJoinNode()){
					addJoin(nodesType, i, node.getMappingInfo().getPersistentName().getAsIs(), node.getMappingInfo().getOpaeumId());
				}else if(controlNode.getControlNodeType().isMergeNode()){
					addMerge(nodesType, i, node.getMappingInfo().getPersistentName().getAsIs(), node.getMappingInfo().getOpaeumId());
				}else if(controlNode.getControlNodeType().isDecisionNode()){
					choiceNodes.put(addChoice(nodesType, i, node.getMappingInfo().getPersistentName().toString(), node.getMappingInfo().getOpaeumId()), node);
				}else{
					System.out.println(controlNode.getControlNodeType() + " not supported");
				}
			}else if(node instanceof INakedParameterNode && ((INakedParameterNode) node).getParameter().isResult()){
				addActionNode(nodesType, i, node);
			}else if(node instanceof INakedExpansionNode && ((INakedExpansionNode) node).isOutputElement()){
				addActionNode(nodesType, i, node);
			}else if(node instanceof INakedAction){
				INakedAction action = (INakedAction) node;
				if(action instanceof INakedAcceptEventAction){
					addWaitState(nodesType, i, (INakedAcceptEventAction) node);
				}else if(action instanceof INakedCallBehaviorAction && ((INakedCallBehaviorAction) node).getBehavior().isProcess()){
					addWaitState(nodesType, i, (INakedCallAction) node);
				}else if(action instanceof INakedEmbeddedTask){
					addWaitState(nodesType, i, (INakedEmbeddedTask) node);
				}else if(action instanceof INakedCallOperationAction && ((INakedCallOperationAction) node).isLongRunning()){
					addWaitState(nodesType, i, (INakedCallOperationAction) node);
				}else if(action.hasExceptions()){
					addExceptionAwareState(nodesType, i, action);
				}else if(node instanceof INakedExpansionRegion){
					addWaitState(nodesType, i, (INakedExpansionRegion) node);
				}else if(node instanceof INakedStructuredActivityNode){
					populateContainer((INakedStructuredActivityNode) node);
				}else{
					addActionNode(nodesType, i, action);
				}
				Collection<INakedExceptionHandler> handlers = action.getHandlers();
				for(INakedExceptionHandler handler:handlers){
					createConnection(connections, action.getMappingInfo().getOpaeumId(), handler.getHandlerBody().getMappingInfo().getOpaeumId());
				}
			}else{
				System.out.println(node.getName() + ":" + node.getClass().getName() + " not supported yet");
			}
			i++;
			if(node.isImplicitFork()){
				i = insertArtificialFork(nodesType, connections, i, node);
			}else if(requiresArtificialDecision(node)){
				i = insertArtificialChoice(nodesType, choiceNodes, connections, i, node);
			}else{
				sourceIdMap.peek().put(node, node.getMappingInfo().getOpaeumId());
			}
			if(requiresArtificialFinalNode(node)){
				addFinalNode(nodesType, i, "artificialFinalFor" + node.getName(), node.getMappingInfo().getOpaeumId() + ARTIFICIAL_FORK_ID);
				i++;
				createConnection(connections, node.getMappingInfo().getOpaeumId(), node.getMappingInfo().getOpaeumId() + ARTIFICIAL_FORK_ID);
			}
		}
		return i;
	}
	public boolean requiresArtificialDecision(INakedActivityNode node){
		return node.isImplicitDecision();
	}
	private StateType addWaitState(NodesType nodes,int i,INakedAction task){
		StateType state = addState(nodes, i, task.getMappingInfo().getPersistentName().toString(), task.getMappingInfo().getOpaeumId());
		OnEntryType onEntry = ProcessFactory.eINSTANCE.createOnEntryType();
		state.getOnEntry().add(onEntry);
		ActionMap map = new ActionMap(task);
		createAction(map.doActionMethod(), onEntry.getAction(), true);
		return state;
	}
	public void addExceptionAwareState(NodesType nodesType,int i,INakedAction action){
		ActivityNodeMap map = new ActivityNodeMap(action);
		StateType state = addState(nodesType, i, action.getMappingInfo().getPersistentName().getAsIs(), action.getMappingInfo().getOpaeumId());
		OnEntryType onEntry = ProcessFactory.eINSTANCE.createOnEntryType();
		createAction(map.doActionMethod(), onEntry.getAction(), true);
		state.getOnEntry().add(onEntry);
	}
	private int insertArtificialFork(NodesType nodesType,ConnectionsType connections,int i,INakedActivityNode node){
		Long forkId = node.getMappingInfo().getOpaeumId() + ARTIFICIAL_FORK_ID;
		addFork(nodesType, i, Jbpm5Util.getArtificialForkName(node), forkId);
		createConnection(connections, node.getMappingInfo().getOpaeumId(), forkId);
		i++;
		sourceIdMap.peek().put(node, node.getMappingInfo().getOpaeumId() + ARTIFICIAL_FORK_ID);
		return i;
	}
	private int insertArtificialChoice(NodesType nodesType,HashMap<SplitType,INakedActivityNode> choiceNodes,ConnectionsType connections,int i,INakedActivityNode node){
		Long forkId = node.getMappingInfo().getOpaeumId() + ARTIFICIAL_CHOICE_ID;
		SplitType split = addChoice(nodesType, i, Jbpm5Util.getArtificialChoiceName(node), forkId);
		createConnection(connections, node.getMappingInfo().getOpaeumId(), forkId);
		choiceNodes.put(split, node);
		i++;
		sourceIdMap.peek().put(node, node.getMappingInfo().getOpaeumId() + ARTIFICIAL_CHOICE_ID);
		return i;
	}
	private void addActionNode(NodesType nodes,int i,INakedActivityNode node){
		ActionNodeType actionNode = ProcessFactory.eINSTANCE.createActionNodeType();
		setBounds(i, actionNode, node.getMappingInfo().getOpaeumId());
		ActivityNodeMap map = new ActivityNodeMap(node);
		createAction(map.doActionMethod(), actionNode.getAction(), true);
		actionNode.setName(node.getMappingInfo().getPersistentName().getAsIs());
		nodes.getActionNode().add(actionNode);
	}
	private void addWaitState(NodesType nodes,int i,INakedAcceptEventAction action){
		StateType state = addState(nodes, i, action.getMappingInfo().getPersistentName().toString(), action.getMappingInfo().getOpaeumId());
		ActionMap map = new ActionMap(action);
		if(action.requiresEventRequest() && action.getAllEffectiveIncoming().size() > 0){
			OnEntryType onEntry = ProcessFactory.eINSTANCE.createOnEntryType();
			state.getOnEntry().add(onEntry);
			createAction(map.doActionMethod(), onEntry.getAction(), true);
			OnExitType onExit = ProcessFactory.eINSTANCE.createOnExitType();
			state.getOnExit().add(onExit);
			createAction(map.getCancelEventsMethod(), onExit.getAction(), false);
		}
	}
}
