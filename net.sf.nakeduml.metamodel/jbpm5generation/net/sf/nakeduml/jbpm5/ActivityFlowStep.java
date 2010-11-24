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
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.core.INakedElement;

import org.drools.drools._5._0.process.ActionNodeType;
import org.drools.drools._5._0.process.ActionType;
import org.drools.drools._5._0.process.ConnectionType;
import org.drools.drools._5._0.process.ConnectionsType;
import org.drools.drools._5._0.process.DocumentRoot;
import org.drools.drools._5._0.process.ForEachType;
import org.drools.drools._5._0.process.JoinType;
import org.drools.drools._5._0.process.MappingType;
import org.drools.drools._5._0.process.NodesType;
import org.drools.drools._5._0.process.OnEntryType;
import org.drools.drools._5._0.process.OnExitType;
import org.drools.drools._5._0.process.ProcessFactory;
import org.drools.drools._5._0.process.ProcessType;
import org.drools.drools._5._0.process.SplitType;
import org.drools.drools._5._0.process.StartType;
import org.drools.drools._5._0.process.StateType;
import org.drools.drools._5._0.process.SubProcessType;
import org.eclipse.emf.common.util.EList;

@StepDependency(phase = FlowGenerationPhase.class)
public class ActivityFlowStep extends FlowGenerationStep {
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
			populate(container, nodesType, connections);
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
		createAction(new ActionMap(node).doActionMethod(), onEntry.getAction(),true);
		subProcess.getOnEntry().add(onEntry);
		setBounds(i, subProcess);
		nodesType.getSubProcess().add(subProcess);
	}

	private void createConnectionBetweenLastTwoNodes(int i, ConnectionsType connections) {
		ConnectionType startConn = ProcessFactory.eINSTANCE.createConnectionType();
		startConn.setFromType("DROOLS_DEFAULT");
		startConn.setFrom("" + (i - 1));
		startConn.setTo("" + i);
		connections.getConnection().add(startConn);
	}

	private SplitType addSplit(int i, NodesType nodes, INakedActivityNode state, String type) {
		SplitType split = ProcessFactory.eINSTANCE.createSplitType();
		split.setType(type);
		nodes.getSplit().add(split);
		split.setName(state.getMappingInfo().getPersistentName().toString());
		setBounds(i, split);
		return split;
	}

	private int insertArtificialJoin(NodesType nodes, ConnectionsType connections, int i, INakedActivityNode state) {
		addJoin(nodes, i, state, "2");
		ConnectionType connection = ProcessFactory.eINSTANCE.createConnectionType();
		connection.setFrom(i + "");
		connection.setTo((i + 1) + "");
		connections.getConnection().add(connection);
		i++;
		return i;
	}

	public void addJoin(NodesType nodes, int i, INakedActivityNode state, String type) {
		JoinType join = ProcessFactory.eINSTANCE.createJoinType();
		join.setType(type);
		join.setName(BpmUtil.getArtificialJoinName(state));
		setBounds(i, join);
		nodes.getJoin().add(join);
	}

	private void doConstraints(INakedActivityNode node, SplitType split) {
		Set<? extends GuardedFlow> outgoing = node.getAllEffectiveOutgoing();
		addConstaintsToSplit(split, outgoing);
	}

	private final int addFinalNode(NodesType nodes, ConnectionsType connections, int i, INakedActivityNode state) {
		if ((state.getOwnerElement() instanceof INakedStructuredActivityNode)) {
			addFinalNode(i, nodes, state.getMappingInfo().getPersistentName().getAsIs(), false);
		} else {
			StateType node = ProcessFactory.eINSTANCE.createStateType();
			node.setName(state.getMappingInfo().getPersistentName().getAsIs());
			nodes.getState().add(node);
			setBounds(i, node);
			i++;
			addFinalNode(i, nodes, state.getMappingInfo().getPersistentName() + "_end", false);
			this.createConnectionBetweenLastTwoNodes(i, connections);
		}
		return i;
	}

	private void addForEachNode(NodesType nodes, int i, INakedExpansionRegion structuredNode) {
		ForEachType flowState = ProcessFactory.eINSTANCE.createForEachType();
		flowState.setName(structuredNode.getMappingInfo().getPersistentName().toString());
		setBounds(i, flowState);
		INakedExpansionNode inputElement = structuredNode.getInputElement().get(0);
		flowState.setCollectionExpression("processObject." + ActivityUtil.getCollectionExpression(inputElement) + "(context)");
		flowState.setVariableName(inputElement.getName());
		flowState.setWaitForCompletion("true");
		nodes.getForEach().add(flowState);
		flowState.getNodes().add(ProcessFactory.eINSTANCE.createNodesType());
		flowState.getConnections().add(ProcessFactory.eINSTANCE.createConnectionsType());
		populate(structuredNode, flowState.getNodes().get(0), flowState.getConnections().get(0));
	}

	private void populate(ActivityNodeContainer container, NodesType nodesType, ConnectionsType connections) {
		int i = 0;
		HashMap<SplitType, INakedActivityNode> choiceNodes = new HashMap<SplitType, INakedActivityNode>();
		Collection<INakedActivityNode> activityNodes = new HashSet<INakedActivityNode>(container.getActivityNodes());
		Collection<INakedActivityNode> startNodes = getEffectiveStartNodes(container);
		activityNodes.removeAll(container.getStartNodes());// REmove the now
															// redundant initial
															// nodes
		activityNodes.removeAll(startNodes);//
		if (startNodes.size() > 1) {
			INakedElement element = (INakedElement) container;
			i = addArtificialStartNode(nodesType, i, element);
			i = addArtificialForkNode(nodesType, i, element);
			int forkId = i;
			createConnectionBetweenLastTwoNodes(i, connections);
			for (INakedActivityNode sn : startNodes) {
				i = addNode(nodesType, connections, i, choiceNodes, sn);
				ConnectionType connection = ProcessFactory.eINSTANCE.createConnectionType();
				connection.setFrom(forkId + "");
				connection.setTo("" + i);
				connections.getConnection().add(connection);
			}
		} else if (startNodes.size() == 1) {
			INakedActivityNode startNode = startNodes.iterator().next();
			i = addArtificialStartNode(nodesType, i, startNode);
			i = addNode(nodesType, connections, i, choiceNodes, startNode);
			createConnectionBetweenLastTwoNodes(i, connections);
		}
		activityNodes.removeAll(startNodes);
		for (INakedActivityNode node : activityNodes) {
			i = addNode(nodesType, connections, i, choiceNodes, node);
			if (requiresArtificialFinalNode(node)) {
				i++;
				addFinalNode(i, nodesType, "artificialFinalFor" + node.getName(), false);
				createConnectionBetweenLastTwoNodes(i, connections);
			}
		}
		for (INakedActivityEdge t : container.getActivityEdges()) {
			Integer sourceId = sourceIdMap.get(t.getEffectiveSource());
			Integer targetId = targetIdMap.get(t.getEffectiveTarget());
			if (sourceId != null && targetId != null) {
				// Not all nodes are manifest, e.g. initialNodes and some
				// ParameterNodes
				ConnectionType connection = ProcessFactory.eINSTANCE.createConnectionType();
				connection.setFrom(sourceId + "");
				connection.setTo(targetId + "");
				connections.getConnection().add(connection);
			}
		}
		for (Map.Entry<SplitType, INakedActivityNode> entry : choiceNodes.entrySet()) {
			this.doConstraints(entry.getValue(), entry.getKey());
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

	private int addArtificialForkNode(NodesType nodesType, int i, INakedElement element) {
		i++;
		SplitType split = ProcessFactory.eINSTANCE.createSplitType();
		split.setName("artificialForFor" + element.getMappingInfo().getPersistentName().getAsIs());
		split.setType("1");
		setBounds(i, split);
		nodesType.getSplit().add(split);
		return i;
	}

	private int addArtificialStartNode(NodesType nodesType, int i, INakedElement element) {
		i++;
		StartType node1 = ProcessFactory.eINSTANCE.createStartType();
		node1.setName("artificialStartFor" + element.getMappingInfo().getPersistentName().getAsIs());
		setBounds(i, node1);
		nodesType.getStart().add(node1);
		return i;
	}

	private int addNode(NodesType nodesType, ConnectionsType connections, int i, HashMap<SplitType, INakedActivityNode> choiceNodes,
			INakedActivityNode node) {
		if (!(node instanceof INakedPin)) {
			i++;
			targetIdMap.put(node, i);
			if (node.isImplicitJoin()) {
				i = insertArtificialJoin(nodesType, connections, i, node);
			}
			sourceIdMap.put(node, i);
			if (node instanceof INakedControlNode) {
				INakedControlNode controlNode = (INakedControlNode) node;
				if (controlNode.getControlNodeType().isFlowFinalNode()) {
					i = addFinalNode(nodesType, connections, i, node);
				} else if (controlNode.getControlNodeType().isActivityFinalNode()) {
					i = addFinalNode(nodesType, connections, i, node);
				} else if (controlNode.getControlNodeType().isForkNode()) {
					addSplit(i, nodesType, node, "1");
				} else if (controlNode.getControlNodeType().isJoinNode()) {
					addJoin(nodesType, i, node, "1");
				} else if (controlNode.getControlNodeType().isMergeNode()) {
					addJoin(nodesType, i, node, "2");
				} else if (controlNode.getControlNodeType().isDecisionNode()) {
					choiceNodes.put(addSplit(i, nodesType, node, "2"), node);
				} else {
					System.out.println(controlNode.getControlNodeType() + " not supported");
				}
			} else if (node instanceof INakedParameterNode && ((INakedParameterNode) node).getParameter().isResult()) {
				addActionNode(nodesType, i, node);
			} else if (node instanceof INakedExpansionNode && ((INakedExpansionNode) node).isOutputElement()) {
				addActionNode(nodesType, i, node);
			} else if (node instanceof INakedAction) {
				if (node instanceof INakedAcceptEventAction) {
					INakedAcceptEventAction action = (INakedAcceptEventAction) node;
					addWaitState(nodesType, i, action);
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
		}
		return i;
	}

	private void addActionNode(NodesType nodes, int i, INakedActivityNode node) {
		ActionNodeType actionNode = ProcessFactory.eINSTANCE.createActionNodeType();
		setBounds(i, actionNode);
		ActivityNodeMap map = new ActivityNodeMap(node);
		createAction(map.doActionMethod(), actionNode.getAction(),true);
		actionNode.setName(node.getMappingInfo().getPersistentName().getAsIs());
		nodes.getActionNode().add(actionNode);
	}

	private void addWaitState(NodesType nodes, int i, INakedAcceptEventAction action) {
		StateType state = ProcessFactory.eINSTANCE.createStateType();
		ActionMap map = new ActionMap(action);
		setBounds(i, state);
		state.setName(action.getMappingInfo().getPersistentName().toString());
		nodes.getState().add(state);
		if (action.getEvent() instanceof INakedTimeEvent) {
			OnEntryType onEntry = ProcessFactory.eINSTANCE.createOnEntryType();
			state.getOnEntry().add(onEntry);
			OnExitType onExit = ProcessFactory.eINSTANCE.createOnExitType();
			state.getOnExit().add(onExit);
			createAction(map.doActionMethod(), onEntry.getAction(),true);
			createAction(map.getCancelTimersMethod(), onExit.getAction(),false);
		}
	}

	private ActionType createAction(String methodName, EList<ActionType> action, boolean passContext) {
		ActionType entryAction = ProcessFactory.eINSTANCE.createActionType();
		action.add(entryAction);
		entryAction.setDialect("mvel");
		entryAction.setType("expression");
		String string = passContext?"context":"";
		entryAction.setValue("processObject." + methodName + "("+string+")");
		return entryAction;
	}
}
