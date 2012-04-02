package org.opaeum.feature;

import java.util.HashSet;
import java.util.Set;

public class Steps extends SequenceCalculator<ITransformationStep>{
	@Override
	public void initializeFromClasses(Set<Class<? extends ITransformationStep>> selectedExecutionUnits){
		super.initializeFromClasses(selectedExecutionUnits);
		super.initializeFromClasses(selectedExecutionUnits);
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
		int replaceIndex = indexOfItemToReplace(realReplacement, remove);
		if(replaceIndex >= 0){
			int position = super.executionUnits.indexOf(realReplacement);
			if(position >= replaceIndex){
				super.executionUnits.set(replaceIndex, realReplacement);
			}else{
				// rather remove the later occurring replaced item. Ideally this should not happen
				super.executionUnits.remove(replaceIndex);
			}
			super.executionUnits.remove(position);
			for(ITransformationStep step:remove){
				super.executionUnits.remove(step);
			}
		}
	}
	private int indexOfItemToReplace(ITransformationStep currentStep,Set<ITransformationStep> itemsToRemove){
		StepDependency ann = currentStep.getClass().getAnnotation(StepDependency.class);
		for(Class<? extends ITransformationStep> class1:ann.replaces()){
			for(int i = 0;i < super.executionUnits.size();i++){
				ITransformationStep step = super.executionUnits.get(i);
				if(class1.equals(step.getClass())){
					itemsToRemove.add(step);
					if(step.getClass().getAnnotation(StepDependency.class).replaces().length > 0){
						int index = indexOfItemToReplace(step, itemsToRemove);
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
