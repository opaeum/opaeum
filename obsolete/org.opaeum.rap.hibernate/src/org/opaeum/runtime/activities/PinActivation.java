package org.opaeum.runtime.activities;

import java.util.ArrayList;
import java.util.List;

public abstract class PinActivation extends ObjectNodeActivation{
	public ActionActivation actionActivation = null;
	private int upper;
	protected int lower;
	private String name;
	public PinActivation(IActivityNodeContainerExecution group,String id,String name, int lower,int upper){
		super(group, id);
		this.upper = upper;
		this.lower = lower;
		this.name=name;
	}
	public void fire(List<ActivityToken> incomingTokens){
		// Add all incoming tokens to the pin.
		// [Note that a pin will consume all tokens offered to it, even if this
		// is more than the multiplicity upper bound, but will only offer tokens
		// up to that upper bound.]
		this.addTokens(incomingTokens);
	}
	public List<ActivityToken> takeOfferedTokens(){
		//TODO consider only taking tokens up to the upper value
		// Take only a number of tokens only up to the limit allowed by
		// the multiplicity upper bound of the pin for this activation.
		int count = this.countUnofferedTokens();
		List<ActivityToken> tokens = new ArrayList<ActivityToken>();
		// Note: upper < 0 indicates an unbounded upper multiplicity.
		if(isUpperUnlimited() || count < upper){
			List<ActivityEdgeInstance> incomingEdges = this.incomingEdges;
			for(ActivityEdgeInstance edge:incomingEdges){
				int incomingCount = edge.countOfferedValues();
				List<ActivityToken> incomingTokens = new ArrayList<ActivityToken>();
				if(isUpperUnlimited() || incomingCount < upper - count){
					incomingTokens = edge.takeOfferedTokens();
					count = count + incomingCount;
				}else if(count < upper){
					incomingTokens = edge.takeOfferedTokens(upper - count);
					count = upper;
				}
				tokens.addAll(incomingTokens);
			}
		}
		return tokens;
	}
	protected boolean isUpperUnlimited(){
		return upper ==-1;
	}
	public String getName(){
		return name;
	}
}
