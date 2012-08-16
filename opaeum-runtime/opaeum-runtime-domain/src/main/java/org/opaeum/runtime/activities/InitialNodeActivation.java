package org.opaeum.runtime.activities;

import java.util.ArrayList;
import java.util.List;


public class InitialNodeActivation<AE extends IActivityNodeContainerExecution, T extends IActivityToken<AE>> extends ControlNodeActivation<AE,T>{
	public InitialNodeActivation(AE group,String id){
		super(group, id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public void fire(List<T> incomingTokens){
		List<T> tokens = new ArrayList<T>();
		T t = (T) group.createToken(null);
		tokens.add(t);
		t.setKind(TokenKind.CONTROl);
		this.addTokens(tokens);
		this.sendOffers(tokens);
	}
}
