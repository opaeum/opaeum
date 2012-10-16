package org.opaeum.runtime.statemachines;

public class PseudoStateActivation extends VertexActivation{
	boolean isInitial;
	public PseudoStateActivation(String id,RegionActivation region){
		super(id, region);
	}
	public boolean isInitial(){
		return isInitial;
	}
	public void setInitial(boolean isInitial){
		this.isInitial = isInitial;
	}
	public void enter(StateMachineToken token, VertexActivation target) {
		token.transferTo(this);
		
		//TODO
		
	}
}
