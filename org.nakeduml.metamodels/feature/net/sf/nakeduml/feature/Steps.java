package net.sf.nakeduml.feature;





public class Steps extends SequenceCalculator<TransformationStep> {

	@Override
	protected BeforeAndAfter createStepAndPredecessor(TransformationStep step) {
		StepDependency fd = step.getClass().getAnnotation(StepDependency.class);
		if (fd == null) {
			throw new IllegalArgumentException("Class " + step.getClass().getName() + " does not have a StepDependency annotation");
		}
		return new BeforeAndAfter(step, fd.before(), fd.after());
	}


}
