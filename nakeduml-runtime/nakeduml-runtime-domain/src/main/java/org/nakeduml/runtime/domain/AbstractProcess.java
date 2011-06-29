package org.nakeduml.runtime.domain;
import java.io.Serializable;
import java.util.Set;

import org.drools.runtime.process.WorkflowProcessInstance;
public interface AbstractProcess extends ActiveObject{
	boolean isStepActive(AbstractProcessStep step);
	Set<AbstractProcessStep> getActiveLeafSteps();
	AbstractProcessStep getInnermostNonParallelStep();
	void forceToStep(AbstractProcessStep step);
	WorkflowProcessInstance getProcessInstance();
	Long getProcessInstanceId();
}
