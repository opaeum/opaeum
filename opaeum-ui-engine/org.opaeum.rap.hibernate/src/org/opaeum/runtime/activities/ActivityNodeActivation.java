package org.opaeum.runtime.activities;

import java.util.ArrayList;
import java.util.List;

public abstract class ActivityNodeActivation{
	protected ActivityNodeContainerInstance group;
	private String id;
	public List<ActivityEdgeInstance> incomingEdges = new ArrayList<ActivityEdgeInstance>();
	public List<ActivityEdgeInstance> outgoingEdges = new ArrayList<ActivityEdgeInstance>();
	public boolean running = false;
	public List<Token> heldTokens = new ArrayList<Token>();
	public ActivityNodeActivation(ActivityNodeContainerInstance group,String id){
		super();
		this.group = group;
		this.id = id;
	}
	public ActivityNodeContainerInstance getGroup(){
		return group;
	}
	public List<ActivityEdgeInstance> getIncomingEdges(){
		return incomingEdges;
	}
	public List<ActivityEdgeInstance> getOutgoingEdges(){
		return outgoingEdges;
	}
	public String getId(){
		return id;
	}
	public void run(){
		this.running = true;
	} // run
	public List<Token> getHeldTokens(){
		if(heldTokens == null){
			heldTokens = group.getTokensHeldBy(this);
		}
		return heldTokens;
	}
	public void receiveOffer(){
		// Receive an offer from an incoming edge.
		// Check if all prerequisites have been satisfied. If so, fire.
		boolean ready = this.isReady();
		List<Token> tokens = new ArrayList<Token>();
		if(ready){
			tokens = this.takeOfferedTokens();
		}
		if(ready){
			this.fire(tokens);
		}
	} // receiveOffer
	public List<Token> takeOfferedTokens(){
		// Get tokens from all incoming edges.
		List<Token> allTokens = new ArrayList<Token>();
		List<ActivityEdgeInstance> incomingEdges = this.incomingEdges;
		for(int i = 0;i < incomingEdges.size();i++){
			ActivityEdgeInstance incomingEdge = incomingEdges.get(i);
			List<Token> tokens = incomingEdge.takeOfferedTokens();
			for(int j = 0;j < tokens.size();j++){
				Token token = tokens.get(j);
				allTokens.add(token);
			}
		}
		return allTokens;
	} // takeOfferedTokens
	public abstract void fire(List<Token> incomingTokens);
	public void sendOffers(List<Token> tokens){
		// Send offers for the given set of tokens over all outgoing edges (if
		// there are any tokens actually being offered).
		if(tokens.size() > 0){
			// *** Send all outgoing offers concurrently. ***
			for(ActivityEdgeInstance ai:this.outgoingEdges){
				ai.sendOffer(tokens);
			}
		}
	} // sendOffers
	public void terminate(){
		this.running = false;
	} // terminate
	public boolean isReady(){
		// Check if all the prerequisites for this node have been satisfied.
		// By default, check that this node is running.
		return this.isRunning();
	} // isReady
	public boolean isRunning(){
		return this.running;
	}
	public void addOutgoingEdge(ActivityEdgeInstance edge){
		// Add an activity edge instance as an outgoing edge of this activity
		// node activation.
		edge.source = this;
		this.outgoingEdges.add(edge);
	}
	public void addIncomingEdge(ActivityEdgeInstance edge){
		// Add an activity edge instance as an incoming edge of this activity
		// node activation.
		edge.target = this;
		this.incomingEdges.add(edge);
	}
	public void addToken(Token token){
		// Transfer the given token to be held by this node.
		Token transferredToken = token.transfer(this);
		this.getHeldTokens().add(transferredToken);
	}
	public int removeToken(Token token){
		// Remove the given token, if it is held by this node activation.
		// Return the position (counting from 1) of the removed token (0 if
		// there is none removed).
		boolean notFound = true;
		int i = 1;
		while(notFound & i <= getHeldTokens().size()){
			if(getHeldTokens().get(i - 1) == token){
				notFound = false;
			}
			i = i + 1;
		}
		if(notFound){
			i = 0;
		}else{
			i = i - 1;
		}
		return i;
	}
	public void addTokens(List<Token> tokens){
		for(int i = 0;i < tokens.size();i++){
			Token token = tokens.get(i);
			this.addToken(token);
		}
	} // addTokens
	public List<Token> takeTokens(){
		// Take the tokens held by this node activation.
		List<Token> tokens = this.getTokens();
		this.clearTokens();
		return tokens;
	} // takeTokens
	public void clearTokens(){
		// Remove all held tokens.
		while(this.getHeldTokens().size() > 0){
			this.getHeldTokens().get(0).withdraw();
		}
	} // clearTokens
	public List<Token> getTokens(){
		// Get the tokens held by this node activation.
		List<Token> tokens = new ArrayList<Token>();
		List<Token> heldTokens = this.getHeldTokens();
		for(int i = 0;i < heldTokens.size();i++){
			Token heldToken = heldTokens.get(i);
			tokens.add(heldToken);
		}
		return tokens;
	} // getTokens
}
