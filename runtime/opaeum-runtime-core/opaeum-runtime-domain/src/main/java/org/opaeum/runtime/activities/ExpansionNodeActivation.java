package org.opaeum.runtime.activities;

import java.util.List;

public class ExpansionNodeActivation<AE extends IActivityNodeContainerExecution, T extends IActivityToken<AE>> extends ObjectNodeActivation<AE,T>{
	String name;
	public ExpansionNodeActivation(AE group,String id, String name){
		super(group, id);
		this.name=name;
	}
	private ExpansionRegionActivation<AE,T> expansionRegionActivation;
	public void fire(List<T> incomingTokens){
		this.addTokens(incomingTokens);
	}
	public void receiveOffer(){
		this.getExpansionRegionActivation().receiveOffer();
	}
	public boolean isReady(){
		return false;
	}
	public ExpansionRegionActivation<AE,T> getExpansionRegionActivation(){
		return expansionRegionActivation;
	}
	public String getName(){
		return name;
	}
}
