package org.opaeum.runtime.activities;

import java.util.ArrayList;
import java.util.List;


public class InitialNodeActivation extends ControlNodeActivation{
	public InitialNodeActivation(ActivityNodeContainerInstance group,String id){
		super(group, id);
	}
	@Override
	public void fire(List<Token> incomingTokens){
		List<Token> tokens = new ArrayList<Token>();
		tokens.add(group.createToken(TokenKind.CONTROl));
		this.addTokens(tokens);
		this.sendOffers(tokens);
	}
}
