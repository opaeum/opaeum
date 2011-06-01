package org.nakeduml.runtime.domain;
import java.util.Set;

import org.drools.runtime.process.WorkflowProcessInstance;
public interface IProcessObject extends IActiveObject{
	boolean isStepActive(IProcessStep step);
	Set<IProcessStep> getActiveLeafSteps();
	IProcessStep getInnermostNonParallelStep();
	void forceToStep(IProcessStep step);
	WorkflowProcessInstance getProcessInstance();
}
