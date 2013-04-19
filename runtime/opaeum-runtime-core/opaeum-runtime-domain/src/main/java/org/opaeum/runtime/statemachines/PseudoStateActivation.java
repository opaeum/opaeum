package org.opaeum.runtime.statemachines;

public class PseudoStateActivation<SME extends IStateMachineExecution, T extends IStateMachineToken<SME>> extends VertexActivation<SME,T>{
	boolean isInitial;
	@SuppressWarnings("rawtypes")
	public PseudoStateActivation(String id,RegionActivation region){
		super(id, region);
	}
	
	public boolean isInitial(){
		return isInitial;
	}
	public void setInitial(boolean isInitial){
		this.isInitial = isInitial;
	}

}
