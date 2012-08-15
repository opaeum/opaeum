package org.opaeum.runtime.domain;

import java.util.Set;

public interface IToken<BE extends IBehaviorExecution> extends IPersistentObject{
	Set<IToken<BE>> getChildTokens();

	IToken<BE> getParentToken();

	void setHasRunToCompletion(boolean hasRunToCompletion);

	boolean isHasRunToCompletion();

	void setActive(boolean isActive);

	boolean isActive();

	void transferTo(IExecutionElement<BE> vertexActivation);

	IExecutionElement<BE> getCurrentExecutionElement();

	BE  getBehaviorExecution();


}
