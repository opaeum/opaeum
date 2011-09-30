package org.opeum.feature;

import java.util.Set;

public class Steps extends SequenceCalculator<ITransformationStep>{
	@Override
	public void initializeFromClasses(Set<Class<? extends ITransformationStep>> selectedExecutionUnits){
		super.initializeFromClasses(selectedExecutionUnits);
		super.initializeFromClasses(selectedExecutionUnits);
		for(Class<? extends ITransformationStep> cls:selectedExecutionUnits){
			Class<? extends ITransformationStep>[] replaces = cls.getAnnotation(StepDependency.class).replaces();
			if(replaces.length > 0){
				ITransformationStep replacement = findStep(cls);
				replaceFirstMatch(replacement);
				for(Class<? extends ITransformationStep> replace:replaces){
					ITransformationStep findStep = findStep(replace);
					if(findStep != replacement){
						executionUnits.remove(findStep);
					}
				}
			}
		}
	}
	private ITransformationStep findStep(Class<? extends ITransformationStep> cls){
		ITransformationStep replacement = null;
		for(ITransformationStep step:super.executionUnits){
			if(cls.isInstance(step)){
				replacement = step;
				break;
			}
		}
		return replacement;
	}
	private void replaceFirstMatch(ITransformationStep replacement){
		int position=super.executionUnits.indexOf(replacement);
		super.executionUnits.remove(replacement);
		for(Class<? extends ITransformationStep> class1:replacement.getClass().getAnnotation(StepDependency.class).replaces()){
			for(int i = 0;i < super.executionUnits.size();i++){
				ITransformationStep step = super.executionUnits.get(i);
				if(class1.equals(step.getClass())){
					super.executionUnits.set(i, replacement);
					break;
				}
			}
		}
		if(!super.executionUnits.contains(replacement)){
			super.executionUnits.add(position,replacement);
		}
	}
	@Override
	protected BeforeAndAfter createStepAndPredecessor(Class<? extends ITransformationStep> step){
		StepDependency fd = step.getAnnotation(StepDependency.class);
		if(fd == null){
			throw new IllegalArgumentException("Class " + step.getName() + " does not have a StepDependency annotation");
		}
		return new BeforeAndAfter(step, fd.before(), fd.after());
	}
}
