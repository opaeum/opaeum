package net.sf.nakeduml.jbpm5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.NakedStateMap;
import net.sf.nakeduml.javageneration.jbpm5.EventUtil;
import net.sf.nakeduml.linkage.StateMachineUtil;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.statemachines.INakedRegion;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.metamodel.statemachines.IRegionOwner;

import org.drools.drools._5._0.process.ActionNodeType;
import org.drools.drools._5._0.process.CompositeType;
import org.drools.drools._5._0.process.ConnectionsType;
import org.drools.drools._5._0.process.DocumentRoot;
import org.drools.drools._5._0.process.DynamicType;
import org.drools.drools._5._0.process.NodesType;
import org.drools.drools._5._0.process.OnEntryType;
import org.drools.drools._5._0.process.OnExitType;
import org.drools.drools._5._0.process.ProcessFactory;
import org.drools.drools._5._0.process.ProcessType;
import org.drools.drools._5._0.process.StartType;
import org.drools.drools._5._0.process.StateType;
import org.eclipse.emf.common.util.EList;

@StepDependency(phase = FlowGenerationPhase.class)
public class StateMachineFlowStep extends AbstractFlowStep{
	private static final int ARTIFICIAL_START_NODE_ID = 300000;
	private static final int ARTIFICIAL_FORK_ID = 400000;
	private static final int ARTIFICIAL_END_NODE_ID = 500000;
	private static final int ARTIFICIAL_JOIN_ID = 600000;
	private static final int ON_COMPLETION_NODE_ID = 700000;
	private static final int INIT_NODE_ID = 800000;
	@VisitAfter(matchSubclasses = true)
	public void createRoot(INakedStateMachine sm){
		DocumentRoot root = super.createRoot(sm);
		ProcessType process = root.getProcess();
		sourceIdMap = new HashMap<INakedElement,Integer>();
		targetIdMap = new HashMap<INakedElement,Integer>();
		addRegions(sm, process.getNodes().get(0), process.getConnections().get(0));
	}
	public void addRegions(IRegionOwner owner,NodesType nodes,ConnectionsType connections){
		List<INakedRegion> regions = owner.getRegions();
		int i = 1;
		// Create composite node per region.
		StartType start = ProcessFactory.eINSTANCE.createStartType();
		int startNodeId = owner.getMappingInfo().getNakedUmlId() + ARTIFICIAL_START_NODE_ID;
		start.setName("artificial_start");
		nodes.getStart().add(start);
		int from = startNodeId;
		setBounds(i, start, startNodeId);
		if(owner instanceof INakedStateMachine){
			addInitNode(nodes, i,(INakedStateMachine) owner );
			createConnection(connections, startNodeId, owner.getMappingInfo().getNakedUmlId() + INIT_NODE_ID);
			startNodeId=owner.getMappingInfo().getNakedUmlId() + INIT_NODE_ID;
			from=startNodeId;
		}
		if(regions.size() > 1){
			i++;
			from = owner.getMappingInfo().getNakedUmlId() + ARTIFICIAL_FORK_ID;
			super.addFork(nodes, i, "artificial_fork", from);
			createConnection(connections, startNodeId, from);
		}
		i++;
		List<Integer> regionIds = new ArrayList<Integer>();
		for(INakedRegion region:regions){
			DynamicType node = createDynamicState(nodes, i, region.getName(), region.getMappingInfo().getNakedUmlId());
			i++;
			populateRegion(node.getNodes().get(0), node.getConnections().get(0), region);
			regionIds.add(region.getMappingInfo().getNakedUmlId());
		}
		int endNodeId = owner.getMappingInfo().getNakedUmlId() + ARTIFICIAL_END_NODE_ID;
		addFinalNode(nodes, i, "artificial_end", endNodeId);
		i++;
		if(owner instanceof INakedState && ((INakedState) owner).getCompletionTransitions().size() > 0){
			int id = owner.getMappingInfo().getNakedUmlId() + ON_COMPLETION_NODE_ID;
			StateType state = super.addState(nodes, i, "on_completion_of_" + owner.getName(), id);
			state.getOnEntry().add(ProcessFactory.eINSTANCE.createOnEntryType());
			createAction(EventUtil.getEventConsumerName(((INakedState) owner).getCompletionEvent()), state.getOnEntry().get(0).getAction(),false);
			createConnection(connections, id, endNodeId);
			endNodeId = id;
			i++;
		}
		int to = endNodeId;
		if(regions.size() > 1){
			to = owner.getMappingInfo().getNakedUmlId() + ARTIFICIAL_JOIN_ID;
			super.addJoin(nodes, i, "artificial_join", to);
			createConnection(connections, to, endNodeId);
			i++;
		}
		for(Integer regionId:regionIds){
			createConnection(connections, from, regionId);
			createConnection(connections, regionId, to);
		}
		// TODO create connection to each region
	}
	private void populateRegion(NodesType nodes,ConnectionsType connections,INakedRegion region){
		List<INakedState> states = new ArrayList<INakedState>(region.getStates());
		int i = 1;
		for(INakedState state:states){
			i++;
			if(state.getKind().isInitial() || state.getKind().isDeepHistory() || state.getKind().isShallowHistory()){
				addInitialNode(nodes, i, "start", state.getMappingInfo().getNakedUmlId() + ARTIFICIAL_START_NODE_ID);
				i++;
				addSimpleState(nodes, i, state);
				createConnection(connections, state.getMappingInfo().getNakedUmlId() + ARTIFICIAL_START_NODE_ID, state.getMappingInfo().getNakedUmlId());
			}else if(state.getKind().isOrthogonal() || state.getKind().isComposite()){
				addCompoisteState(nodes, connections, i, state);
			}else{
				addSimpleState(nodes, i, state);
			}
		}
	}
	private void addCompoisteState(NodesType nodes,ConnectionsType connections,int i,INakedState state){
		Integer id = state.getMappingInfo().getNakedUmlId();
		CompositeType cs = createCompositeState(nodes, i, state.getName(), id);
		nodes.getComposite().add(cs);
		NakedStateMap map = new NakedStateMap(state);
		addActions(state, map, cs.getOnEntry(), cs.getOnExit());
		addRegions(state, cs.getNodes().get(0), cs.getConnections().get(0));
	}
	private void addSimpleState(NodesType nodes,int i,INakedState state){
		NakedStateMap map = new NakedStateMap(state);
		StateType flowState = ProcessFactory.eINSTANCE.createStateType();
		setBounds(i, flowState, state.getMappingInfo().getNakedUmlId());
		flowState.setName(state.getMappingInfo().getPersistentName().toString());
		EList<OnEntryType> onEntries = flowState.getOnEntry();
		EList<OnExitType> onExits = flowState.getOnExit();
		addActions(state, map, onEntries, onExits);
		nodes.getState().add(flowState);
	}
	private void addActions(INakedState state,NakedStateMap map,EList<OnEntryType> onEntries,EList<OnExitType> onExits){
		if(StateMachineUtil.doesWorkOnEntry(state)){
			OnEntryType onEntry = ProcessFactory.eINSTANCE.createOnEntryType();
			createAction(map.getOnEntryMethod(), onEntry.getAction(),true);
			onEntries.add(onEntry);
		}
		if(StateMachineUtil.doesWorkOnExit(state)){
			OnExitType onExit = ProcessFactory.eINSTANCE.createOnExitType();
			createAction(map.getOnExitMethod(), onExit.getAction(),true);
			onExits.add(onExit);
		}
	}
	private void addInitNode(NodesType nodes,int i,INakedStateMachine sm){
		ActionNodeType actionNode = ProcessFactory.eINSTANCE.createActionNodeType();
		setBounds(i, actionNode, sm.getMappingInfo().getNakedUmlId() + INIT_NODE_ID);
		createAction("init", actionNode.getAction(), true);
		actionNode.setName("init");
		nodes.getActionNode().add(actionNode);
	}
}
