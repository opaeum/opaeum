package org.opaeum.runtime.statemachines;

public abstract class HistoryStateActivation<SME extends IStateMachineExecution, T extends IStateMachineToken<SME>> extends PseudoStateActivation<SME,T>{

	public HistoryStateActivation(String id,RegionActivation<SME,T> region){
		super(id, region);
	}
	public abstract String getHistoricalStateId();
}
