package org.opaeum.runtime.activities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ActionActivation extends ActivityNodeActivation{
	public ActionActivation(IActivityNodeContainerExecution group,String id){
		super(group, id);
	}
	public List<PinActivation> pinActivations = new ArrayList<PinActivation>();
	public boolean firing = false;
	public void run(){
		// Run this action activation and any outoging fork node attached to it.
		super.run();
		if(this.outgoingEdges.size() > 0){
			this.outgoingEdges.get(0).target.run();
		}
		this.firing = false;
	}
	public List<ActivityToken> takeOfferedTokens(){
		// If the action is not locally reentrant, then mark this activation as
		// firing.
		// Take any incoming offers of control tokens, then concurrently fire
		// all input pin activations.
		// Note: This is included here to happen in the same isolation scope as
		// the isReady test.
		List<ActivityToken> offeredTokens = new ArrayList<ActivityToken>();
		List<ActivityEdgeInstance> incomingEdges = this.incomingEdges;
		for(ActivityEdgeInstance incomingEdge:incomingEdges){
			List<ActivityToken> tokens = incomingEdge.takeOfferedTokens();
			for(ActivityToken token:tokens){
				token.withdraw();
				offeredTokens.add(token);
			}
		}
		for(PinActivation pinActivation:pinActivations){
			List<ActivityToken> tokens = pinActivation.takeOfferedTokens();
			pinActivation.fire(tokens);
			for(ActivityToken token:tokens){
				offeredTokens.add(token);
			}
		}
		return offeredTokens;
	}
	public void fire(List<ActivityToken> incomingTokens){
		// Do the main action behavior then concurrently fire all output pin
		// activations
		// and offer a single control token. Then activate the action again,
		// if it is still ready to fire and has at least one token actually
		// being
		// offered to it.
		boolean fireAgain = false;
		do{
			this.doAction();
			this.sendOffers();
			fireAgain = false;
			this.firing = false;
			if(this.isReady()){
				incomingTokens = this.takeOfferedTokens();
				fireAgain = incomingTokens.size() > 0;
				this.firing = this.isFiring() & fireAgain;
			}
		}while(fireAgain);
	}
	public void terminate(){
		// Terminate this action activation and any outgoing fork node attached
		// to it.
		super.terminate();
		if(this.outgoingEdges.size() > 0){
			this.outgoingEdges.get(0).target.terminate();
		}
	}
	public boolean isReady(){
		// In addition to the default condition, check that, if the action has
		// isLocallyReentrant=false, then the activation is not currently
		// firing,
		// and that the sources of all incoming edges (control flows) have
		// offers and all input pin activations are ready.
		// [This assumes that all edges directly incoming to the action are
		// control flows.]
		boolean ready = super.isReady() && !this.isFiring();
		int i = 1;
		while(ready & i <= this.incomingEdges.size()){
			ready = this.incomingEdges.get(i - 1).hasOffer();
			i = i + 1;
		}
		for(PinActivation pinActivation:pinActivations){
			if(pinActivation instanceof InputPinActivation){
				ready = ready && pinActivation.isReady();
			}
		}
		return ready;
	}
	public boolean isFiring(){
		// Indicate whether this action activation is currently firing or not.
		return firing;
	}
	public abstract void doAction();
	public void sendOffers(){
		for(PinActivation pa:pinActivations){
			if(pa instanceof OutputPinActivation){
				pa.sendUnofferedTokens();
			}
		}
		if(this.outgoingEdges.size() == 1){
			List<ActivityToken> tokens = new ArrayList<ActivityToken>();
			ActivityToken parent = group.createToken(TokenKind.CONTROl);
			tokens.add(parent);
			this.addTokens(tokens);
			outgoingEdges.get(0).sendOffer(tokens);
		}else{
			ActivityToken parent = group.createToken(TokenKind.CONTROl);
			// Send offers on all outgoing control flows.
			for(ActivityEdgeInstance edge:this.outgoingEdges){
				List<ActivityToken> tokens = new ArrayList<ActivityToken>();
				ActivityToken forkedToken = group.createToken(TokenKind.FORKED);
				forkedToken.remainingOffersCount = this.outgoingEdges.size();
				forkedToken.baseToken=parent;
				tokens.add(forkedToken);
				this.addTokens(tokens);
				edge.sendOffer(tokens);
			}
		}
	}
	public void addPinActivation(PinActivation pinActivation){
		// Add a pin activation to this action activation.
		this.pinActivations.add(pinActivation);
		pinActivation.actionActivation = this;
	}
	public PinActivation getPinActivation(String pin){
		// Precondition: The given pin is owned by the action of the action
		// activation.
		// Return the pin activation corresponding to the given pin.
		for(PinActivation pa:this.pinActivations){
			if(pa.getName().equals(pin)){
				return pa;
			}
		}
		return null;
	}
	public void putToken(String pin,Object value){
		// Precondition: The action execution has fired and the given pin is
		// owned by the action of the action execution.
		// Place a token for the given value on the pin activation corresponding
		// to the given output pin.
		ActivityToken token = group.createToken(TokenKind.OBJECT);
		token.setValue(value);
		PinActivation pinActivation = this.getPinActivation(pin);
		pinActivation.addToken(token);
	}
	public void putTokens(String pin,List<? extends Object> values){
		// Precondition: The action execution has fired and the given pin is
		// owned by the action of the action execution.
		// Place tokens for the given values on the pin activation corresponding
		// to the given output pin.
		for(Object object:values){
			this.putToken(pin, object);
		}
	}
	public <T>List<T> getTokens(String pin){
		// Precondition: The action execution has fired and the given pin is
		// owned by the action of the action execution.
		// Get any tokens held by the pin activation corresponding to the given
		// input pin and return them
		// (but leave the tokens on the pin).
		PinActivation pinActivation = this.getPinActivation(pin);
		List<T> result = new ArrayList<T>();
		List<ActivityToken> tokens = pinActivation.getUnofferedTokens();
		for(ActivityToken token:tokens){
			if(token.hasValue()){
				result.add((T) token.getValue());
			}
		}
		return result;
	}
	public <T>List<T> takeTokens(String pin){
		// Precondition: The action execution has fired and the given pin is
		// owned by the action of the action execution.
		// Take any tokens held by the pin activation corresponding to the given
		// input pin and return them.
		PinActivation pinActivation = this.getPinActivation(pin);
		List<ActivityToken> tokens = pinActivation.takeUnofferedTokens();
		List<T> values = new ArrayList<T>();
		for(ActivityToken token:tokens){
			if(token.hasValue()){
				values.add((T) token.getValue());
			}
		}
		return values;
	}
}
