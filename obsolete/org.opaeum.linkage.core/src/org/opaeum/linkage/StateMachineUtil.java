package org.opaeum.linkage;

import org.eclipse.uml2.uml.INakedChangeEvent;
import org.eclipse.uml2.uml.INakedState;
import org.eclipse.uml2.uml.INakedTimeEvent;
import org.eclipse.uml2.uml.INakedTransition;
import org.eclipse.uml2.uml.INakedTrigger;

public class StateMachineUtil{
	public static INakedState getHistoryPeer(INakedState state){
		for(INakedState s:state.getContainer().getStates()){
			if(s.getKind().isShallowHistory()){
				return s;
			}
		}
		return null;
	}
	public static boolean doesWorkOnEntry(INakedState state){
		return state.getEntry() != null || hasImmediateCompletionTransitions(state) || requestsEvents(state) || (needsToRecordHistory(state))
				|| state.getDoActivity() != null || state.getKind().isShallowHistory() ||state.getKind().isFinal();
	}
	private static boolean needsToRecordHistory(INakedState state){
		return(state.getKind().isRestingState() && getHistoryPeer(state) != null);
	}
	private static boolean hasImmediateCompletionTransitions(INakedState state){
		return state.getCompletionTransitions().size() > 0 && !(state.getKind().isOrthogonal() || state.getKind().isComposite());
	}
	public static boolean requestsEvents(INakedState state){
		for(INakedTransition t:state.getOutgoing()){
			for(INakedTrigger tigger:t.getTriggers()){
				if(tigger.getEvent() instanceof INakedChangeEvent || tigger.getEvent() instanceof INakedTimeEvent){
					return true;
				}
			}
		}
		return false;
	}
	public static boolean doesWorkOnExit(INakedState state){
		return state.getExit() != null || requestsEvents(state);
	}
}