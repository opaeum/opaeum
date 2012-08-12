package org.opaeum.runtime.statemachines;

public abstract class HistoryStateActivation extends PseudoStateActivation{

	public HistoryStateActivation(String id,RegionActivation region){
		super(id, region);
	}
	public abstract String getHistoricalStateId();
}
