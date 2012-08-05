package org.opaeum.runtime.activities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParameterNodeActivation extends ObjectNodeActivation{
	private String name;
	public ParameterNodeActivation(ActivityNodeContainerInstance group,String id,String name){
		super(group, id);
		this.name = name;
	}
	@Override
	public void fire(List<Token> incomingTokens){
		// If there are no incoming edges, this is an activation of an input
		// activity parameter node.
		// Get the values from the input parameter indicated by the activity
		// parameter node and offer those values as object tokens.
		if(this.incomingEdges.size() == 0){
			Object parameterValue = ((ActivityInstance) this.getGroup()).getParameterValue(name);
			List<Object> values = null;
			if(parameterValue != null){
				if(!(parameterValue instanceof List)){
					values = Collections.singletonList(parameterValue);
				}
				for(Object object:values){
					Token token=group.createToken(TokenKind.OBJECT);
					token.setValue(object);
					this.addToken(token);
				}
				this.sendUnofferedTokens();
			}
		}
		// If there are one or more incoming edges, this is an activation of an
		// output activity parameter node.
		// Take the tokens offered on incoming edges and add them to the set of
		// tokens being offered.
		// [Note that an output activity parameter node may fire multiple times,
		// accumulating tokens offered to it.]
		else{
			this.addTokens(incomingTokens);
		}
	}
	public void clearTokens(){
		// Clear all held tokens only if this is an input parameter node.
		if(this.incomingEdges.size() == 0){
			super.clearTokens();
		}
	} // clearTokens
}
