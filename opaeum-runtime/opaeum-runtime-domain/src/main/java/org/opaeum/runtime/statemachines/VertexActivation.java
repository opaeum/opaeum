package org.opaeum.runtime.statemachines;

import java.util.Set;

import org.opaeum.runtime.domain.IExecutionElement;
import org.opaeum.runtime.domain.IToken;

public class VertexActivation<SME extends IStateMachineExecution,T extends IStateMachineToken<SME> > extends StateMachineExecutionElement<SME,T> {
	private RegionActivation<SME,T> containingRegionActivation;
	public VertexActivation(String id,RegionActivation<SME,T> region){
		super(id);
		this.setContainingRegionActivation(region);
		getBehaviorExecution().getExecutionElements().put(id, this);
	}
	public RegionActivation<SME,T> getContainingRegionActivation(){
		return containingRegionActivation;
	}
	private void setContainingRegionActivation(RegionActivation<SME,T> containingRegionActivation){
		this.containingRegionActivation = containingRegionActivation;
	}
	public boolean contains(VertexActivation<SME,T> other){
		return false;
	}
	@Override
	public SME getStateMachineExecution(){
		return getContainingRegionActivation().getStateMachineExecution();
	}
	@SuppressWarnings("unchecked")
	public T exit(){
		T token = null;
		for(IToken<SME> t:getStateMachineExecution().getTokens()){
			if(t.getCurrentExecutionElement() == this){
				token = (T) t;
			}
		}
		if(token != null){
			exit(token);
		}
		return token;
	}
	@SuppressWarnings("unchecked")
	protected void exit(T token){
		Set<IToken<SME>> childTokens = token.getChildTokens();
		for(IToken<SME> childToken:childTokens){
			IExecutionElement<SME> childActivation = childToken.getCurrentExecutionElement();
			if(childActivation instanceof VertexActivation){
				 ((VertexActivation<SME,T>) childActivation).exit((T)childToken);
			}
		}
		for(IToken<SME> childToken:childTokens){
			getStateMachineExecution().removeToken(childToken);
		}
		childTokens.clear();
	}
	public String getId(){
		return id;
	}
	public void enter(T token, VertexActivation<SME,T> target) {
		
	}
	public void onCompletion() {
		// TODO Auto-generated method stub
		
	}
	public void onEntry(T token) {

	}

}
