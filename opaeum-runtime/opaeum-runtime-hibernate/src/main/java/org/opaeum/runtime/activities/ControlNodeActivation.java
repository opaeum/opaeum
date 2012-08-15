package org.opaeum.runtime.activities;

import java.util.List;

public class ControlNodeActivation<AE extends IActivityNodeContainerExecution, T extends ActivityToken<AE>> extends ActivityNodeActivation<AE,T>{
	public ControlNodeActivation(AE group,String id){
		super(group, id);
	}
	public void fire(List<T> incomingTokens){
		// By default, offer all tokens on all outgoing edges.
		this.sendOffers(incomingTokens);
	}
}
