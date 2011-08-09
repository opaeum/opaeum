package net.sf.nakeduml.feature;





public class Steps extends SequenceCalculator<ITransformationStep> {

	@Override
	protected BeforeAndAfter createStepAndPredecessor(Class<? extends ITransformationStep> step) {
		StepDependency fd = step.getAnnotation(StepDependency.class);
		if (fd == null) {
			throw new IllegalArgumentException("Class " + step.getName() + " does not have a StepDependency annotation");
		}
		return new BeforeAndAfter(step, fd.before(), fd.after());
	}


}
