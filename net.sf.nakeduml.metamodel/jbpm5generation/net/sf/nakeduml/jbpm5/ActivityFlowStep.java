package net.sf.nakeduml.jbpm5;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActionMap;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActivityNodeMap;
import net.sf.nakeduml.javageneration.jbpm5.BpmUtil;
import net.sf.nakeduml.javageneration.jbpm5.activity.ActivityUtil;
import net.sf.nakeduml.javageneration.util.ActionFeatureBridge;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.ActivityNodeContainer;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionRegion;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.activities.INakedStructuredActivityNode;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.core.INakedElement;

import org.drools.drools._5._0.process.ActionNodeType;
import org.drools.drools._5._0.process.ActionType;
import org.drools.drools._5._0.process.ConnectionsType;
import org.drools.drools._5._0.process.DocumentRoot;
import org.drools.drools._5._0.process.ForEachType;
import org.drools.drools._5._0.process.MappingType;
import org.drools.drools._5._0.process.NodesType;
import org.drools.drools._5._0.process.OnEntryType;
import org.drools.drools._5._0.process.OnExitType;
import org.drools.drools._5._0.process.ProcessFactory;
import org.drools.drools._5._0.process.ProcessType;
import org.drools.drools._5._0.process.SplitType;
import org.drools.drools._5._0.process.StateType;
import org.drools.drools._5._0.process.SubProcessType;
import org.eclipse.emf.common.util.EList;

@StepDependency(phase = FlowGenerationPhase.class)
public class ActivityFlowStep extends FlowGenerationStep {
	private static final int ARTIFICIAL_JOIN_ID = 100000;
	private static final int ARTIFICIAL_FORK_ID = 200000;
	private static final int ARTIFICIAL_START_NODE_ID = 300000;
	private static final int ARTIFICIAL_FINAL_NODE_ID = 400000;

	@VisitAfter(matchSubclasses = true)
	public void createRoot(INakedActivity a) {
		if (a.getActivityKind() != ActivityKind.SIMPLE_SYNCHRONOUS_METHOD) {
			DocumentRoot root = super.createRoot(a);
			ProcessType process = root.getProcess();
			sourceIdMap = new HashMap<INakedElement, Integer>();
			targetIdMap = new HashMap<INakedElement, Integer>();
			NodesType nodesType = process.getNodes().get(0);
			ConnectionsType connections = process.getConnections().get(0);
			ActivityNodeContainer container = a;
			populateContainer(container, nodesType, connections);
		}
	}

	private void addSubProcessCall(NodesType nodesType, int i, INakedCallAction node) {
		SubProcessType subProcess = ProcessFactory.eINSTANCE.createSubProcessType();
		subProcess.setName(node.getMappingInfo().getPersistentName().getAsIs());
		MappingType mapping = ProcessFactory.eINSTANCE.createMappingType();
		mapping.setType("in");
		ActionFeatureBridge actionBridge = new ActionFeatureBridge(node);
		if (node.getOwnerElement() instanceof INakedActivity) {
			mapping.setFrom("processObject." + actionBridge.getName());
		} else {
			mapping.setFrom(actionBridge.getName());
		}
		mapping.setTo("processObject");
		subProcess.getMapping().add(mapping);
		subProcess.setWaitForCompletion("true");
		subProcess.setIndependent("false");
		subProcess.setProcessId(BpmUtil.generateProcessName(node.getCalledElement()));
		OnEntryType onEntry = ProcessFactory.eINSTANCE.createOnEntryType();
		createAction(new ActionMap(node).doActionMethod(), onEntry.getAction(), true);
		subProcess.getOnEntry().add(onEntry);
		setBounds(i, subProcess, node.getMappingInfo().getNakedUmlId());
		nodesType.getSubProcess().add(subProcess);
	}

	private int insertArtificialJoin(NodesType nodes, ConnectionsType connections, int i, INakedActivityNode state) {
		int joinId = state.getMappingInfo().getNakedUmlId() + ARTIFICIAL_JOIN_ID;
		addJoin(nodes, i, BpmUtil.getArtificialJoinName(state), joinId);
		createConnection(connections, joinId, state.getMappingInfo().getNakedUmlId());
		i++;
		targetIdMap.put(state, joinId);
		return i;
	}

	private final int addFinalNode(NodesType nodes, ConnectionsType connections, int i, INakedActivityNode state) {
		String name = state.getMappingInfo().getPersistentName().getAsIs();
		Integer nakedUmlId = state.getMappingInfo().getNakedUmlId();
		if ((state.getOwnerElement() instanceof INakedStructuredActivityNode)) {
			addFinalNode(nodes, i, name, nakedUmlId);
		} else {
			addState(nodes, i, name, nakedUmlId);
			i++;
			int finalNodeId = nakedUmlId + ARTIFICIAL_FINAL_NODE_ID;
			addFinalNode(nodes, i, state.getMappingInfo().getPersistentName() + "_end", finalNodeId);
			this.createConnection(connections, nakedUmlId, finalNodeId);
		}
		return i;
	}

	private void addForEachNode(NodesType nodes, int i, INakedExpansionRegion structuredNode) {
		ForEachType flowState = ProcessFactory.eINSTANCE.createForEachType();
		flowState.setName(structuredNode.getMappingInfo().getPersistentName().toString());
		setBounds(i, flowState, structuredNode.getMappingInfo().getNakedUmlId());
		INakedExpansionNode inputElement = structuredNode.getInputElement().get(0);
		flowState.setCollectionExpression("processObject." + ActivityUtil.getCollectionExpression(inputElement) + "(context)");
		flowState.setVariableName(inputElement.getName());
		flowState.setWaitForCompletion("true");
		nodes.getForEach().add(flowState);
		flowState.getNodes().add(ProcessFactory.eINSTANCE.createNodesType());
		flowState.getConnections().add(ProcessFactory.eINSTANCE.createConnectionsType());
		populateContainer(structuredNode, flowState.getNodes().get(0), flowState.getConnections().get(0));
	}

	private void populateContainer(ActivityNodeContainer container, NodesType nodesType, ConnectionsType connections) {
		int i = 1;
		HashMap<SplitType, INakedActivityNode> choiceNodes = new HashMap<SplitType, INakedActivityNode>();
		Collection<INakedActivityNode> activityNodes = new HashSet<INakedActivityNode>(container.getActivityNodes());
		Collection<INakedActivityNode> effectiveStartNodes = getEffectiveStartNodes(container);
		// Remove the now redundant initial nodes
		activityNodes.removeAll(container.getStartNodes());
		// Effective Startnodes will be treated separately
		activityNodes.removeAll(effectiveStartNodes);
		int startNodeId = container.getMappingInfo().getNakedUmlId() + ARTIFICIAL_START_NODE_ID;
		addInitialNode(nodesType, i, "artificialStartFor" + container.getMappingInfo().getPersistentName().getAsIs(), startNodeId);
		i++;
		if (effectiveStartNodes.size() > 1) {
			// INsert artificial Fork;
			int forkId = container.getMappingInfo().getNakedUmlId() + ARTIFICIAL_FORK_ID;
			addFork(nodesType, i, "artificialForkFor" + container.getMappingInfo().getPersistentName().getAsIs(), forkId);
			createConnection(connections, startNodeId, forkId);
			i++;
			startNodeId = forkId;
		}
		// Add connections from fork/startNode
		for (INakedActivityNode effectiveStartNode : effectiveStartNodes) {
			i = addNode(nodesType, connections, i, choiceNodes, effectiveStartNode);
			Integer targetId = targetIdMap.get(effectiveStartNode);
			createConnection(connections, startNodeId, targetId);
		}
		// Add Nodes
		for (INakedActivityNode node : activityNodes) {
			i = addNode(nodesType, connections, i, choiceNodes, node);
		}
		// Add connections
		for (INakedActivityEdge t : container.getActivityEdges()) {
			Integer sourceId = sourceIdMap.get(t.getEffectiveSource());
			Integer targetId = targetIdMap.get(t.getEffectiveTarget());
			if (sourceId != null && targetId != null) {
				// Not all nodes manifest in jbpm nodes, e.g. initialNodes and
				// some ParameterNodes
				createConnection(connections, sourceId, targetId);
			}
		}
		for (Map.Entry<SplitType, INakedActivityNode> entry : choiceNodes.entrySet()) {
			this.addConstraintsToSplit(entry.getKey(), entry.getValue().getAllEffectiveOutgoing());
		}
	}

	private boolean requiresArtificialFinalNode(INakedActivityNode node) {
		boolean isFinalNode = node instanceof INakedControlNode && ((INakedControlNode) node).getControlNodeType().isFinalNode();
		boolean isOutputExpansionNode = node instanceof INakedExpansionNode && ((INakedExpansionNode) node).isOutputElement();
		return (node.getAllEffectiveOutgoing().isEmpty() && !isFinalNode) || isOutputExpansionNode;
	}

	private Collection<INakedActivityNode> getEffectiveStartNodes(ActivityNodeContainer container) {
		Set<INakedActivityNode> results = new HashSet<INakedActivityNode>();
		Collection<INakedActivityNode> startNodes = container.getStartNodes();
		for (INakedActivityNode sn : startNodes) {
			if (isInitialNode(sn)) {
				Set<INakedActivityEdge> outging = sn.getAllEffectiveOutgoing();
				for (INakedActivityEdge edge : outging) {
					results.add(edge.getEffectiveTarget());
				}
			} else {
				results.add(sn);
			}
		}
		return results;
	}

	private boolean isInitialNode(INakedActivityNode startNode) {
		boolean isInitialNode = startNode instanceof INakedControlNode
				&& ((INakedControlNode) startNode).getControlNodeType().isInitialNode();
		boolean isInputParameter = startNode instanceof INakedParameterNode
				&& ((INakedParameterNode) startNode).getParameter().isArgument();
		boolean isInputExpansionNode = startNode instanceof INakedExpansionNode && ((INakedExpansionNode) startNode).isInputElement();
		return isInitialNode || isInputParameter || isInputExpansionNode;
	}

	private int addNode(NodesType nodesType, ConnectionsType connections, int i, HashMap<SplitType, INakedActivityNode> choiceNodes,
			INakedActivityNode node) {
		if (!(node instanceof INakedPin)) {
			if (node.isImplicitJoin()) {
				i = insertArtificialJoin(nodesType, connections, i, node);
			} else {
				targetIdMap.put(node, node.getMappingInfo().getNakedUmlId());
			}
			if (node.isImplicitFork()) {
				i = insertArtificialFork(nodesType, connections, i, node);
			} else {
				sourceIdMap.put(node, node.getMappingInfo().getNakedUmlId());
			}
			if (node instanceof INakedControlNode) {
				INakedControlNode controlNode = (INakedControlNode) node;
				if (controlNode.getControlNodeType().isFlowFinalNode()) {
					i = addFinalNode(nodesType, connections, i, node);
				} else if (controlNode.getControlNodeType().isActivityFinalNode()) {
					i = addFinalNode(nodesType, connections, i, node);
				} else if (controlNode.getControlNodeType().isForkNode()) {
					addFork(nodesType, i, node.getMappingInfo().getPersistentName().toString(), node.getMappingInfo().getNakedUmlId());
				} else if (controlNode.getControlNodeType().isJoinNode()) {
					addJoin(nodesType, i, node.getMappingInfo().getPersistentName().getAsIs(), node.getMappingInfo().getNakedUmlId());
				} else if (controlNode.getControlNodeType().isMergeNode()) {
					addMerge(nodesType, i, node.getMappingInfo().getPersistentName().getAsIs(), node.getMappingInfo().getNakedUmlId());
				} else if (controlNode.getControlNodeType().isDecisionNode()) {
					choiceNodes.put(
							addChoice(nodesType, i, node.getMappingInfo().getPersistentName().toString(), node.getMappingInfo()
									.getNakedUmlId()), node);
				} else {
					System.out.println(controlNode.getControlNodeType() + " not supported");
				}
			} else if (node instanceof INakedParameterNode && ((INakedParameterNode) node).getParameter().isResult()) {
				addActionNode(nodesType, i, node);
			} else if (node instanceof INakedExpansionNode && ((INakedExpansionNode) node).isOutputElement()) {
				addActionNode(nodesType, i, node);
			} else if (node instanceof INakedAction) {
				if (node instanceof INakedAcceptEventAction) {
					addWaitState(nodesType, i, (INakedAcceptEventAction) node);
				} else if (node instanceof INakedCallAction && ((INakedCallAction) node).isProcessCall()) {
					addSubProcessCall(nodesType, i, (INakedCallAction) node);
				} else {
					addActionNode(nodesType, i, (INakedAction) node);
				}
			} else if (node instanceof INakedExpansionRegion) {
				addForEachNode(nodesType, i, (INakedExpansionRegion) node);
			} else {
				System.out.println(node.getName() + ":" + node.getClass().getName() + " not supported yet");
			}
			i++;
			if (requiresArtificialFinalNode(node)) {
				addFinalNode(nodesType, i, "artificialFinalFor" + node.getName(), node.getMappingInfo().getNakedUmlId()
						+ ARTIFICIAL_FORK_ID);
				i++;
				createConnection(connections, node.getMappingInfo().getNakedUmlId(), node.getMappingInfo().getNakedUmlId()
						+ ARTIFICIAL_FORK_ID);
			}
		}
		return i;
	}

	private int insertArtificialFork(NodesType nodesType, ConnectionsType connections, int i, INakedActivityNode node) {
		int forkId = node.getMappingInfo().getNakedUmlId() + ARTIFICIAL_FORK_ID;
		addFork(nodesType, i, node.getMappingInfo().getPersistentName().getAsIs(), forkId);
		createConnection(connections, node.getMappingInfo().getNakedUmlId(), forkId);
		i++;
		sourceIdMap.put(node, node.getMappingInfo().getNakedUmlId() + ARTIFICIAL_FORK_ID);
		return i;
	}

	private void addActionNode(NodesType nodes, int i, INakedActivityNode node) {
		ActionNodeType actionNode = ProcessFactory.eINSTANCE.createActionNodeType();
		setBounds(i, actionNode, node.getMappingInfo().getNakedUmlId());
		ActivityNodeMap map = new ActivityNodeMap(node);
		createAction(map.doActionMethod(), actionNode.getAction(), true);
		actionNode.setName(node.getMappingInfo().getPersistentName().getAsIs());
		nodes.getActionNode().add(actionNode);
	}

	private void addWaitState(NodesType nodes, int i, INakedAcceptEventAction action) {
		StateType state = addState(nodes, i, action.getMappingInfo().getPersistentName().toString(), action.getMappingInfo()
				.getNakedUmlId());
		ActionMap map = new ActionMap(action);
		if (action.getTrigger() != null && action.getTrigger().getEvent() instanceof INakedTimeEvent) {
			OnEntryType onEntry = ProcessFactory.eINSTANCE.createOnEntryType();
			state.getOnEntry().add(onEntry);
			OnExitType onExit = ProcessFactory.eINSTANCE.createOnExitType();
			state.getOnExit().add(onExit);
			createAction(map.doActionMethod(), onEntry.getAction(), true);
			createAction(map.getCancelTimersMethod(), onExit.getAction(), false);
		}
	}

	private ActionType createAction(String methodName, EList<ActionType> action, boolean passContext) {
		ActionType entryAction = ProcessFactory.eINSTANCE.createActionType();
		action.add(entryAction);
		entryAction.setDialect("mvel");
		entryAction.setType("expression");
		String string = passContext ? "context" : "";
		entryAction.setValue("processObject." + methodName + "(" + string + ")");
		return entryAction;
	}
}
