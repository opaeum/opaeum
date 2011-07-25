package net.sf.nakeduml.feature;

public class Phases extends SequenceCalculator<TransformationPhase<? extends TransformationStep,?>> {
	@Override
	protected BeforeAndAfter createStepAndPredecessor(TransformationPhase<? extends TransformationStep,?> step) {
		PhaseDependency phaseDependency = step.getClass().getAnnotation(PhaseDependency.class);
		if (phaseDependency == null) {
			throw new IllegalArgumentException("Class " + step.getClass() + " has no PhaseDependency annotation");
		}
		return new BeforeAndAfter(step, phaseDependency.before(), phaseDependency.after());
	}
}
