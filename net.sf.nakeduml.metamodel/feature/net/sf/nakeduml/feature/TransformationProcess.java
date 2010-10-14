package net.sf.nakeduml.feature;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * This class will become the entry point for the entire transformation process
 * 
 * 
 * @author abarnard
 * 
 */
public class TransformationProcess {
	Set<Object> models = new HashSet<Object>();
	Set<Class<? extends TransformationStep>> actualClasses = new HashSet<Class<? extends TransformationStep>>();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(NakedUmlConfig config, Object sourceModel, Set<Class<? extends TransformationStep>> proposedStepClasses) {
		replaceModels(sourceModel);
		Phases phases = new Phases();
		this.actualClasses = ensurePresenceOfDependencies(proposedStepClasses);
		phases.initializeFromClasses(getPhaseClassesFor(actualClasses));
		List<TransformationPhase<? extends TransformationStep>> phaseList = phases.getExecutionUnits();
		for (TransformationPhase<? extends TransformationStep> phase : phaseList) {
			setInputModelsFor(phase);
			phase.initialize(config);
			Class<? extends TransformationPhase<? extends TransformationStep>> class1 = (Class) phase.getClass();
			Steps steps  = new Steps();
			steps.initializeFromClasses(getStepsForPhase(class1,actualClasses));
			List featuresFor = steps.getExecutionUnits();
			System.out.println("Executing phase " + phase.getClass() + " .... ");
			long time = System.currentTimeMillis();
			Object[] execute = phase.execute(featuresFor);
			replaceModels(execute);
			System.out.println("Executing phase " + phase.getClass() + " took " + (System.currentTimeMillis() - time) + "ms");
		}
	}


	private Set<Class<? extends TransformationStep>> getStepsForPhase(
			Class<? extends TransformationPhase<? extends TransformationStep>> phaseClass, Set<Class<? extends TransformationStep>> stepClasses) {
		Set<Class<? extends TransformationStep>> results = new HashSet<Class<? extends TransformationStep>>();
		for (Class<? extends TransformationStep> stepClass : stepClasses) {
			if(stepClass.getAnnotation(StepDependency.class).phase()==phaseClass){
				results.add(stepClass);
			}
		}
		return results;
	}


	private Set<Class<? extends TransformationPhase<? extends TransformationStep>>> getPhaseClassesFor(
			Set<Class<? extends TransformationStep>> stepClasses) {
		Set<Class<? extends TransformationPhase<? extends TransformationStep>>> phaseClasses = new HashSet<Class<? extends TransformationPhase<? extends TransformationStep>>>();
		for (Class<? extends TransformationStep> t : stepClasses) {
			Class<? extends TransformationPhase<? extends TransformationStep>> phaseClass = t.getAnnotation(StepDependency.class).phase();
			phaseClasses.add(phaseClass);
		}
		return phaseClasses;
	}

	private void setInputModelsFor(TransformationPhase<? extends TransformationStep> phase) {
		Field[] fields = getFields(phase);
		for (Field field : fields) {
			if (field.isAnnotationPresent(InputModel.class)) {
				field.setAccessible(true);
				Object existingModel = findModel(field.getType());
				try {
					if (existingModel == null) {
						existingModel = createModel(phase, field);
					}
					field.set(phase, existingModel);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (InstantiationException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	private Object createModel(TransformationPhase<? extends TransformationStep> phase, Field field) throws InstantiationException,
			IllegalAccessException {
		Class<?> modelClass = field.getType();
		if (modelClass.isInterface()) {
			Class<?>[] implementationClass = field.getAnnotation(InputModel.class).implementationClass();
			if (implementationClass.length == 1) {
				modelClass = implementationClass[0];
			} else {
				throw new IllegalStateException("No existing model found of type " + field.getType() + " to provide input for the phase  "
						+ phase.getClass()
						+ ". Could not instantiate it because it is an interface. Please use the 'implementationClass' attribute.");
			}
		}
		Object model = modelClass.newInstance();
		this.models.add(model);
		return model;
	}

	private Field[] getFields(TransformationPhase<? extends TransformationStep> phase) {
		return phase.getClass().getDeclaredFields();
	}

	private void replaceModels(Object... models) {
		for (Object newModel : models) {
			Iterator<Object> iter = this.models.iterator();
			inner: while (iter.hasNext()) {
				Object oldModel = iter.next();
				if (newModel.getClass().isInstance(oldModel)) {
					iter.remove();
					break inner;
				}
			}
			this.models.add(newModel);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T findModel(Class<T> currentClass) {
		for (Object model : models) {
			if (currentClass.isInstance(model)) {
				return (T) model;
			}
		}
		return null;
	}
	public Set<Class<? extends TransformationStep>> ensurePresenceOfDependencies(Set<Class<? extends TransformationStep>> selectedFeatures) {
		try {
			for (Class<? extends TransformationStep> c : selectedFeatures) {
				ensureRequiredDependenciesPresent(c);
			}
			return actualClasses;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void ensureRequiredDependenciesPresent(Class<? extends TransformationStep> stepClass) throws IllegalAccessException, InstantiationException {
		if (!actualClasses.contains(stepClass)) {
			actualClasses.add(stepClass);
			for (Class<? extends TransformationStep> c : stepClass.getAnnotation(StepDependency.class).requires()) {
				ensureRequiredDependenciesPresent(c);
			}
		}
	}
}
