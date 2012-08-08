package org.opaeum.runtime.activities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class ExpansionRegionActivation extends ActionActivation{
	public ExpansionRegionActivation(ActivityNodeContainerInstance group,String id){
		super(group, id);
	}
	private List<ExpansionNodeActivation> inputNodeActivations = new ArrayList<ExpansionNodeActivation>();
	private List<ExpansionNodeActivation> outputNodeActivations = new ArrayList<ExpansionNodeActivation>();
	public List<Set<Token>> inputTokens = new ArrayList<Set<Token>>();
	public List<Set<Token>> inputExpansionTokens = new ArrayList<Set<Token>>();
	public abstract List<ExpansionRegionExecution> getActivationGroups();
	public List<Token> takeOfferedTokens(){
		return new ArrayList<Token>();
	}
	public void doAction(){
		// If the expansion region has mustIsolate=true, then carry out its
		// behavior with isolation.
		// Otherwise just activate it normally.
		this.doStructuredActivity();
	}
	public void doStructuredActivity(){
		// Create a number of expansion region activation groups equal to the
		// number of values expanded in the region,
		// setting the region inputs and group inputs for each group.
		// Run the body of the region in each group, either iteratively or in
		// parallel.
		// Add the outputs of each activation group to the corresonding output
		// expansion node activations.
	}
	public void terminate(){
		// Terminate the execution of all contained node activations (which
		// completes the performance of the expansion region activation).
		List<ExpansionRegionExecution> activationGroups = this.getActivationGroups();
		for(ExpansionRegionExecution expansionRegionExecution:activationGroups){
			expansionRegionExecution.terminateAll();
		}
		super.terminate();
	}
	public boolean isReady(){
		// In addition to the usual ready checks for an action, check that all
		// expansion nodes have the same number of inputs (greater than zero).
		boolean ready = super.isReady();
		if(ready){
			int n = this.numberOfValues(); // This gets the number of values on
			// the first expansion node.
			if(n < 1){
				return false;
			}else{
				int i = 1;
				while(ready & i <= inputNodeActivations.size()){
					ready = inputNodeActivations.get(i - 1).countOfferedValues() == n;
					i = i + 1;
				}
			}
		}
		return ready;
	}
	public void sendOffers(){
		// Fire all output expansion nodes and send offers on all outgoing
		// control flows.
		List<ExpansionNodeActivation> list = this.outputNodeActivations;
		for(ExpansionNodeActivation expansionNodeActivation:list){
			expansionNodeActivation.sendUnofferedTokens();
		}
		// Send offers on all outgoing control flows.
		super.sendOffers();
	}
	public ExpansionNodeActivation getExpansionNodeActivation(String node){
		// Return the expansion node activation corresponding to the given
		// expansion node, in the context of the activity node activation group
		// this expansion region activation is in.
		// [Note: Expansion regions do not own their expansion nodes. Instead,
		// they are own as object nodes by the enclosing activity or group.
		// Therefore, they will already be activated along with their expansion
		// region.]
		for(ExpansionNodeActivation a:this.inputNodeActivations){
			if(a.getName().equals(node)){
				return a;
			}
		}
		return null;
	}
	public int numberOfValues(){
		// Return the number of values on the first input expansion node of the
		// expansion region of this activation.
		// (The region is required to have at least one input expansion node.)
		return inputNodeActivations.get(0).countOfferedValues();
	}
}
