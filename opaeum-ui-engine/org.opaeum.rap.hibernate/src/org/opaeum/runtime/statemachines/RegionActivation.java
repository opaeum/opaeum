package org.opaeum.runtime.statemachines;

import java.util.HashSet;
import java.util.Set;

public abstract class RegionActivation implements IStateMachineExecutionElement{
	private String id;
	private IStateMachineExecution stateMachineExecution;
	private StateActivation owningState;
	protected Set<VertexActivation> vertices = new HashSet<VertexActivation>();
	protected Set<TransitionInstance> transitions = new HashSet<TransitionInstance>();
	public RegionActivation(String id,StateActivation owningState){
		super();
		this.id = id;
		this.owningState = owningState;
		getStateMachineExecution().getExecutionElements().put(id, this);
		owningState.getRegions().add(this);
	}
	public RegionActivation(String id,IStateMachineExecution stateMachineExecution){
		super();
		this.id = id;
		this.stateMachineExecution = stateMachineExecution;
		getStateMachineExecution().getExecutionElements().put(id, this);
	}
	public void linkTransitions(){
		for (VertexActivation v : vertices) {
			if(v instanceof StateActivation){
				StateActivation sa=(StateActivation) v;
				for (RegionActivation ra : sa.getRegions()) {
					ra.linkTransitions();
				}
			}
				
		}
	}
	public IStateMachineExecution getStateMachineExecution(){
		if(stateMachineExecution != null){
			return stateMachineExecution;
		}else{
			return owningState.getStateMachineExecution();
		}
	}
	public StateActivation getContainingState(){
		return owningState;
	}
	public boolean contains(VertexActivation other){
		for(VertexActivation v:vertices){
			if(v == other || v.contains(other)){
				return true;
			}
		}
		return false;
	}
	public void initiate(StateMachineToken token){
		for(VertexActivation v:vertices){
			if(v instanceof PseudoStateActivation && ((PseudoStateActivation) v).isInitial()){
				if(v instanceof HistoryStateActivation){
					HistoryStateActivation h = (HistoryStateActivation) v;
					if(h.getHistoricalStateId() == null){
						enter(token, h);
					}else{
						enter(token, (VertexActivation) getStateMachineExecution().getExecutionElements().get(h.getHistoricalStateId()));
					}
				}else{
					enter(token, v);
				}
				break;
			}
		}
	}
	public void enter(StateMachineToken token,VertexActivation target){
		token = getStateMachineExecution().createToken(token);
		for(VertexActivation v:vertices){
			if(v == target){
				token.transferTo(target);
			}else if(v.contains(target)){
				token.transferTo(target);
				((StateActivation) v).enter(token, target);
			}
		}
	}
}
