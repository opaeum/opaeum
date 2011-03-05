package org.nakeduml.runtime.domain;
import java.util.Set;
public interface AbstractProcess extends ActiveObject{
	boolean isStepActive(AbstractProcessStep step);
	Set<AbstractProcessStep> getActiveLeafSteps();
	AbstractProcessStep getInnermostNonParallelStep();
	void forceToStep(AbstractProcessStep step);
}
