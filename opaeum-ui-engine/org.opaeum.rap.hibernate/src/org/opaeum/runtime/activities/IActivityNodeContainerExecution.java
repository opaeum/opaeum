package org.opaeum.runtime.activities;

import java.util.List;

import org.opaeum.hibernate.domain.IBehaviorExecution;


public interface IActivityNodeContainerExecution extends IBehaviorExecution{
	ActivityNodeActivation getActivityNodeActivation(String id);
	ActivityEdgeInstance getActivityEdgeInstance(String id);
	List<ActivityToken> getTokensHeldBy(ActivityNodeActivation node);
	List<ActivityToken> getTokensOfferedTo(ActivityEdgeInstance edge);
	ActivityToken createToken(TokenKind kind);

	void terminateAll();
}
