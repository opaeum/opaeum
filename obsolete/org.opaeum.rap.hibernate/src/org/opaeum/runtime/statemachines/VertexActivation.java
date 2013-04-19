package org.opaeum.runtime.statemachines;

import java.util.Set;

import org.opaeum.hibernate.domain.AbstractToken;

public class VertexActivation implements IStateMachineExecutionElement{
	private String id;
	private RegionActivation containingRegionActivation;
	public VertexActivation(String id,RegionActivation region){
		super();
		this.id = id;
		this.setContainingRegionActivation(region);
		getStateMachineExecution().getExecutionElements().put(id, this);
	}
	public IStateMachineExecution getStateMachineExecution(){
		return getContainingRegionActivation().getStateMachineExecution();
	}
	public RegionActivation getContainingRegionActivation(){
		return containingRegionActivation;
	}
	private void setContainingRegionActivation(RegionActivation containingRegionActivation){
		this.containingRegionActivation = containingRegionActivation;
	}
	public boolean contains(VertexActivation other){
		return false;
	}
	public StateMachineToken exit(){
		StateMachineToken token = null;
		for(StateMachineToken t:getStateMachineExecution().getTokens()){
			if(t.getCurrentExecutionElement() == this){
				token = t;
			}
		}
		if(token != null){
			exit(token);
		}
		return token;
	}
	protected void exit(StateMachineToken token){
		Set<? extends StateMachineToken> children = token.getChildTokens();
		for(StateMachineToken childToken:children){
			IStateMachineExecutionElement childActivation = childToken.getCurrentExecutionElement();
			if(childActivation instanceof VertexActivation){
				 ((VertexActivation) childActivation).exit(childToken);
			}
		}
		for(StateMachineToken childToken:children){
			getStateMachineExecution().removeToken(childToken);
		}
		token.getChildTokens().clear();
	}
	public String getId(){
		return id;
	}
	public void enter(StateMachineToken token, VertexActivation target) {
		
	}
	public void onCompletion() {
		// TODO Auto-generated method stub
		
	}

}
