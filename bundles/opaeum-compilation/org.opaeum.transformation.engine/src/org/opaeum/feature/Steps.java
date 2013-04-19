package org.opaeum.feature;

import java.util.HashSet;
import java.util.Set;

public class Steps extends SequenceCalculator<ITransformationStep>{
	@Override
	public void initializeFromClasses(Set<Class<? extends ITransformationStep>> selectedExecutionUnits){
		super.initializeFromClasses(selectedExecutionUnits);
//		super.initializeFromClasses(selectedExecutionUnits);
		performReplacements(selectedExecutionUnits);
	}
	private void performReplacements(Set<Class<? extends ITransformationStep>> selectedExecutionUnits){
		for(Class<? extends ITransformationStep> cls:selectedExecutionUnits){
			Class<? extends ITransformationStep>[] replaces = cls.getAnnotation(StepDependency.class).replaces();
			if(replaces.length > 0){
				ITransformationStep replacement = findStep(cls);
				if(replacement != null){
					replaceFirstMatch(replacement);
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
	private void replaceFirstMatch(ITransformationStep realReplacement){
		HashSet<ITransformationStep> remove = new HashSet<ITransformationStep>();
		int replaceIndex = indexOfItemToReplace(realReplacement.getClass(), remove);
		if(replaceIndex >= 0){
			int position = super.executionUnits.indexOf(realReplacement);
			super.executionUnits.set(replaceIndex, realReplacement);
			super.executionUnits.remove(position);
			for(ITransformationStep step:remove){
				super.executionUnits.remove(step);
			}
		}
	}
	private int indexOfItemToReplace(Class<? extends ITransformationStep> currentStep,Set<ITransformationStep> itemsToRemove){
		StepDependency ann = currentStep.getAnnotation(StepDependency.class);
		for(Class<? extends ITransformationStep> replacedStepClass:ann.replaces()){
			for(int i = 0;i < super.executionUnits.size();i++){
				ITransformationStep replacedStepInstance = super.executionUnits.get(i);
				if(replacedStepClass.equals(replacedStepInstance.getClass())){
					itemsToRemove.add(replacedStepInstance);
					if(replacedStepInstance.getClass().getAnnotation(StepDependency.class).replaces().length > 0){
						//Recursively look for steps to replace
						int index = indexOfItemToReplace(replacedStepInstance.getClass(), itemsToRemove);
						if(index == -1){
							// Not found, take current steps index
							return i;
						}else{
							return index;
						}
					}else{
						// no more transitive replacements
						return i;
					}
				}
			}
		}
		// Replaced step not found in configured steps, check if transitive replacements are in configured steps
		for(Class<? extends ITransformationStep> class1:ann.replaces()){
			int indexOfItemToReplace = indexOfItemToReplace(class1, itemsToRemove);
			if(indexOfItemToReplace > -1){
				return indexOfItemToReplace(class1, itemsToRemove);
			}
		}
		return -1;
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
