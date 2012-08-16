package org.opaeum.runtime.activities;

import java.util.List;

import org.opaeum.runtime.domain.IBehaviorExecution;

@SuppressWarnings("rawtypes")
public interface IActivityNodeContainerExecution extends IBehaviorExecution{
	IActivityNodeActivation getActivityNodeActivation(String id);
	IActivityEdgeInstance getActivityEdgeInstance(String id);
	<AE extends IActivityNodeContainerExecution,T extends IActivityToken<AE>> List<T> getTokensHeldBy(IActivityNodeActivation<AE,T> node);
	<AE extends IActivityNodeContainerExecution,T extends IActivityToken<AE>>  List<T> getTokensOfferedTo(IActivityEdgeInstance<AE,T> edge);
//	IActivityToken createToken(TokenKind kind);
	void terminateAll();
}
