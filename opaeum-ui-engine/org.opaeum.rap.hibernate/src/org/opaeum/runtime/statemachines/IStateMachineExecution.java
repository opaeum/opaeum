package org.opaeum.runtime.statemachines;

import java.util.Map;
import java.util.Set;

import org.opaeum.hibernate.domain.IBehaviorExecution;

public interface IStateMachineExecution extends IBehaviorExecution{
	Set<? extends StateMachineToken> getTokens();
	Map<String,IStateMachineExecutionElement> getExecutionElements();
	void removeToken(StateMachineToken childToken);
	StateMachineToken createToken(StateMachineToken parentToken);
}
