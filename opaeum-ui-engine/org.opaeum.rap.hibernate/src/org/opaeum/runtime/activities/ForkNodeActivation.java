package org.opaeum.runtime.activities;

import java.util.List;


public class ForkNodeActivation extends ControlNodeActivation{
	public ForkNodeActivation(ActivityNodeContainerInstance group,String id){
		super(group, id);
	}
	public void fire(List<Token> incomingTokens){

		this.addTokens(incomingTokens);
		this.sendOffers(incomingTokens);
	} 
}
