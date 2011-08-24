package net.sf.nakeduml.feature;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

/**
 * This class will become the entry point for the entire transformation process
 * 
 */
@SuppressWarnings({
		"unchecked","rawtypes"
})
public class TransformationProcess{
	public static interface TransformationProgressLog{
		void startTask(String s,int size);
		void endTask();
		boolean isCanceled();
		void workOnStep(String s);
	}
	Set<Object> models = new HashSet<Object>();
	Set<Object> changedElements = new HashSet<Object>();
	Set<Class<? extends ITransformationStep>> actualClasses = new HashSet<Class<? extends ITransformationStep>>();
	private Phases phases;
	private TransformationProgressLog log = new TransformationProgressLog(){
		String lastTask;
		long lastStart = System.currentTimeMillis();
		@Override
		public void startTask(String s,int size){
			System.out.println(s);
			lastTask = s;
			lastStart = System.currentTimeMillis();
		}
		public void workOnStep(String s){
			System.out.println(s);
		}
		@Override
		public void endTask(){
			System.out.println(lastTask + " took " + (System.currentTimeMillis() - lastStart) + " ms");
		}
		@Override
		public boolean isCanceled(){
			return false;
		}
	};
	private NakedUmlConfig config;
	private boolean cancelled;
	public TransformationProgressLog getLog(){
		return log;
	}
	public void setLog(TransformationProgressLog log){
		this.log = log;
	}
	public void integrate(){
		TransformationContext context = new TransformationContext(actualClasses, true);
		List<TransformationPhase<? extends ITransformationStep,?>> phaseList = getPhases();
		log.startTask("Generating Integration Code", getPhases().size());
		for(TransformationPhase<? extends ITransformationStep,?> phase:phaseList){
			log.workOnStep("Executing " + phase.getClass().getSimpleName());
			if(phase instanceof IntegrationPhase){
				executePhase(context, phase);
			}
		}
		log.endTask();
	}
	public void executePhase(Class<? extends TransformationPhase<?,?>> phaseClass,boolean isIntegrationPhase){
		for(TransformationPhase<? extends ITransformationStep,?> phase:getPhases()){
			if(phaseClass.isInstance(phase)){
				executePhase(new TransformationContext(actualClasses, isIntegrationPhase), phase);
			}
		}
	}
	private void executePhase(TransformationContext context,TransformationPhase<? extends ITransformationStep,?> phase){
		setInputModelsFor(phase);
		phase.initializeSteps();
		phase.execute(log, context);
		context.featuresApplied(phase.getSteps());
	}
	public void execute(NakedUmlConfig config,Object sourceModel,Set<Class<? extends ITransformationStep>> proposedStepClasses){
		initialize(config, proposedStepClasses);
		models.add(sourceModel);
		execute();
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
	public void execute(){
		TransformationContext context = new TransformationContext(actualClasses, false);
		List<TransformationPhase<? extends ITransformationStep,?>> phaseList = getPhases();
		log.startTask("Executing Transformation Phases", getPhases().size());
		for(TransformationPhase<? extends ITransformationStep,?> phase:phaseList){
			if(!log.isCanceled()){
				log.workOnStep("Executing Phase " + phase.getClass().getSimpleName());
				executePhase(context, phase);
			}
		}
		log.endTask();
	}
	public void executeFrom(Class<? extends TransformationPhase<?,?>> c){
		TransformationContext context = new TransformationContext(actualClasses, false);
		List<TransformationPhase<? extends ITransformationStep,?>> phaseList = getPhases();
		log.startTask("Executing Transformation Phases", getPhases().size());
		boolean start = false;
		for(TransformationPhase<? extends ITransformationStep,?> phase:phaseList){
			log.workOnStep("Executing Phase " + phase.getClass().getSimpleName());
			if(start || (start = c.isInstance(phase))){
				executePhase(context, phase);
			}
		}
		log.endTask();
	}
	public void initialize(NakedUmlConfig config,Set<Class<? extends ITransformationStep>> proposedStepClasses){
		this.models.clear();
		this.config = config;
		this.actualClasses = new HashSet<Class<? extends ITransformationStep>>();
		this.phases = new Phases();
		this.actualClasses = ensurePresenceOfDependencies(proposedStepClasses);
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
	public Collection<?> processElements(Collection changes,Class<?> fromPhase){
		this.changedElements.clear();
		if(changes.size() > 0){
			log.startTask("Processing Individual Elements", getPhases().size());
			this.changedElements.addAll(changes);
			TransformationContext context = new TransformationContext(actualClasses, false);
			List<TransformationPhase<? extends ITransformationStep,?>> phaseList = getPhases();
			boolean start = false;
			for(TransformationPhase phase:phaseList){
				if(start || (start = fromPhase.isInstance(phase))){
					setInputModelsFor(phase);
					phase.initializeSteps();
					this.changedElements.addAll(phase.processElements(context, findElementsFor(phase)));
				}
			}
			log.endTask();
		}
		return this.changedElements;
	}
	private Collection findElementsFor(TransformationPhase phase){
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
						if(existingModel == null){
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
			for(Class<? extends ITransformationStep> c:selectedFeatures){
				StepDependency annotation = c.getAnnotation(StepDependency.class);
				for(Class<? extends ITransformationStep> replaced:annotation.replaces()){
					selectedFeatures.remove(replaced);
				}
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
	public void removeModel(Class<?> class1){
		Collection<Object> models = new ArrayList<Object>(this.models);
		for(Object object:models){
			if(class1.isInstance(object)){
				this.models.remove(object);
			}
		}
	}
	public void replaceModel(Object model){
		removeModel(model.getClass());
		this.models.add(model);
	}
}
