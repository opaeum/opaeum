package org.opaeum.runtime.domain;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface IBehaviorExecution<T extends IToken, EE extends IExecutionElement>{
	Collection<OutgoingEvent> getOutgoingEvents();
	Collection<CancelledEvent> getCancelledEvents();
	Set<T> getTokens();
	Map<String, EE> getExecutionElements();
}
