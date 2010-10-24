package net.sf.nakeduml.jbpm5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.jbpm5.BpmUtil;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.statemachines.INakedRegion;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;

import org.drools.drools._5._0.process.ActionType;
import org.drools.drools._5._0.process.CompositeType;
import org.drools.drools._5._0.process.ConnectionType;
import org.drools.drools._5._0.process.ConnectionsType;
import org.drools.drools._5._0.process.DocumentRoot;
import org.drools.drools._5._0.process.EndType;
import org.drools.drools._5._0.process.JoinType;
import org.drools.drools._5._0.process.NodesType;
import org.drools.drools._5._0.process.OnEntryType;
import org.drools.drools._5._0.process.OnExitType;
import org.drools.drools._5._0.process.ProcessFactory;
import org.drools.drools._5._0.process.ProcessType;
import org.drools.drools._5._0.process.SplitType;
import org.drools.drools._5._0.process.StateType;
import org.eclipse.emf.common.util.EList;

@StepDependency(phase = FlowGenerationPhase.class)
public class StateMachineFlowStep extends FlowGenerationStep {
	@VisitAfter(matchSubclasses = true)
	public void createRoot(INakedStateMachine sm) {
		DocumentRoot root = super.createRoot(sm);
		ProcessType process=root.getProcess();
		buildStates(sm.getRegions(), process.getNodes().get(0), process.getConnections().get(0));
	}

	public void buildStates(List<INakedRegion> regions, NodesType nodes, ConnectionsType connections) {
		for (INakedRegion region : regions) {
			List<INakedState> states = region.getStates();
			Map<INakedState, Integer> sourceIdMap = new HashMap<INakedState, Integer>();
			Map<INakedState, Integer> targetIdMap = new HashMap<INakedState, Integer>();
			int i = 0;
			for (INakedState state : states) {
				i++;
				targetIdMap.put(state, i);
				if (state.getIncoming().size() > 1) {
					JoinType join = ProcessFactory.eINSTANCE.createJoinType();
					join.setType("2");
					join.setName(BpmUtil.getArtificialJoinName(state));
					setBounds(i, join);
					nodes.getJoin().add(join);
					ConnectionType connection = ProcessFactory.eINSTANCE.createConnectionType();
					connection.setFrom(i + "");
					connection.setTo((i + 1) + "");
					connections.getConnection().add(connection);
					i++;
				}
				sourceIdMap.put(state, i);
				if (state.getKind().isInitial()) {
					addStartNode(nodes, i, state);
				} else if (state.getKind().isFinal()) {
					addFinalNode(nodes, connections, i, state);
					i++;
				} else if (state.getKind().isFork()) {
					// Not really supported
				} else if (state.getKind().isJoin()) {
					// Not really supported
				} else if (state.getKind().isChoice()) {
					SplitType split = ProcessFactory.eINSTANCE.createSplitType();
					split.setType("2");
					nodes.getSplit().add(split);
					split.setName(state.getMappingInfo().getPersistentName().toString());
					setBounds(i, split);
					// Not really supported
				} else if (state.getKind().isSimple()) {
					addSimpleState(nodes, i, state);
				} else if (state.getKind().isOrthogonal()) {
				} else if (state.getKind().isComposite()) {
					addCompositeState(nodes, i, state);
				} else {
					System.out.println(state.getName() + ":" + state.getKind() + " not supported yet");
				}
			}
			for (INakedTransition t : region.getTransitions()) {
				ConnectionType connection = ProcessFactory.eINSTANCE.createConnectionType();
				connection.setFrom(sourceIdMap.get(t.getSource()) + "");
				connection.setTo(targetIdMap.get(t.getTarget()) + "");
				connections.getConnection().add(connection);
			}
		}
	}

	protected final void addFinalNode(NodesType nodes, ConnectionsType connections, int i, INakedElement state) {
		StateType node = ProcessFactory.eINSTANCE.createStateType();
		node.setName(state.getMappingInfo().getPersistentName().getAsIs());
		nodes.getState().add(node);
		setBounds(i, node);
		EndType endNode = ProcessFactory.eINSTANCE.createEndType();
		endNode.setName(state.getMappingInfo().getPersistentName().getAsIs() + "_end");
		nodes.getEnd().add(endNode);
		setBounds(i + 1, endNode);
		ConnectionType connection = ProcessFactory.eINSTANCE.createConnectionType();
		connection.setFrom(i + "");
		connection.setTo((i + 1) + "");
		connections.getConnection().add(connection);
	}

	private void addCompositeState(NodesType nodes, int i, INakedState state) {
		CompositeType flowState = ProcessFactory.eINSTANCE.createCompositeType();
		flowState.setName(state.getMappingInfo().getPersistentName().toString());
		setBounds(i, flowState);
		if (state.getEntry() != null) {
			OnEntryType onEntry = ProcessFactory.eINSTANCE.createOnEntryType();
			flowState.getOnEntry().add(onEntry);
			buildAction(state, onEntry.getAction(), true);
		}
		if (state.getExit() != null) {
			OnExitType onExit = ProcessFactory.eINSTANCE.createOnExitType();
			flowState.getOnExit().add(onExit);
			buildAction(state, onExit.getAction(), false);
		}
		nodes.getComposite().add(flowState);
		flowState.getConnections().add(ProcessFactory.eINSTANCE.createConnectionsType());
		flowState.getNodes().add(ProcessFactory.eINSTANCE.createNodesType());
		buildStates(state.getRegions(), flowState.getNodes().get(0), flowState.getConnections().get(0));
	}

	private void addSimpleState(NodesType nodes, int i, INakedState state) {
		StateType flowState = ProcessFactory.eINSTANCE.createStateType();
		setBounds(i, flowState);
		flowState.setName(state.getMappingInfo().getPersistentName().toString());
		if (state.getEntry() != null) {
			OnEntryType onEntry = ProcessFactory.eINSTANCE.createOnEntryType();
			flowState.getOnEntry().add(onEntry);
			buildAction(state, onEntry.getAction(), true);
		}
		if (state.getExit() != null) {
			OnExitType onExit = ProcessFactory.eINSTANCE.createOnExitType();
			flowState.getOnExit().add(onExit);
			buildAction(state, onExit.getAction(), false);
		}
		nodes.getState().add(flowState);
	}

	private void buildAction(INakedState state, EList<ActionType> actions, boolean entry) {
		ActionType action = ProcessFactory.eINSTANCE.createActionType();
		actions.add(action);
		action.setDialect("MVEL");
		action.setValue("#{processObject." + (entry ? "onEntryOf" : "onExitOf") + state.getName() + "}");
	}
}
