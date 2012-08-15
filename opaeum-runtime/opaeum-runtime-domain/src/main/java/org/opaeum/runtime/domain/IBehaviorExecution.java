package org.opaeum.runtime.domain;

import java.util.Map;
import java.util.Set;

@SuppressWarnings("rawtypes")
public interface IBehaviorExecution extends IEventGenerator{
	Set<OutgoingEvent> getOutgoingEvents();
	Set<CancelledEvent> getCancelledEvents();
	void removeToken(IToken childToken);
	IToken createToken(IToken parentToken);
	Set<IToken> getTokens();
	Map<String, IExecutionElement> getExecutionElements();
}
