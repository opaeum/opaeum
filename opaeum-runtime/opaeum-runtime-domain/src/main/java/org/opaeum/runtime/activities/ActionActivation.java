package org.opaeum.runtime.activities;

import java.util.ArrayList;
import java.util.List;

public abstract class ActionActivation<AE extends IActivityNodeContainerExecution, T extends IActivityToken<AE>>  extends ActivityNodeActivation<AE,T>{
	public List<PinActivation<AE,T>> pinActivations = new ArrayList<PinActivation<AE,T>>();
	public boolean firing = false;
	public ActionActivation(AE group,String id){
		super(group, id);
	}
	public void run(){
		// Run this action activation and any outoging fork node attached to it.
		super.run();
		if(this.outgoingEdges.size() > 0){
			this.outgoingEdges.get(0).target.run();
		}
		this.firing = false;
	}
	public List<T> takeOfferedTokens(){
		// If the action is not locally reentrant, then mark this activation as
		// firing.
		// Take any incoming offers of control tokens, then concurrently fire
		// all input pin activations.
		// Note: This is included here to happen in the same isolation scope as
		// the isReady test.
		List<T> offeredTokens = new ArrayList<T>();
		List<ActivityEdgeInstance<AE,T>> incomingEdges = this.incomingEdges;
		for(ActivityEdgeInstance<AE,T> incomingEdge:incomingEdges){
			List<T> tokens = incomingEdge.takeOfferedTokens();
			for(T token:tokens){
				token.withdraw();
				offeredTokens.add(token);
			}
		}
		for(PinActivation<AE,T> pinActivation:pinActivations){
			List<T> tokens = pinActivation.takeOfferedTokens();
			pinActivation.fire(tokens);
			for(T token:tokens){
				offeredTokens.add(token);
			}
		}
		return offeredTokens;
	}
	public void fire(List<T> incomingTokens){
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
		for(PinActivation<AE,T> pinActivation:pinActivations){
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
	@SuppressWarnings("unchecked")
	public void sendOffers(){
		for(PinActivation<AE,T> pa:pinActivations){
			if(pa instanceof OutputPinActivation){
				pa.sendUnofferedTokens();
			}
		}
		if(this.outgoingEdges.size() == 1){
			List<T> tokens = new ArrayList<T>();
			T parent = (T) group.createToken(null);
			parent.setKind(TokenKind.CONTROl);
			tokens.add(parent);
			this.addTokens(tokens);
			outgoingEdges.get(0).sendOffer(tokens);
		}else{
			T parent = (T) group.createToken(null);
			parent.setKind(TokenKind.CONTROl);
			parent.setRemainingOffersCount(this.outgoingEdges.size());
			// Send offers on all outgoing control flows.
			for(ActivityEdgeInstance<AE,T> edge:this.outgoingEdges){
				List<T> tokens = new ArrayList<T>();
				T forkedToken = (T) group.createToken(parent);
				forkedToken.setKind(TokenKind.FORKED);
				tokens.add(forkedToken);
				this.addTokens(tokens);
				edge.sendOffer(tokens);
			}
		}
	}
	public void addPinActivation(PinActivation<AE,T> pinActivation){
		// Add a pin activation to this action activation.
		this.pinActivations.add(pinActivation);
		pinActivation.actionActivation = this;
	}
	public PinActivation<AE,T> getPinActivation(String pin){
		// Precondition: The given pin is owned by the action of the action
		// activation.
		// Return the pin activation corresponding to the given pin.
		for(PinActivation<AE,T> pa:this.pinActivations){
			if(pa.getName().equals(pin)){
				return pa;
			}
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public void putToken(String pin,Object value){
		// Precondition: The action execution has fired and the given pin is
		// owned by the action of the action execution.
		// Place a token for the given value on the pin activation corresponding
		// to the given output pin.
		T token = (T) group.createToken(null);
		token.setKind(TokenKind.OBJECT);
		token.setValue(value);
		PinActivation<AE,T> pinActivation = this.getPinActivation(pin);
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
	@SuppressWarnings("unchecked")
	public List<T> getTokens(String pin){
		// Precondition: The action execution has fired and the given pin is
		// owned by the action of the action execution.
		// Get any tokens held by the pin activation corresponding to the given
		// input pin and return them
		// (but leave the tokens on the pin).
		PinActivation<AE,T> pinActivation = this.getPinActivation(pin);
		List<T> result = new ArrayList<T>();
		List<T> tokens = pinActivation.getUnofferedTokens();
		for(T token:tokens){
			if(token.hasValue()){
				result.add((T) token.getValue());
			}
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	public List<T> takeTokens(String pin){
		// Precondition: The action execution has fired and the given pin is
		// owned by the action of the action execution.
		// Take any tokens held by the pin activation corresponding to the given
		// input pin and return them.
		PinActivation<AE,T> pinActivation = this.getPinActivation(pin);
		List<T> tokens = pinActivation.takeUnofferedTokens();
		List<T> values = new ArrayList<T>();
		for(T token:tokens){
			if(token.hasValue()){
				values.add((T) token.getValue());
			}
		}
		return values;
	}
}
