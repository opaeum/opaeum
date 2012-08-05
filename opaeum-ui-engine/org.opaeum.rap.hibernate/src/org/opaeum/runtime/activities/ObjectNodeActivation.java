package org.opaeum.runtime.activities;

import java.util.ArrayList;
import java.util.List;

public abstract class ObjectNodeActivation extends ActivityNodeActivation{
	public ObjectNodeActivation(ActivityNodeContainerInstance group,String id){
		super(group, id);
	}
	public void run(){
		super.run();
	}
	public int evaluateUpperBound(){
		return -1;
	}
	public void sendOffers(List<Token> tokens){
		// If the set of tokens to be sent is empty, then offer a null token
		// instead.
		// Otherwise, offer the given tokens as usual.
		if(tokens.size() == 0){
			tokens.add(group.createToken(TokenKind.OBJECT));
		}
		super.sendOffers(tokens);
	}
	public void terminate(){
		// Remove any offered tokens and terminate.
		this.clearTokens();
		super.terminate();
	}
	public void addToken(Token token){
		// Transfer the given token to be held by this node only if it is a
		// non-null object token.
		// If it is a control token or a null token, consume it without holding
		// it.
		if(token.getValue() == null){
			token.withdraw();
		}else{
			super.addToken(token);
		}
	} // addToken
	public int removeToken(Token token){
		// Remove the given token, if it is held by this node activation.
		int i = super.removeToken(token);
		return i;
	}
	public void clearTokens(){
		// Remove all held tokens.
		super.clearTokens();
	}
	public int countOfferedValues(){
		// Count the total number of non-null object tokens being offered to
		// this node activation.
		int totalValueCount = 0;
		for(ActivityEdgeInstance aei:this.incomingEdges){
			totalValueCount = totalValueCount + aei.countOfferedValues();
		}
		return totalValueCount;
	}
	public void sendUnofferedTokens(){
		// Send offers over all outgoing edges, if there are any tokens to be
		// offered.
		List<Token> tokens = this.getUnofferedTokens();
		this.sendOffers(tokens);
	}
	public int countUnofferedTokens(){
		int result=0;
		for(Token token:getHeldTokens()){
			if(!token.isOffered()){
				result++;
			}
		}
		return result;
	}
	public List<Token> getUnofferedTokens(){
		// Get the next set of unoffered tokens to be offered and return it.
		// [Note: This effectively treats all object flows as if they have
		// weight=*, rather than the weight=1 default in the current
		// superstructure semantics.]
		List<Token> tokens = new ArrayList<Token>();
		for(Token token:this.getHeldTokens()){
			if(!token.isOffered()){
				tokens.add(token);
			}
		}
		return tokens;
	}
	public List<Token> takeUnofferedTokens(){
		List<Token> unofferedTokens = this.getUnofferedTokens();
		for(Token token:unofferedTokens){
			token.withdraw();
		}
		return unofferedTokens;
	}
}
