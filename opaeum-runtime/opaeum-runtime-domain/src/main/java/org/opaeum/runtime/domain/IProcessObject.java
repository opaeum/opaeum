package org.opaeum.runtime.domain;
import java.util.Set;

import org.drools.runtime.process.WorkflowProcessInstance;
public interface IProcessObject extends IActiveObject, CompositionNode{
	boolean isStepActive(IProcessStep step);
	Set<IProcessStep> getActiveLeafSteps();
	IProcessStep getInnermostNonParallelStep();
	void forceToStep(IProcessStep step);
	WorkflowProcessInstance getProcessInstance();
	Long getProcessInstanceId();
	boolean isProcessDirty();
}
