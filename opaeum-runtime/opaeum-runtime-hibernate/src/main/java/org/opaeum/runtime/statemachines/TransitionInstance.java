package org.opaeum.runtime.statemachines;

import java.util.Collection;

import org.opaeum.runtime.domain.OutgoingEvent;

public abstract class TransitionInstance<SME extends IStateMachineExecution, T extends IStateMachineToken<SME>> extends StateMachineExecutionElement<SME,T>{
	private VertexActivation<SME,T> source;
	protected VertexActivation<SME,T> target;
	protected RegionActivation<SME,T> regionActivation;
	@SuppressWarnings("unchecked")
	public TransitionInstance(String id,RegionActivation<SME,T> regionActivation, String sourceId, String targetId){
		super(id);
		this.regionActivation=regionActivation;
		this.source=(VertexActivation<SME,T>) regionActivation.getStateMachineExecution().getExecutionElements().get(sourceId);
		this.target=(VertexActivation<SME,T>) regionActivation.getStateMachineExecution().getExecutionElements().get(targetId);
	}
	public VertexActivation<SME,T> getSource(){
		return source;
	}
	public String getId() {
		return id;
	}
	public SME getStateMachineExecution(){
		return regionActivation.getStateMachineExecution();
	}
	protected VertexActivation<SME,T> getMainSource(){
		VertexActivation<SME,T> from = source;
		while(!from.getContainingRegionActivation().contains(target)){
			from=from.getContainingRegionActivation().getContainingState();
		}
		return from;
	}
	protected VertexActivation<SME,T> getMainTarget(){
		VertexActivation<SME,T> to = target;
		while(!to.getContainingRegionActivation().contains(source)){
			to=to.getContainingRegionActivation().getContainingState();
		}
		return to;
	}
	public VertexActivation<SME,T> getTarget() {
		return target;
	}
	public Collection<OutgoingEvent> getOutgoingEvents(){
		return getBehaviorExecution().getOutgoingEvents();
	}
}
