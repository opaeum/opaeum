package net.sf.nakeduml.util;
import java.util.List;
import java.util.Set;
public interface AbstractProcess {
	public abstract boolean isStepActive(AbstractProcessStep step);
	public abstract Set<AbstractProcessStep> getActiveLeafSteps();
	public abstract AbstractProcessStep getInnermostNonParallelStep();
	public abstract void forceToStep(AbstractProcessStep step);
}
