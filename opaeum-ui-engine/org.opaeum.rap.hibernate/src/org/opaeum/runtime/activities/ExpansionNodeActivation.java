package org.opaeum.runtime.activities;

import java.util.List;

public class ExpansionNodeActivation extends ObjectNodeActivation{
	String name;
	public ExpansionNodeActivation(IActivityNodeContainerExecution group,String id, String name){
		super(group, id);
		this.name=name;
	}
	private ExpansionRegionActivation expansionRegionActivation;
	public void fire(List<ActivityToken> incomingTokens){
		this.addTokens(incomingTokens);
	}
	public void receiveOffer(){
		this.getExpansionRegionActivation().receiveOffer();
	}
	public boolean isReady(){
		return false;
	}
	public ExpansionRegionActivation getExpansionRegionActivation(){
		return expansionRegionActivation;
	}
	public String getName(){
		return name;
	}
}
