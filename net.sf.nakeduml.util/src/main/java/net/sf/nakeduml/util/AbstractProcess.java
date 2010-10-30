package net.sf.nakeduml.util;
import java.util.Set;
public interface AbstractProcess {
	boolean isStepActive(AbstractProcessStep step);
	Set<AbstractProcessStep> getActiveLeafSteps();
	AbstractProcessStep getInnermostNonParallelStep();
	void forceToStep(AbstractProcessStep step);
	boolean processSignal(AbstractSignal signal);
}
