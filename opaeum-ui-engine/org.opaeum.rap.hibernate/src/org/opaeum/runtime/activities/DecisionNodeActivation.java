package org.opaeum.runtime.activities;

import java.util.ArrayList;
import java.util.List;

import org.drools.command.GetVariableCommand;

public class DecisionNodeActivation extends ControlNodeActivation{
	private ActivityEdgeInstance decisionInputFlow;
	private DecisionInputBehavior  decisionInputBehavior;
	public DecisionNodeActivation(ActivityNodeContainerInstance group,String id){
		super(group, id);
	}
	public void fire(List<Token> incomingTokens){
		// Get the decision values and test them on each guard.
		// Forward the offer over the edges for which the test succeeds.
		// List<Token> incomingTokens = this.takeOfferedTokens();
		List<Token> removedControlTokens = this.removeJoinedControlTokens(incomingTokens);
		List<ActivityEdgeInstance> outgoingEdges = this.outgoingEdges;
		for(ActivityEdgeInstance edgeInstance:outgoingEdges){
			
			Object guard = edgeInstance.evaluateGuard();
			List<Token> offeredTokens = new ArrayList<Token>();
			for(Token incomingToken:incomingTokens){
				
				Object decisionValue = getDecisionValues(incomingToken);
				if(Boolean.TRUE.equals(guard) ||  guard.equals(decisionValue)){
					offeredTokens.add(incomingToken);
				}
			}
			if(offeredTokens.size() > 0){
				for(int j = 0;j < removedControlTokens.size();j++){
					Token removedControlToken = removedControlTokens.get(j);
					offeredTokens.add(removedControlToken);
				}
				edgeInstance.sendOffer(offeredTokens);
			}
		}
	}
	public Object getDecisionValues(Token incomingToken){
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
	public List<Token> takeOfferedTokens(){
		// Get tokens from the incoming edge that is not the decision input
		// flow.
		List<Token> allTokens = new ArrayList<Token>();
		List<ActivityEdgeInstance> incomingEdges = this.incomingEdges;
		for(ActivityEdgeInstance edgeInstance:incomingEdges){
			if(edgeInstance != decisionInputFlow){
				List<Token> tokens = edgeInstance.takeOfferedTokens();
				allTokens.addAll(tokens);
			}
		}
		return allTokens;
	}
	public Object getDecisionInputFlowValue(){
		// Take the next token available on the decision input flow, if any, and
		// return its value.
		ActivityEdgeInstance decisionInputFlowInstance = this.getDecisionInputFlowInstance();
		Object value = null;
		if(decisionInputFlowInstance != null){
			List<Token> tokens = decisionInputFlowInstance.takeOfferedTokens();
			if(tokens.size() > 0){
				value = tokens.get(0).getValue();
			}
		}
		return value;
	}
	public ActivityEdgeInstance getDecisionInputFlowInstance(){
		return decisionInputFlow;
	}
	public List<Token> removeJoinedControlTokens(List<Token> incomingTokens){
		// If the primary incoming edge is an object flow, then remove any
		// control tokens from the incoming tokens and return them.
		// [Control tokens may effectively be offered on an object flow outgoing
		// from a join node that has both control and object flows incoming.]
		List<Token> removedControlTokens = new ArrayList<Token>();
		if(this.hasObjectFlowInput()){
			int i = 1;
			while(i <= incomingTokens.size()){
				Token token = incomingTokens.get(i - 1);
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
		for(ActivityEdgeInstance e:incomingEdges){
			if(e != decisionInputFlow && e.isObjectFlow()){
				return true;
			}
		}
		return false;
	}
}
