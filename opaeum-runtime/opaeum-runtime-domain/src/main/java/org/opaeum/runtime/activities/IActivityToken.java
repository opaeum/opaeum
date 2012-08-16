package org.opaeum.runtime.activities;

import org.opaeum.runtime.domain.IToken;

public interface IActivityToken<AE extends IActivityNodeContainerExecution> extends IToken<AE>{
	TokenKind getKind();
	void setKind(TokenKind k);
	void withdraw();
	Object getValue();
	void setValue(Object o);
	boolean isControl();
	IActivityToken<AE> transfer(ActivityNodeActivation<AE,?> n);
	void offeredTo(ActivityEdgeInstance<AE,?> n);
	boolean isOffered();
	boolean hasValue();
	boolean isWithdrawn();
	void unOffer();
	public abstract void setRemainingOffersCount(int remainingOffersCount);
	public abstract int getRemainingOffersCount();
	
}
