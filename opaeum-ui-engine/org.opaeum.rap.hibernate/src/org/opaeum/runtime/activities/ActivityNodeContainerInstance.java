package org.opaeum.runtime.activities;

import java.util.List;


public interface ActivityNodeContainerInstance{
	ActivityNodeActivation getActivityNodeActivation(String id);
	ActivityEdgeInstance getActivityEdgeInstance(String id);
	List<Token> getTokensHeldBy(ActivityNodeActivation node);
	List<Token> getTokensOfferedTo(ActivityEdgeInstance edge);
	Token createToken(TokenKind kind);
}
