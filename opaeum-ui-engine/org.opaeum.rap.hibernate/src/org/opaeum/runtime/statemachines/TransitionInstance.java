package org.opaeum.runtime.statemachines;

public abstract class TransitionInstance implements IStateMachineExecutionElement{
	private String id;
	private VertexActivation source;
	protected VertexActivation target;
	protected RegionActivation regionActivation;
	public TransitionInstance(String id,RegionActivation regionActivation, String sourceId, String targetId){
		super();
		this.regionActivation=regionActivation;
		this.id = id;
		this.source=(VertexActivation) regionActivation.getStateMachineExecution().getExecutionElements().get(sourceId);
		this.target=(VertexActivation) regionActivation.getStateMachineExecution().getExecutionElements().get(targetId);
	}
	public VertexActivation getSource(){
		return source;
	}
	public IStateMachineExecution getStateMachineExecution(){
		return regionActivation.getStateMachineExecution();
	}
	protected VertexActivation getMainSource(){
		VertexActivation from = source;
		while(!from.getContainingRegionActivation().contains(target)){
			from=from.getContainingRegionActivation().getContainingState();
		}
		return from;
	}
	protected VertexActivation getMainTarget(){
		VertexActivation to = target;
		while(!to.getContainingRegionActivation().contains(source)){
			to=to.getContainingRegionActivation().getContainingState();
		}
		return to;
	}
	public VertexActivation getTarget() {
		return target;
	}
}
