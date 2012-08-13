package org.opaeum.runtime.domain;

import java.util.Map;
import java.util.Set;

public interface IBehaviorExecution{
	Set<? extends IToken> getTokens();
	Map<String, ? extends IExecutionElement> getExecutionElements();
}
