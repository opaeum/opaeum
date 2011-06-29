package net.sf.nakeduml.jbpm5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.NakedStateMap;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.statemachines.INakedRegion;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;
import net.sf.nakeduml.metamodel.statemachines.IRegionOwner;

import org.drools.drools._5._0.process.ActionType;
import org.drools.drools._5._0.process.CompositeType;
import org.drools.drools._5._0.process.ConnectionsType;
import org.drools.drools._5._0.process.DocumentRoot;
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
public class StateMachineFlowStep extends AbstractFlowStep{
	private static final int ARTIFICIAL_START_NODE_ID = 300000;
	private static final int ARTIFICIAL_FORK_ID = 400000;
	private static final int ARTIFICIAL_END_NODE_ID = 500000;
	private static final int ARTIFICIAL_JOIN_ID = 600000;
	@VisitAfter(matchSubclasses = true)
	public void createRoot(INakedStateMachine sm){
		DocumentRoot root = super.createRoot(sm);
		ProcessType process = root.getProcess();
		sourceIdMap = new HashMap<INakedElement,Integer>();
		targetIdMap = new HashMap<INakedElement,Integer>();
		buildStates(sm, process.getNodes().get(0), process.getConnections().get(0));
	}
	public void buildStates(IRegionOwner owner,NodesType nodes,ConnectionsType connections){
		List<INakedRegion> regions = owner.getRegions();
		if(regions.size() == 1){
			populateRegion(nodes, connections, regions.get(0));
		}else{
			int i = 1;
			// Create composite node per region.
			StartType start = ProcessFactory.eINSTANCE.createStartType();
			int startNodeId = owner.getMappingInfo().getNakedUmlId() + ARTIFICIAL_START_NODE_ID;
			setBounds(i, start, startNodeId);
			start.setName("artificial_start");
			nodes.getStart().add(start);
			i++;
			int forkId = owner.getMappingInfo().getNakedUmlId() + ARTIFICIAL_FORK_ID;
			super.addFork(nodes, i, "artificial_fork", forkId);
			createConnection(connections, startNodeId, forkId);
			i++;
			List<Integer> regionIds = new ArrayList<Integer>();
			for(INakedRegion region:regions){
				CompositeType node = createCompositeState(nodes, i, region.getName(), region.getMappingInfo().getNakedUmlId());
				i++;
				populateRegion(node.getNodes().get(0), node.getConnections().get(0), region);
				regionIds.add(region.getMappingInfo().getNakedUmlId());
			}
			int joinId = owner.getMappingInfo().getNakedUmlId() + ARTIFICIAL_JOIN_ID;
			super.addJoin(nodes, i, "artificial_join", joinId);
			i++;
			int endNodeId = owner.getMappingInfo().getNakedUmlId() + ARTIFICIAL_END_NODE_ID;
			addFinalNode(nodes, i, "artificial_end", endNodeId);
			createConnection(connections, joinId, endNodeId);
			i++;
			for(Integer regionId:regionIds){
				createConnection(connections, forkId, regionId);
				createConnection(connections, regionId, joinId);
			}
			// TODO create connection to each region
		}
	}
	private void populateRegion(NodesType nodes,ConnectionsType connections,INakedRegion region){
		List<INakedState> states = region.getStates();
		int i = 1;
		HashMap<SplitType,INakedState> choiceNodes = new HashMap<SplitType,INakedState>();
		for(INakedState state:states){
			i++;
			if(state.getIncoming().size() > 1){
				i = insertArtificialMerge(nodes, connections, i, state);
			}else{
				targetIdMap.put(state, state.getMappingInfo().getNakedUmlId());
			}
			sourceIdMap.put(state, state.getMappingInfo().getNakedUmlId());
			if(state.getKind().isInitial()){
				addStartNode(nodes, i, state);
			}else if(state.getKind().isFinal()){
				i = addFinalNode(nodes, connections, i, state);
			}else if(state.getKind().isFork()){
				// TODO Not really supported, but can be done
			}else if(state.getKind().isJoin()){
				// TODO Not really supported but can be done
			}else if(state.getKind().isChoice()){
				choiceNodes
						.put(super.addChoice(nodes, i, state.getMappingInfo().getPersistentName().toString(), state.getMappingInfo().getNakedUmlId()),
								state);
			}else if(state.getKind().isSimple()){
				addSimpleState(nodes, i, state);
			}else if(state.getKind().isOrthogonal()){
				addCompositeState(nodes, i, state);
			}else if(state.getKind().isComposite()){
				addCompositeState(nodes, i, state);
			}else{
				System.out.println(state.getName() + ":" + state.getKind() + " not supported yet");
				// TODO history, deep history, junction
			}
		}
		for(INakedTransition t:region.getTransitions()){
			if(t.getSource().getContainer() == t.getTarget().getContainer()){
				createConnection(connections, sourceIdMap.get(t.getSource()), targetIdMap.get(t.getTarget()));
			}
		}
		for(Map.Entry<SplitType,INakedState> entry:choiceNodes.entrySet()){
			addConstraintsToSplit(entry.getKey(), entry.getValue().getOutgoing(), false);
		}
	}
	private int insertArtificialMerge(NodesType nodes,ConnectionsType connections,int i,INakedState state){
		int joinId = state.getMappingInfo().getNakedUmlId() + ARTIFICIAL_JOIN_ID;
		super.addMerge(nodes, i, Jbpm5Util.getArtificialJoinName(state), joinId);
		createConnection(connections, joinId, state.getMappingInfo().getNakedUmlId());
		targetIdMap.put(state, joinId);
		i++;
		return i;
	}
	private final int addFinalNode(NodesType nodes,ConnectionsType connections,int i,INakedState state){
		if((state.getContainer().getRegionOwner() instanceof INakedState)){
			addFinalNode(nodes, i, state.getMappingInfo().getPersistentName().getAsIs(), state.getMappingInfo().getNakedUmlId());
		}else{
			StateType node = ProcessFactory.eINSTANCE.createStateType();
			node.setName(state.getMappingInfo().getPersistentName().getAsIs());
			nodes.getState().add(node);
			setBounds(i, node, state.getMappingInfo().getNakedUmlId());
			i++;
			addFinalNode(nodes, i, state.getMappingInfo().getPersistentName() + "_end", state.getMappingInfo().getNakedUmlId()
					+ ARTIFICIAL_END_NODE_ID);
			this.createConnection(connections, state.getMappingInfo().getNakedUmlId(), state.getMappingInfo().getNakedUmlId()
					+ ARTIFICIAL_END_NODE_ID);
		}
		return i;
	}
	private void addCompositeState(NodesType nodes,int i,INakedState state){
		String name = state.getMappingInfo().getPersistentName().toString();
		Integer nakedUmlId = state.getMappingInfo().getNakedUmlId();
		NakedStateMap map = new NakedStateMap(state);
		CompositeType flowState = createCompositeState(nodes, i, name, nakedUmlId);
		if(state.getEntry() != null){
			OnEntryType onEntry = ProcessFactory.eINSTANCE.createOnEntryType();
			flowState.getOnEntry().add(onEntry);
			createAction(map.getOnEntryMethod(), onEntry.getAction());
		}
		if(state.getExit() != null){
			OnExitType onExit = ProcessFactory.eINSTANCE.createOnExitType();
			flowState.getOnExit().add(onExit);
			createAction(map.getOnExitMethod(), onExit.getAction());
		}
		buildStates(state, flowState.getNodes().get(0), flowState.getConnections().get(0));
	}
	private void addSimpleState(NodesType nodes,int i,INakedState state){
		NakedStateMap map = new NakedStateMap(state);
		OnEntryType onEntry = ProcessFactory.eINSTANCE.createOnEntryType();
		OnExitType onExit = ProcessFactory.eINSTANCE.createOnExitType();
		StateType flowState = ProcessFactory.eINSTANCE.createStateType();
		setBounds(i, flowState, state.getMappingInfo().getNakedUmlId());
		flowState.setName(state.getMappingInfo().getPersistentName().toString());
		if(state.getEntry() != null){
			createAction(map.getOnEntryMethod(), onEntry.getAction());
		}
		if(state.getDoActivity() != null){
			createAction(map.getDoActivityMethod(), onEntry.getAction());
		}
		if(state.getExit() != null){
			createAction(map.getOnExitMethod(), onExit.getAction());
		}
		nodes.getState().add(flowState);
		if(state.getTimeTriggerTransitions().size() > 0){
			createAction(map.getRequestEventsMethod(), onEntry.getAction());
			createAction(map.getCancelEventsMethod(), onExit.getAction());
		}
		if(onEntry.getAction().size() > 0){
			flowState.getOnEntry().add(onEntry);
		}
		if(onExit.getAction().size() > 0){
			flowState.getOnExit().add(onExit);
		}
	}
	private ActionType createAction(String methodName,EList<ActionType> action){
		ActionType entryAction = ProcessFactory.eINSTANCE.createActionType();
		action.add(entryAction);
		entryAction.setDialect("mvel");
		entryAction.setType("expression");
		entryAction.setValue("processObject." + methodName + "()");
		return entryAction;
	}
}
