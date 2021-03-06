package org.opaeum.feature;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.opaeum.feature.StepDependency.StrategyRequirement;
import org.opaeum.runtime.domain.IntrospectionUtil;

/**
 * This class will become the entry point for the entire transformation process
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class TransformationProcess{
	public static interface TransformationProgressLog{
		void startTask(String s,int size);
		void endLastTask();
		boolean isCanceled();
		void startStep(String s);
		void endLastStep();
		void error(String string,Throwable t);
		void info(String string);
		void registerInstanceCountMap(Map<Class<?>,Long> instanceCount);
	}
	Set<Object> models = new HashSet<Object>();
	Set<Class<? extends ITransformationStep>> actualClasses = new HashSet<Class<? extends ITransformationStep>>();
	StrategyCalculator strategies;
	private Phases phases;
	private OpaeumConfig config;
	public void integrate(TransformationProgressLog log){
		TransformationContext context = new TransformationContext(actualClasses, true, log,strategies);
		List<TransformationPhase<? extends ITransformationStep,?>> phaseList = getPhases();
		log.startTask("Generating Integration Code", getPhases().size());
		for(TransformationPhase<? extends ITransformationStep,?> phase:phaseList){
			if(phase instanceof IntegrationPhase){
				executePhase(context, phase);
			}
		}
		log.endLastTask();
	}
	public void executePhase(Class<? extends TransformationPhase<?,?>> phaseClass,boolean isIntegrationPhase,TransformationProgressLog log){
		TransformationContext context = new TransformationContext(actualClasses, isIntegrationPhase, log,strategies);
		for(TransformationPhase<? extends ITransformationStep,?> phase:getPhases()){
			if(phaseClass.isInstance(phase)){
				executePhase(context, phase);
			}
		}
	}
	private void executePhase(TransformationContext context,TransformationPhase<? extends ITransformationStep,?> phase){
		setInputModelsFor(phase);
		phase.initializeSteps();
		phase.execute(context);
		phase.release();
		context.featuresApplied(phase.getSteps());
	}
	public void execute(OpaeumConfig config,Object sourceModel,Set<Class<? extends ITransformationStep>> proposedStepClasses,TransformationProgressLog log){
		initialize(config, proposedStepClasses);
		models.add(sourceModel);
		execute(log);
	}
	public <T extends TransformationPhase>T getPhase(Class<T> c){
		for(TransformationPhase<? extends ITransformationStep,?> p:getPhases()){
			if(c.isInstance(p)){
				return (T) p;
			}
		}
		return null;
	}
	public List<TransformationPhase<? extends ITransformationStep,?>> getPhases(){
		return phases.getExecutionUnits();
	}
	public void execute(TransformationProgressLog log){
		TransformationContext context = new TransformationContext(actualClasses, false, log,strategies);
		List<TransformationPhase<? extends ITransformationStep,?>> phaseList = getPhases();
		log.startTask("Executing Transformation Phases", getPhases().size());
		for(TransformationPhase<? extends ITransformationStep,?> phase:phaseList){
			if(!log.isCanceled()){
				executePhase(context, phase);
			}
		}
		log.endLastTask();
	}
	public void executeFrom(Class<? extends TransformationPhase<?,?>> c,TransformationProgressLog log,boolean isRelease){
		TransformationContext context = new TransformationContext(actualClasses, false, log,strategies);
		context.setRelease(isRelease);
		List<TransformationPhase<? extends ITransformationStep,?>> phaseList = getPhases();
		List<TransformationPhase<?,?>> phases = new ArrayList<TransformationPhase<?,?>>();
		boolean start = false;
		// TODO this before/after logic will only work for a single level dependency
		PhaseDependency pd = c.getAnnotation(PhaseDependency.class);
		Set<Class<?>> before = new HashSet<Class<?>>();
		if(pd != null){
			before.addAll(Arrays.asList(pd.before()));
		}
		for(TransformationPhase<? extends ITransformationStep,?> phase:phaseList){
			PhaseDependency pd2 = phase.getClass().getAnnotation(PhaseDependency.class);
			Set<Class<?>> after = new HashSet<Class<?>>();
			if(pd2 != null){
				after.addAll(Arrays.asList(pd2.after()));
			}
			if(start || (start = c.isInstance(phase) || before.contains(phase.getClass()) || after.contains(c))){
				phases.add(phase);
			}
		}
		log.startTask("Executing Transformation Phases", phases.size());
		for(TransformationPhase<?,?> phase:phases){
			executePhase(context, phase);
		}
		log.endLastTask();
	}
	public void initialize(OpaeumConfig config,Set<Class<? extends ITransformationStep>> proposedStepClasses){
		this.models.clear();
		this.config = config;
		this.actualClasses = new HashSet<Class<? extends ITransformationStep>>();
		this.phases = new Phases();
		this.actualClasses = ensurePresenceOfDependencies(proposedStepClasses);
		strategies=new StrategyCalculator(actualClasses);
		phases.initializeFromClasses(getPhaseClassesFor(actualClasses));
		List<TransformationPhase<? extends ITransformationStep,?>> phaseList = getPhases();
		for(TransformationPhase<? extends ITransformationStep,?> phase:phaseList){
			Steps steps = new Steps();
			Class<? extends TransformationPhase<? extends ITransformationStep,?>> class1 = (Class) phase.getClass();
			steps.initializeFromClasses(getStepsForPhase(class1, actualClasses));
			List<ITransformationStep> executionUnits = steps.getExecutionUnits();
			phase.initialize(config, (List) executionUnits);
		}
		
		
		
	}
	public Collection<?> processElements(Collection changes,Class<?> fromPhase,TransformationProgressLog log){
		Set<Object> changedElements = new HashSet<Object>();
		if(changes.size() > 0){
			log.startTask("Processing Individual Elements", getPhases().size());
			changedElements.addAll(changes);
			TransformationContext context = new TransformationContext(actualClasses, false, log,strategies);
			List<TransformationPhase<? extends ITransformationStep,?>> phaseList = getPhases();
			boolean start = false;
			for(TransformationPhase phase:phaseList){
				if(!log.isCanceled() && (start || (start = fromPhase.isInstance(phase)))){
					setInputModelsFor(phase);
					phase.initializeSteps();
					changedElements.addAll(phase.processElements(context, findElementsFor(phase, changedElements)));
					phase.release();
				}
			}
			log.endLastTask();
		}
		return changedElements;
	}
	private Collection findElementsFor(TransformationPhase phase,Collection<?> changedElements){
		Collection result = new ArrayList();
		ParameterizedType typeVariable = (ParameterizedType) phase.getClass().getGenericInterfaces()[0];
		Class<?> arg1 = (Class<?>) typeVariable.getActualTypeArguments()[1];
		for(Object object:changedElements){
			if(arg1.isInstance(object)){
				result.add(object);
			}
		}
		return result;
	}
	private Set<Class<? extends ITransformationStep>> getStepsForPhase(Class<? extends TransformationPhase<? extends ITransformationStep,?>> phaseClass,
			Set<Class<? extends ITransformationStep>> stepClasses){
		Set<Class<? extends ITransformationStep>> results = new HashSet<Class<? extends ITransformationStep>>();
		for(Class<? extends ITransformationStep> stepClass:stepClasses){
			if(stepClass.getAnnotation(StepDependency.class).phase() == phaseClass){
				results.add(stepClass);
			}
		}
		return results;
	}
	private Set<Class<? extends TransformationPhase<? extends ITransformationStep,?>>> getPhaseClassesFor(Set<Class<? extends ITransformationStep>> stepClasses){
		Set<Class<? extends TransformationPhase<? extends ITransformationStep,?>>> phaseClasses = new HashSet<Class<? extends TransformationPhase<? extends ITransformationStep,?>>>();
		for(Class<? extends ITransformationStep> t:stepClasses){
			Class<? extends TransformationPhase<? extends ITransformationStep,?>> phaseClass = t.getAnnotation(StepDependency.class).phase();
			phaseClasses.add(phaseClass);
		}
		return phaseClasses;
	}
	private void setInputModelsFor(Object phase){
		if(phase != null){
			Set<Field> fields = getFields(phase);
			for(Field field:fields){
				if(field.isAnnotationPresent(InputModel.class)){
					field.setAccessible(true);
					Object existingModel = findModel(field.getType());
					try{
						if(existingModel == null && !field.getAnnotation(InputModel.class).optional()){
							existingModel = createModel(phase, field);
						}
						field.set(phase, existingModel);
					}catch(IllegalAccessException e){
						throw new RuntimeException(e);
					}catch(InstantiationException e){
						// ignore - may be set later
					}catch(IllegalStateException e){
						// ignore - may be set later
					}
				}
			}
		}
	}
	private Object createModel(Object phase,Field field) throws InstantiationException,IllegalAccessException{
		Class<?> modelClass = field.getType();
		if(modelClass.isInterface()){
			Class<?>[] implementationClass = field.getAnnotation(InputModel.class).implementationClass();
			if(implementationClass.length == 1){
				modelClass = implementationClass[0];
			}else{
				throw new IllegalStateException("No existing model found of type " + field.getType() + " to provide input for the phase  " + phase.getClass()
						+ ". Could not instantiate it because it is an interface. Please use the 'implementationClass' attribute.");
			}
		}
		Object model = modelClass.newInstance();
		this.models.add(model);
		return model;
	}
	private Set<Field> getFields(Object phase){
		Set<Field> result = new HashSet<Field>();
		Class<?> cls = phase.getClass();
		while(cls != Object.class){
			result.addAll(Arrays.asList(cls.getDeclaredFields()));
			cls = cls.getSuperclass();
		}
		return result;
	}
	public <T>T findModel(Class<T> currentClass){
		for(Object model:models){
			if(currentClass.isInstance(model)){
				return (T) model;
			}
		}
		return null;
	}
	private Set<Class<? extends ITransformationStep>> ensurePresenceOfDependencies(Set<Class<? extends ITransformationStep>> selectedFeatures){
		try{
			for(Class<? extends ITransformationStep> c:selectedFeatures){
				ensureRequiredDependenciesPresent(c);
			}
			return actualClasses;
		}catch(RuntimeException e){
			throw e;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	private void ensureRequiredDependenciesPresent(Class<? extends ITransformationStep> stepClass) throws IllegalAccessException,InstantiationException{
		if(!actualClasses.contains(stepClass)){
			actualClasses.add(stepClass);
			stepClass.newInstance();// force static inits
			StepDependency annotation = stepClass.getAnnotation(StepDependency.class);
			if(annotation == null){
				throw new IllegalStateException(stepClass.getName() + " does not have a StepDependency annotation");
			}
			Class<? extends ITransformationStep>[] requires = annotation.requires();
			for(Class<? extends ITransformationStep> c:requires){
				ensureRequiredDependenciesPresent(c);
			}
		}
	}
	public synchronized void removeModel(Class<?> class1){
		if(this.models.size() > 0){
			// CHeck for size as there is some weird negative array size problem that emerges
			Collection<Object> models = new ArrayList<Object>(this.models);
			for(Object object:models){
				if(class1.isInstance(object)){
					this.models.remove(object);
				}
			}
		}
	}
	public synchronized void replaceModel(Object model){
		removeModel(model.getClass());
		this.models.add(model);
	}
	public OpaeumConfig getConfig(){
		return this.config;
	}
	public void release(){
		config = null;
		if(phases != null){
			for(TransformationPhase<? extends ITransformationStep,?> transformationPhase:phases.getExecutionUnits()){
				transformationPhase.release();
			}
		}
		this.models.clear();
		this.actualClasses.clear();
		this.phases = null;
	}
	public <T> T getStrategy(Class<T> class1){
		return strategies.newInstance(class1);
	}
}
