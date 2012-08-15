package org.opaeum.runtime.domain;
public interface IProcessObject extends IActiveObject, CompositionNode{
	@SuppressWarnings("rawtypes")
	boolean isStepActive(Class<? extends IExecutionElement> step);
}
