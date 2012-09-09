package org.opaeum.runtime.statemachines;

import java.util.HashSet;
import java.util.Set;

import org.opaeum.runtime.domain.IObservation;


public abstract class StateMachineExecutionElement<SME extends IStateMachineExecution,T extends IStateMachineToken<SME>> implements
		IStateMachineExecutionElement<SME,T>{
	protected String id;
	protected Set<IObservation> observations=new HashSet<IObservation>();
	public StateMachineExecutionElement(String id2){
		this.id = id2;
	}
	public String getId(){
		return id;
	}
	@Override
	public SME getBehaviorExecution(){
		return getStateMachineExecution();
	}
	@Override
	public void registerObservation(IObservation obs){
		observations.add(obs);
	}
	public void onEntry(T t){
		for(IObservation o:observations){
			o.onEntry(this);
		}
	}
	public void onExit(T t){
		for(IObservation o:observations){
			o.onExit(this);
		}
	}

}