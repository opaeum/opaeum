package org.opaeum.runtime.domain;

public interface IToken extends IPersistentObject{

	public abstract void setHasRunToCompletion(boolean hasRunToCompletion);

	public abstract boolean isHasRunToCompletion();

	public abstract void setActive(boolean isActive);

	public abstract boolean isActive();

	public abstract void transferTo(IExecutionElement vertexActivation);

	public abstract IExecutionElement getCurrentExecutionElement();

	public abstract IBehaviorExecution getBehaviorExecution();

	public abstract String getClassIdentifier();

}
