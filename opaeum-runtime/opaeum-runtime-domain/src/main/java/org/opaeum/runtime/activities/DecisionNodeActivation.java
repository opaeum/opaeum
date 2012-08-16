package org.opaeum.runtime.activities;

import java.util.ArrayList;
import java.util.List;

public class DecisionNodeActivation<AE extends IActivityNodeContainerExecution, T extends IActivityToken<AE>> extends ControlNodeActivation<AE,T>{
	private ActivityEdgeInstance<AE,T> decisionInputFlow;
	private DecisionInputBehavior  decisionInputBehavior;
	public DecisionNodeActivation(AE group,String id){
		super(group, id);
	}
	public void fire(List<T> incomingTokens){
		// Get the decision values and test them on each guard.
		// Forward the offer over the edges for which the test succeeds.
		// List<Token> incomingTokens = this.takeOfferedTokens();
		List<T> removedControlTokens = this.removeJoinedControlTokens(incomingTokens);
		List<ActivityEdgeInstance<AE,T>> outgoingEdges = this.outgoingEdges;
		for(ActivityEdgeInstance<AE,T> edgeInstance:outgoingEdges){
			
			Object guard = edgeInstance.evaluateGuard();
			List<T> offeredTokens = new ArrayList<T>();
			for(T incomingToken:incomingTokens){
				
				Object decisionValue = getDecisionValues(incomingToken);
				if(Boolean.TRUE.equals(guard) ||  guard.equals(decisionValue)){
					offeredTokens.add(incomingToken);
				}
			}
			if(offeredTokens.size() > 0){
				for(int j = 0;j < removedControlTokens.size();j++){
					T removedControlToken = removedControlTokens.get(j);
					offeredTokens.add(removedControlToken);
				}
				edgeInstance.sendOffer(offeredTokens);
			}
		}
	}
	public Object getDecisionValues(T incomingToken){
		// If there is neither a decision input flow nor a decision input
		// behavior, then return the set of values from the incoming tokens.
		// [In this case, the single incoming edge must be an object flow.]
		// If there is a decision input flow, but no decision input behavior,
		// then return a list of the decision input values equal in size to the
		// number of incoming tokens.
		// If there is both a decision input flow and a decision input behavior,
		// then execute the decision input behavior once for each incoming token
		// and return the set of resulting values.
		// If the primary incoming edge is an object flow, then the value on
		// each object token is passed to the decision input behavior, along
		// with the decision input flow value, if any.
		// If the primary incoming edge is a control flow, then the decision
		// input behavior only receives the decision input flow, if any.
		Object decisionInputValue = this.getDecisionInputFlowValue();
		return this.executeDecisionInputBehavior(incomingToken.getValue(), decisionInputValue);
	}
	public Object executeDecisionInputBehavior(Object inputValue,Object decisionInputValue){
		// Create the decision input execution from the decision input behavior.
		// If the behavior has input parameter(s), set the input parameter(s) of
		// the execution to the given value(s).
		// Execute the decision input execution and then remove it.
		// Return the value of the output parameter of the execution.
		// If there is no decision input behavior, the decision input value is
		// returned, if one is given, otherwise the input value is used as the
		// decision value.
		Object decisionInputResult = null;
		if(decisionInputBehavior == null){
			if(decisionInputValue != null){
				decisionInputResult = decisionInputValue;
			}else{
				decisionInputResult = inputValue;
			}
		}else{
			decisionInputResult= this.decisionInputBehavior.execute(inputValue,decisionInputValue);
		}
		return decisionInputResult;
	}
	public boolean isReady(){
		// Check that all incoming edges have sources that are offering tokens.
		// [This should be at most two incoming edges, if there is a decision
		// input flow.]
		int i = 1;
		boolean ready = true;
		while(ready & i <= this.incomingEdges.size()){
			ready = this.incomingEdges.get(i - 1).hasOffer();
			i = i + 1;
		}
		return ready;
	}
	public List<T> takeOfferedTokens(){
		// Get tokens from the incoming edge that is not the decision input
		// flow.
		List<T> allTokens = new ArrayList<T>();
		List<ActivityEdgeInstance<AE,T>> incomingEdges = this.incomingEdges;
		for(ActivityEdgeInstance<AE,T> edgeInstance:incomingEdges){
			if(edgeInstance != decisionInputFlow){
				List<T> tokens = edgeInstance.takeOfferedTokens();
				allTokens.addAll(tokens);
			}
		}
		return allTokens;
	}
	public Object getDecisionInputFlowValue(){
		// Take the next token available on the decision input flow, if any, and
		// return its value.
		ActivityEdgeInstance<AE,T> decisionInputFlowInstance = this.getDecisionInputFlowInstance();
		Object value = null;
		if(decisionInputFlowInstance != null){
			List<T> tokens = decisionInputFlowInstance.takeOfferedTokens();
			if(tokens.size() > 0){
				value = tokens.get(0).getValue();
			}
		}
		return value;
	}
	public ActivityEdgeInstance<AE,T> getDecisionInputFlowInstance(){
		return decisionInputFlow;
	}
	public List<T> removeJoinedControlTokens(List<T> incomingTokens){
		// If the primary incoming edge is an object flow, then remove any
		// control tokens from the incoming tokens and return them.
		// [Control tokens may effectively be offered on an object flow outgoing
		// from a join node that has both control and object flows incoming.]
		List<T> removedControlTokens = new ArrayList<T>();
		if(this.hasObjectFlowInput()){
			int i = 1;
			while(i <= incomingTokens.size()){
				T token = incomingTokens.get(i - 1);
				if(token.isControl()){
					removedControlTokens.add(token);
					incomingTokens.remove(i - 1);
					i = i - 1;
				}
				i = i + 1;
			}
		}
		return removedControlTokens;
	}
	public boolean hasObjectFlowInput(){
		for(ActivityEdgeInstance<AE,T> e:incomingEdges){
			if(e != decisionInputFlow && e.isObjectFlow()){
				return true;
			}
		}
		return false;
	}
}
