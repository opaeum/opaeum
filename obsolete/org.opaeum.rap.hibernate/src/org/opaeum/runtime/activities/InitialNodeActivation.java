package org.opaeum.runtime.activities;

import java.util.ArrayList;
import java.util.List;


public class InitialNodeActivation extends ControlNodeActivation{
	public InitialNodeActivation(IActivityNodeContainerExecution group,String id){
		super(group, id);
	}
	@Override
	public void fire(List<ActivityToken> incomingTokens){
		List<ActivityToken> tokens = new ArrayList<ActivityToken>();
		tokens.add(group.createToken(TokenKind.CONTROl));
		this.addTokens(tokens);
		this.sendOffers(tokens);
	}
}
