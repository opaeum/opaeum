package org.opaeum.runtime.activities;

import java.util.List;


public class ForkNodeActivation extends ControlNodeActivation{
	public ForkNodeActivation(IActivityNodeContainerExecution group,String id){
		super(group, id);
	}
	public void fire(List<ActivityToken> incomingTokens){

		this.addTokens(incomingTokens);
		this.sendOffers(incomingTokens);
	} 
}
