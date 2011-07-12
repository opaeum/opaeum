package net.sf.nakeduml.linkage;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;

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
			if(t.getTrigger() != null && t.getTrigger().getEvent() instanceof INakedEvent){
				return true;
			}
		}
		return false;
	}
	public static boolean doesWorkOnExit(INakedState state){
		return state.getExit() != null || requestsEvents(state);
	}
}
