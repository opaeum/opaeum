package net.sf.nakeduml.jbpm5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.NakedStateMap;
import net.sf.nakeduml.javageneration.jbpm5.BpmUtil;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.statemachines.INakedRegion;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;
import net.sf.nakeduml.metamodel.statemachines.IRegionOwner;

import org.drools.drools._5._0.process.ActionType;
import org.drools.drools._5._0.process.CompositeType;
import org.drools.drools._5._0.process.ConnectionType;
import org.drools.drools._5._0.process.ConnectionsType;
import org.drools.drools._5._0.process.ConstraintType;
import org.drools.drools._5._0.process.ConstraintsType;
import org.drools.drools._5._0.process.DocumentRoot;
import org.drools.drools._5._0.process.EndType;
import org.drools.drools._5._0.process.JoinType;
import org.drools.drools._5._0.process.NodesType;
import org.drools.drools._5._0.process.OnEntryType;
import org.drools.drools._5._0.process.OnExitType;
import org.drools.drools._5._0.process.ProcessFactory;
import org.drools.drools._5._0.process.ProcessType;
import org.drools.drools._5._0.process.SplitType;
import org.drools.drools._5._0.process.StartType;
import org.drools.drools._5._0.process.StateType;
import org.eclipse.emf.common.util.EList;

@StepDependency(phase = FlowGenerationPhase.class)
public class StateMachineFlowStep extends FlowGenerationStep {
	@VisitAfter(matchSubclasses = true)
	public void createRoot(INakedStateMachine sm) {
		DocumentRoot root = super.createRoot(sm);
		ProcessType process = root.getProcess();
		sourceIdMap = new HashMap<INakedElement, Integer>();
		targetIdMap = new HashMap<INakedElement, Integer>();
		buildStates(sm, process.getNodes().get(0), process.getConnections().get(0));
	}

	public void buildStates(IRegionOwner owner, NodesType nodes, ConnectionsType connections) {
		List<INakedRegion> regions = owner.getRegions();
		if (regions.size() == 1) {
			populateRegion(nodes, connections, regions.get(0));
		} else {
			int i = 1;
			// Create composite node per region.
			StartType start = ProcessFactory.eINSTANCE.createStartType();
			setBounds(i, start);
			start.setName("artificial_start");
			nodes.getStart().add(start);
			i++;
			SplitType split = ProcessFactory.eINSTANCE.createSplitType();
			split.setName("artificial_fork");
			setBounds(i, split);
			split.setType("1");
			nodes.getSplit().add(split);
			createConnectionBetweenLastTwoNodes(i, connections);
			i++;
			List<String> regionIds = new ArrayList<String>();
			for (INakedRegion region : regions) {
				CompositeType node = ProcessFactory.eINSTANCE.createCompositeType();
				node.setName(region.getName());
				nodes.getComposite().add(node);
				setBounds(i, node);
				node.setHeight("500");
				node.setWidth("500");
				regionIds.add("" + i);
				NodesType regionNodes = ProcessFactory.eINSTANCE.createNodesType();
				node.getNodes().add(regionNodes);
				ConnectionsType regionConnections = ProcessFactory.eINSTANCE.createConnectionsType();
				node.getConnections().add(regionConnections);
				i++;
				populateRegion(regionNodes, regionConnections, region);
			}
			JoinType join = ProcessFactory.eINSTANCE.createJoinType();
			join.setName("artificial_join");
			setBounds(i, join);
			join.setType("1");
			nodes.getJoin().add(join);
			i++;
			addFinalNode(i, nodes, "artificial_end",false);
			createConnectionBetweenLastTwoNodes(i, connections);
			i++;
			for (String s : regionIds) {
				ConnectionType fromSplit = ProcessFactory.eINSTANCE.createConnectionType();
				fromSplit.setFromType("DROOLS_DEFAULT");
				fromSplit.setFrom(split.getId());
				fromSplit.setTo(s);
				connections.getConnection().add(fromSplit);
				ConnectionType toJoin = ProcessFactory.eINSTANCE.createConnectionType();
				toJoin.setFromType("DROOLS_DEFAULT");
				toJoin.setFrom(s);
				toJoin.setTo(join.getId());
				connections.getConnection().add(toJoin);
			}
			// TODO create connection to each region
		}
	}

	public void createConnectionBetweenLastTwoNodes(int i, ConnectionsType connections) {
		ConnectionType startConn = ProcessFactory.eINSTANCE.createConnectionType();
		startConn.setFromType("DROOLS_DEFAULT");
		startConn.setFrom("" + (i - 1));
		startConn.setTo("" + i);
		connections.getConnection().add(startConn);
	}

	public void populateRegion(NodesType nodes, ConnectionsType connections, INakedRegion region) {
		List<INakedState> states = region.getStates();
		int i = 1;
		HashMap<SplitType, INakedState> choiceNodes = new HashMap<SplitType, INakedState>();
		for (INakedState state : states) {
			i++;
			targetIdMap.put(state, i);
			if (state.getIncoming().size() > 1) {
				i = insertArtificialJoin(nodes, connections, i, state);
			}
			sourceIdMap.put(state, i);
			if (state.getKind().isInitial()) {
				addStartNode(nodes, i, state);
			} else if (state.getKind().isFinal()) {
				addFinalNode(nodes, connections, i, state);
				i++;
			} else if (state.getKind().isFork()) {
				// TODO Not really supported, but can be done
			} else if (state.getKind().isJoin()) {
				// TODO Not really supported but can be done
			} else if (state.getKind().isChoice()) {
				choiceNodes.put(addChoice(i, nodes, state), state);
			} else if (state.getKind().isSimple()) {
				addSimpleState(nodes, i, state);
			} else if (state.getKind().isOrthogonal()) {
				addCompositeState(nodes, i, state);
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
		for (Map.Entry<SplitType, INakedState> entry : choiceNodes.entrySet()) {
			addConstaintsToSplit(entry.getKey(), entry.getValue().getOutgoing());
		}
	}

	private SplitType addChoice(int i, NodesType nodes, INakedState state) {
		SplitType split = ProcessFactory.eINSTANCE.createSplitType();
		split.setType("2");
		nodes.getSplit().add(split);
		split.setName(state.getMappingInfo().getPersistentName().toString());
		setBounds(i, split);
		return split;
	}

	private int insertArtificialJoin(NodesType nodes, ConnectionsType connections, int i, INakedState state) {
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
		return i;
	}

	private final void addFinalNode(NodesType nodes, ConnectionsType connections, int i, INakedState state) {
		if ((state.getContainer().getRegionOwner() instanceof INakedState)) {
			addFinalNode(i, nodes, state.getMappingInfo().getPersistentName().getAsIs(),false);
		} else {
			StateType node = ProcessFactory.eINSTANCE.createStateType();
			node.setName(state.getMappingInfo().getPersistentName().getAsIs());
			nodes.getState().add(node);
			setBounds(i, node);
			i++;
			addFinalNode(i, nodes, state.getMappingInfo().getPersistentName() + "_end",false);
			this.createConnectionBetweenLastTwoNodes(i, connections);
		}
	}


	private void addCompositeState(NodesType nodes, int i, INakedState state) {
		NakedStateMap map = new NakedStateMap(state);
		CompositeType flowState = ProcessFactory.eINSTANCE.createCompositeType();
		flowState.setName(state.getMappingInfo().getPersistentName().toString());
		setBounds(i, flowState);
		if (state.getEntry() != null) {
			OnEntryType onEntry = ProcessFactory.eINSTANCE.createOnEntryType();
			flowState.getOnEntry().add(onEntry);
			createAction(map.getOnEntryMethod(), onEntry.getAction());
		}
		if (state.getExit() != null) {
			OnExitType onExit = ProcessFactory.eINSTANCE.createOnExitType();
			flowState.getOnExit().add(onExit);
			createAction(map.getOnExitMethod(), onExit.getAction());
		}
		nodes.getComposite().add(flowState);
		flowState.getNodes().add(ProcessFactory.eINSTANCE.createNodesType());
		flowState.getConnections().add(ProcessFactory.eINSTANCE.createConnectionsType());
		buildStates(state, flowState.getNodes().get(0), flowState.getConnections().get(0));
	}

	private void addSimpleState(NodesType nodes, int i, INakedState state) {
		NakedStateMap map = new NakedStateMap(state);
		OnEntryType onEntry = ProcessFactory.eINSTANCE.createOnEntryType();
		OnExitType onExit = ProcessFactory.eINSTANCE.createOnExitType();
		StateType flowState = ProcessFactory.eINSTANCE.createStateType();
		setBounds(i, flowState);
		flowState.setName(state.getMappingInfo().getPersistentName().toString());
		if (state.getEntry() != null) {
			createAction(map.getOnEntryMethod(), onEntry.getAction());
		}
		if (state.getDoActivity() != null) {
			createAction(map.getDoActivityMethod(), onEntry.getAction());
		}
		if (state.getExit() != null) {
			createAction(map.getOnExitMethod(), onExit.getAction());
		}
		nodes.getState().add(flowState);
		if (state.getTimeTriggerTransitions().size() > 0) {
			createAction(map.getFireTimersMethod(), onEntry.getAction());
			createAction(map.getCancelTimersMethod(), onExit.getAction());
		}
		if (onEntry.getAction().size() > 0) {
			flowState.getOnEntry().add(onEntry);
		}
		if (onExit.getAction().size() > 0) {
			flowState.getOnExit().add(onExit);
		}
	}

	private ActionType createAction(String methodName, EList<ActionType> action) {
		ActionType entryAction = ProcessFactory.eINSTANCE.createActionType();
		action.add(entryAction);
		entryAction.setDialect("mvel");
		entryAction.setType("expression");
		entryAction.setValue("processObject." + methodName + "()");
		return entryAction;
	}
}
