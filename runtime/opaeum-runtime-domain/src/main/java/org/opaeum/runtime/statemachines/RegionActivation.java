package org.opaeum.runtime.statemachines;

import java.util.HashSet;
import java.util.Set;

public abstract class RegionActivation<SME extends IStateMachineExecution,T extends IStateMachineToken<SME> > extends StateMachineExecutionElement<SME,T>{
	private SME stateMachineExecution;
	private StateActivation<SME,T> owningState;
	protected Set<VertexActivation<? extends SME,? extends T>> vertices = new HashSet<VertexActivation<? extends SME,? extends T>>();
	protected Set<TransitionInstance<? extends SME,? extends T>> transitions = new HashSet<TransitionInstance<? extends SME,? extends T>>();
	@SuppressWarnings({"rawtypes","unchecked"})
	public RegionActivation(String id,StateActivation owningState){
		super(id);
		this.owningState = owningState;
		getStateMachineExecution().getExecutionElements().put(id, this);
		owningState.getRegions().add(this);
	}
	public RegionActivation(String id,SME stateMachineExecution){
		super(id);
		this.stateMachineExecution = stateMachineExecution;
		getStateMachineExecution().getExecutionElements().put(id, this);
	}
	public void linkTransitions(){
		for(VertexActivation<? extends SME,? extends T> v:vertices){
			if(v instanceof StateActivation){
				@SuppressWarnings("unchecked")
				StateActivation<SME,T> sa = (StateActivation<SME,T>) v;
				for(RegionActivation<SME,T> ra:sa.getRegions()){
					ra.linkTransitions();
				}
			}
		}
	}
	public SME getStateMachineExecution(){
		if(stateMachineExecution != null){
			return stateMachineExecution;
		}else{
			return owningState.getStateMachineExecution();
		}
	}
	public StateActivation<SME,T> getContainingState(){
		return owningState;
	}
	public boolean contains(VertexActivation<? extends SME,? extends T> other){
		for(VertexActivation<? extends SME,? extends T> v:vertices){
			if(v == other || v.contains(other)){
				return true;
			}
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public void initiate(T token){
		for(VertexActivation<? extends SME,? extends T>  v:vertices){
			if(v instanceof PseudoStateActivation && ((PseudoStateActivation<SME,T>) v).isInitial()){
				if(v instanceof HistoryStateActivation){
					HistoryStateActivation<SME,T> h = (HistoryStateActivation<SME,T>) v;
					if(h.getHistoricalStateId() == null){
						enter(token, h);
					}else{
						enter(token, (VertexActivation<SME,T>)getStateMachineExecution().getExecutionElements().get(h.getHistoricalStateId()));
					}
				}else{
					enter(token, v);
				}
				break;
			}
		}
	}
	@SuppressWarnings("unchecked")
	public void enter(T token,VertexActivation<? extends SME,? extends T> target){
		token = ((T) getStateMachineExecution().createToken(token));
		super.onEntry(token);
		
		for(VertexActivation<? extends SME,? extends T> v:vertices){
			if(v == target){
				((VertexActivation<SME,T>) v).enter(token, target);
			}else if(v.contains(target)){
				((StateActivation<SME,T>) v).enter(token, target);
			}
		}
	}
}
