package net.sf.nakeduml.feature;

public class Phases extends SequenceCalculator<TransformationPhase<? extends ITransformationStep,?>> {
	@Override
	protected BeforeAndAfter createStepAndPredecessor(Class<? extends TransformationPhase<? extends ITransformationStep,?>> step) {
		PhaseDependency phaseDependency = step.getAnnotation(PhaseDependency.class);
		if (phaseDependency == null) {
			throw new IllegalArgumentException("Class " + step + " has no PhaseDependency annotation");
		}
		return new BeforeAndAfter(step, phaseDependency.before(), phaseDependency.after());
	}
}
