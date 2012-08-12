package org.opaeum.hibernate.domain;

import java.util.Set;

public interface IBehaviorExecution{
	Set<? extends AbstractToken> getTokens();
}
