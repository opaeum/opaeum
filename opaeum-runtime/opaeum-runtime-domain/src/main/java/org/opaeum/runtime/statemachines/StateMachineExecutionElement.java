package org.opaeum.runtime.statemachines;


public abstract class StateMachineExecutionElement<SME extends IStateMachineExecution,T extends IStateMachineToken<SME>> implements
		IStateMachineExecutionElement<SME,T>{
	protected String id;
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

}