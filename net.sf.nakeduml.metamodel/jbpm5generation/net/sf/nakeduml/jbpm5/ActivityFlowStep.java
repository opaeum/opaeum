package net.sf.nakeduml.jbpm5;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActionMap;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActivityNodeMap;
import net.sf.nakeduml.javageneration.jbpm5.BpmUtil;
import net.sf.nakeduml.javageneration.jbpm5.activity.ActivityUtil;
import net.sf.nakeduml.javageneration.util.ActionFeatureBridge;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
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
import org.drools.drools._5._0.process.EndType;
import org.drools.drools._5._0.process.ForEachType;
import org.drools.drools._5._0.process.JoinType;
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
	@VisitAfter(matchSubclasses = true)
	public void createRoot(INakedActivity a) {
		if (a.getActivityKind() != ActivityKind.SIMPLE_SYNCHRONOUS_METHOD) {
			DocumentRoot root = super.createRoot(a);
			ProcessType process = root.getProcess();
			sourceIdMap = new HashMap<INakedElement, Integer>();
			targetIdMap = new HashMap<INakedElement, Integer>();
			populate(process.getNodes().get(0), process.getConnections().get(0), a.getActivityNodes(), a.getActivityEdges());
		}
	}

	public void populate(NodesType nodesType, ConnectionsType connections, Collection<INakedActivityNode> nodes,
			Collection<INakedActivityEdge> edges) {
		int i = 1;
		HashMap<SplitType, INakedActivityNode> choiceNodes = new HashMap<SplitType, INakedActivityNode>();
		for (INakedActivityNode node : nodes) {
			if (!(node instanceof INakedPin)) {
				i++;
				targetIdMap.put(node, i);
				if (node.isImplicitJoin()) {
					i = insertArtificialJoin(nodesType, connections, i, node);
				}
				sourceIdMap.put(node, i);
				if (node instanceof INakedControlNode) {
					INakedControlNode controlNode = (INakedControlNode) node;
					if (controlNode.getControlNodeType().isInitialNode()) {
						addStartNode(nodesType, i, node);
					} else if (controlNode.getControlNodeType().isFlowFinalNode()) {
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
					}
				} else if (node instanceof INakedParameterNode || node instanceof INakedExpansionNode) {
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
		}
		for (INakedActivityEdge t : edges) {
			ConnectionType connection = ProcessFactory.eINSTANCE.createConnectionType();
			connection.setFrom(sourceIdMap.get(t.getEffectiveSource()) + "");
			connection.setTo(targetIdMap.get(t.getEffectiveTarget()) + "");
			connections.getConnection().add(connection);
		}
		for (Map.Entry<SplitType, INakedActivityNode> entry : choiceNodes.entrySet()) {
			this.doConstraints(entry.getValue(), entry.getKey());
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
		}else{
			mapping.setFrom(actionBridge.getName());
			
		}
		mapping.setTo("processObject");
		subProcess.getMapping().add(mapping);
		subProcess.setWaitForCompletion("true");
		subProcess.setIndependent("false");
		subProcess.setProcessId(BpmUtil.generateProcessName(node.getCalledElement()));
		OnEntryType onEntry = ProcessFactory.eINSTANCE.createOnEntryType();
		createAction(new ActionMap(node).doActionMethod(), onEntry.getAction());
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

	private void addForEachNode(NodesType nodes, int i, INakedExpansionRegion state) {
		ForEachType flowState = ProcessFactory.eINSTANCE.createForEachType();
		flowState.setName(state.getMappingInfo().getPersistentName().toString());
		setBounds(i, flowState);
		INakedExpansionNode inputElement = state.getInputElement().get(0);
		flowState.setCollectionExpression("processObject." + ActivityUtil.getCollectionExpression(inputElement) + "(context)");
		flowState.setVariableName(inputElement.getName());
		flowState.setWaitForCompletion("true");
		nodes.getForEach().add(flowState);
		flowState.getNodes().add(ProcessFactory.eINSTANCE.createNodesType());
		flowState.getConnections().add(ProcessFactory.eINSTANCE.createConnectionsType());
		populate(flowState.getNodes().get(0), flowState.getConnections().get(0), state.getChildren(), state.getActivityEdges());
	}

	private void addActionNode(NodesType nodes, int i, INakedActivityNode node) {
		ActionNodeType actionNode = ProcessFactory.eINSTANCE.createActionNodeType();
		setBounds(i, actionNode);
		ActivityNodeMap map = new ActivityNodeMap(node);
		createAction(map.doActionMethod(), actionNode.getAction());
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
			createAction(map.getFireTimersMethod(), onEntry.getAction());
			createAction(map.getCancelTimersMethod(), onExit.getAction());
		}
	}

	private ActionType createAction(String methodName, EList<ActionType> action) {
		ActionType entryAction = ProcessFactory.eINSTANCE.createActionType();
		action.add(entryAction);
		entryAction.setDialect("mvel");
		entryAction.setType("expression");
		entryAction.setValue("processObject." + methodName + "(context)");
		return entryAction;
	}
}
