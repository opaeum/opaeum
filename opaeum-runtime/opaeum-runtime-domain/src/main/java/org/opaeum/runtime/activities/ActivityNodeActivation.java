package org.opaeum.runtime.activities;

import java.util.ArrayList;
import java.util.List;

public abstract class ActivityNodeActivation<AE extends IActivityNodeContainerExecution, T extends IActivityToken<AE>> implements IActivityNodeActivation<AE,T>{
	protected AE group;
	private String id;
	public List<ActivityEdgeInstance<AE,T>> incomingEdges = new ArrayList<ActivityEdgeInstance<AE,T>>();
	public List<ActivityEdgeInstance<AE,T>> outgoingEdges = new ArrayList<ActivityEdgeInstance<AE,T>>();
	public boolean running = false;
	public List<T> heldTokens = new ArrayList<T>();
	public ActivityNodeActivation(AE group,String id){
		super();
		this.group = group;
		this.id = id;
	}
	@Override
	public AE getBehaviorExecution(){
		return getGroup();
	}
	public AE getGroup(){
		return group;
	}
	public List<ActivityEdgeInstance<AE,T>> getIncomingEdges(){
		return incomingEdges;
	}
	public List<ActivityEdgeInstance<AE,T>> getOutgoingEdges(){
		return outgoingEdges;
	}
	public String getId(){
		return id;
	}
	public void run(){
		this.running = true;
	} // run
	@SuppressWarnings({"rawtypes","unchecked"})

	public List<T> getHeldTokens(){
		if(heldTokens == null){
			heldTokens = (List) group.getTokensHeldBy(this);
		}
		return heldTokens;
	}
	public void receiveOffer(){
		// Receive an offer from an incoming edge.
		// Check if all prerequisites have been satisfied. If so, fire.
		boolean ready = this.isReady();
		List<T> tokens = new ArrayList<T>();
		if(ready){
			tokens = this.takeOfferedTokens();
		}
		if(ready){
			this.fire(tokens);
		}
	} // receiveOffer
	public List<T> takeOfferedTokens(){
		// Get tokens from all incoming edges.
		List<T> allTokens = new ArrayList<T>();
		List<ActivityEdgeInstance<AE,T>> incomingEdges = this.incomingEdges;
		for(int i = 0;i < incomingEdges.size();i++){
			ActivityEdgeInstance<AE,T> incomingEdge = incomingEdges.get(i);
			List<T> tokens = incomingEdge.takeOfferedTokens();
			for(int j = 0;j < tokens.size();j++){
				T token = tokens.get(j);
				allTokens.add(token);
			}
		}
		return allTokens;
	} // takeOfferedTokens
	public abstract void fire(List<T> incomingTokens);
	public void sendOffers(List<T> tokens){
		// Send offers for the given set of tokens over all outgoing edges (if
		// there are any tokens actually being offered).
		if(tokens.size() > 0){
			// *** Send all outgoing offers concurrently. ***
			for(ActivityEdgeInstance<AE,T> ai:this.outgoingEdges){
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
	public void addOutgoingEdge(ActivityEdgeInstance<AE,T> edge){
		// Add an activity edge instance as an outgoing edge of this activity
		// node activation.
		edge.source = this;
		this.outgoingEdges.add(edge);
	}
	public void addIncomingEdge(ActivityEdgeInstance<AE,T> edge){
		// Add an activity edge instance as an incoming edge of this activity
		// node activation.
		edge.target = this;
		this.incomingEdges.add(edge);
	}
	@SuppressWarnings("unchecked")
	public void addToken(T token){
		// Transfer the given token to be held by this node.
		T transferredToken = (T)token.transfer(this);
		this.getHeldTokens().add(transferredToken);
	}
	public  <T2 extends IActivityToken<AE>> int removeToken(T2 activityToken){
		// Remove the given token, if it is held by this node activation.
		// Return the position (counting from 1) of the removed token (0 if
		// there is none removed).
		boolean notFound = true;
		int i = 1;
		while(notFound & i <= getHeldTokens().size()){
			if(getHeldTokens().get(i - 1) == activityToken){
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
	public void addTokens(List<T> tokens){
		for(int i = 0;i < tokens.size();i++){
			T token = tokens.get(i);
			this.addToken(token);
		}
	} // addTokens
	public List<T> takeTokens(){
		// Take the tokens held by this node activation.
		List<T> tokens = this.getTokens();
		this.clearTokens();
		return tokens;
	} // takeTokens
	public void clearTokens(){
		// Remove all held tokens.
		while(this.getHeldTokens().size() > 0){
			this.getHeldTokens().get(0).withdraw();
		}
	} // clearTokens
	public List<T> getTokens(){
		// Get the tokens held by this node activation.
		List<T> tokens = new ArrayList<T>();
		List<T> heldTokens = this.getHeldTokens();
		for(int i = 0;i < heldTokens.size();i++){
			T heldToken = heldTokens.get(i);
			tokens.add(heldToken);
		}
		return tokens;
	} // getTokens
}
