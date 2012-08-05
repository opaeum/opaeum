package org.opaeum.jbpm5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.StateMap;

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
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Vertex;
import org.opaeum.eclipse.EmfStateMachineUtil;
import org.opaeum.eclipse.PersistentNameUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;

@StepDependency(phase = FlowGenerationPhase.class)
public class StateMachineFlowStep extends AbstractFlowStep{
	private static final int ARTIFICIAL_START_NODE_ID = 300000;
	private static final int ARTIFICIAL_FORK_ID = 400000;
	private static final int ARTIFICIAL_END_NODE_ID = 500000;
	private static final int ARTIFICIAL_JOIN_ID = 600000;
	private static final int ON_COMPLETION_NODE_ID = 700000;
	private static final int INIT_NODE_ID = 800000;
	@VisitAfter(matchSubclasses = true)
	public void createRoot(StateMachine sm){
		DocumentRoot root = super.createRoot(sm);
		ProcessType process = root.getProcess();
		sourceIdMap.push(new HashMap<Element,Long>());
		targetIdMap.push(new HashMap<Element,Long>());
		addRegions(sm, process.getNodes().get(0), process.getConnections().get(0));
	}
	public void addRegions(Namespace owner,NodesType nodes,ConnectionsType connections){
		Collection<Region> regions = EmfStateMachineUtil.getRegions(owner);
		int i = 1;
		// Create composite node per region.
		StartType start = ProcessFactory.eINSTANCE.createStartType();
		Long startNodeId = EmfWorkspace.getOpaeumId(owner) + ARTIFICIAL_START_NODE_ID;
		start.setName("artificial_start");
		nodes.getStart().add(start);
		Long from = startNodeId;
		setBounds(i, start, startNodeId);
		if(owner instanceof StateMachine){
			addInitNode(nodes, i, (StateMachine) owner);
			createConnection(connections, startNodeId, EmfWorkspace.getOpaeumId(owner) + INIT_NODE_ID);
			startNodeId = EmfWorkspace.getOpaeumId(owner) + INIT_NODE_ID;
			from = startNodeId;
		}
		if(regions.size() > 1){
			i++;
			from = EmfWorkspace.getOpaeumId(owner) + ARTIFICIAL_FORK_ID;
			super.addFork(nodes, i, "artificial_fork", from);
			createConnection(connections, startNodeId, from);
		}
		i++;
		List<Long> regionIds = new ArrayList<Long>();
		for(Region region:regions){
			DynamicType node = createDynamicState(nodes, i, region.getName(), EmfWorkspace.getOpaeumId(region));
			i++;
			populateRegion(node.getNodes().get(0), node.getConnections().get(0), region);
			regionIds.add(EmfWorkspace.getOpaeumId(region));
		}
		Long endNodeId = EmfWorkspace.getOpaeumId(owner) + ARTIFICIAL_END_NODE_ID;
		addFinalNode(nodes, i, "artificial_end", endNodeId);
		i++;
		if(owner instanceof State && EmfStateMachineUtil.getCompletionTransitions((State) owner).size() > 0){
			long id = EmfWorkspace.getOpaeumId(owner) + ON_COMPLETION_NODE_ID;
			StateType state = super.addState(nodes, i, "on_completion_of_" + owner.getName(), id);
			state.getOnEntry().add(ProcessFactory.eINSTANCE.createOnEntryType());
			createAction(eventUtil.getEventConsumerName((State) owner), state.getOnEntry().get(0).getAction(), false);
			createConnection(connections, id, endNodeId);
			endNodeId = id;
			i++;
		}
		Long to = endNodeId;
		if(regions.size() > 1){
			to = EmfWorkspace.getOpaeumId(owner) + ARTIFICIAL_JOIN_ID;
			super.addJoin(nodes, i, "artificial_join", to);
			createConnection(connections, to, endNodeId);
			i++;
		}
		for(Long regionId:regionIds){
			createConnection(connections, from, regionId);
			createConnection(connections, regionId, to);
		}
		// TODO create connection to each region
	}
	private void populateRegion(NodesType nodes,ConnectionsType connections,Region region){
		List<Vertex> states = new ArrayList<Vertex>(region.getSubvertices());
		int i = 1;
		for(Vertex state:states){
			i++;
			if( EmfStateMachineUtil.isStartingVertex(state)){
				addInitialNode(nodes, i, "_start_",EmfWorkspace.getOpaeumId(state) + ARTIFICIAL_START_NODE_ID);
				i++;
				addSimpleState(nodes, i, state);
				createConnection(connections, EmfWorkspace.getOpaeumId(state) + ARTIFICIAL_START_NODE_ID, EmfWorkspace.getOpaeumId(state));
			}else if ( state instanceof State && ( ((State) state).isOrthogonal() || ((State) state).isComposite())){
				addCompoisteState(nodes, connections, i, (State) state);
			}else{
				addSimpleState(nodes, i, state);
			}
		}
	}
	private void addCompoisteState(NodesType nodes,ConnectionsType connections,int i,State state){
		Long id = EmfWorkspace.getOpaeumId(state);
		CompositeType cs = createCompositeState(nodes, i, state.getName(), id);
		nodes.getComposite().add(cs);
		StateMap map = ojUtil.buildStateMap(state);
		addActions(state, map, cs.getOnEntry(), cs.getOnExit());
		addRegions(state, cs.getNodes().get(0), cs.getConnections().get(0));
	}
	private void addSimpleState(NodesType nodes,int i,Vertex state){
		StateMap map = ojUtil.buildStateMap(state);
		StateType flowState = ProcessFactory.eINSTANCE.createStateType();
		setBounds(i, flowState, EmfWorkspace.getOpaeumId( state));
		flowState.setName(PersistentNameUtil.getPersistentName( state).toString());
		EList<OnEntryType> onEntries = flowState.getOnEntry();
		EList<OnExitType> onExits = flowState.getOnExit();
		addActions(state, map, onEntries, onExits);
		nodes.getState().add(flowState);
	}
	private void addActions(Vertex state,StateMap map,EList<OnEntryType> onEntries,EList<OnExitType> onExits){
		if(EmfStateMachineUtil.doesWorkOnEntry(state)){
			OnEntryType onEntry = ProcessFactory.eINSTANCE.createOnEntryType();
			createAction(map.getOnEntryMethod(), onEntry.getAction(), true);
			onEntries.add(onEntry);
		}
		if(state instanceof State &&  EmfStateMachineUtil.doesWorkOnExit((State) state)){
			OnExitType onExit = ProcessFactory.eINSTANCE.createOnExitType();
			createAction(map.getOnExitMethod(), onExit.getAction(), true);
			onExits.add(onExit);
		}
	}
	private void addInitNode(NodesType nodes,int i,StateMachine sm){
		ActionNodeType actionNode = ProcessFactory.eINSTANCE.createActionNodeType();
		setBounds(i, actionNode,  EmfWorkspace.getOpaeumId(sm) + INIT_NODE_ID);
		createAction("init", actionNode.getAction(), true);
		actionNode.setName("init");
		nodes.getActionNode().add(actionNode);
	}
}
