package org.opaeum.runtime.domain;
public interface IProcessObjectBase extends IActiveObject, CompositionNode{
	@SuppressWarnings("rawtypes")
	boolean isStepActive(Class<? extends IExecutionElement> step);
	IProcessStep getInnermostNonParallelStep();
}
