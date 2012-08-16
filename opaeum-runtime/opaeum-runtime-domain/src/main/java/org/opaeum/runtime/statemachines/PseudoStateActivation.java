package org.opaeum.runtime.statemachines;

public class PseudoStateActivation<SME extends IStateMachineExecution, T extends IStateMachineToken<SME>> extends VertexActivation<SME,T>{
	boolean isInitial;
	public PseudoStateActivation(String id,RegionActivation<SME,T> region){
		super(id, region);
	}
	public boolean isInitial(){
		return isInitial;
	}
	public void setInitial(boolean isInitial){
		this.isInitial = isInitial;
	}
	public void enter(T token, VertexActivation<SME,T> target) {
		token.transferTo(this);
		onEntry(token);
	}
}
