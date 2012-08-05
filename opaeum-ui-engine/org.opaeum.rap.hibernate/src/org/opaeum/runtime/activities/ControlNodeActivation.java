package org.opaeum.runtime.activities;

import java.util.List;

public class ControlNodeActivation extends ActivityNodeActivation{
	public ControlNodeActivation(ActivityNodeContainerInstance group,String id){
		super(group, id);
	}
	public void fire(List<Token> incomingTokens){
		// By default, offer all tokens on all outgoing edges.
		this.sendOffers(incomingTokens);
	}
}
