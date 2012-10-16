package org.opaeum.runtime.domain;
import java.util.Set;
public interface IProcessObject extends IActiveObject, CompositionNode{
	boolean isStepActive(IProcessStep step);
	Set<IProcessStep> getActiveLeafSteps();
	IProcessStep getInnermostNonParallelStep();
	void forceToStep(IProcessStep step);
}
