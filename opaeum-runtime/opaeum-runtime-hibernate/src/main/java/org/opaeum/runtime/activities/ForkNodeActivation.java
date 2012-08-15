package org.opaeum.runtime.activities;

import java.util.List;


public class ForkNodeActivation<AE extends IActivityNodeContainerExecution, T extends ActivityToken<AE>> extends ControlNodeActivation<AE,T>{
	public ForkNodeActivation(AE group,String id){
		super(group, id);
	}
	public void fire(List<T> incomingTokens){

		this.addTokens(incomingTokens);
		this.sendOffers(incomingTokens);
	} 
}
