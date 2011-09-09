package net.sf.nakeduml.feature;

import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.feature.TransformationProcess.TransformationProgressLog;

public interface TransformationPhase<FEATURE extends ITransformationStep,ELEMENT>{
	/**
	 * Executes the entire phase
	 * 
	 * @param features
	 * @return OutputModels that will overwrite /replace any existing models in the context
	 */
	public void execute(TransformationContext context);
	/**
	 * Executes all transformation features but for the selected elements only. Returns all elements, possibly even from another metamodel
	 * that need to be evaluated as a result
	 * 
	 * @param context
	 * @param element
	 * @return
	 */
	Collection<?> processElements(TransformationContext context,Collection<ELEMENT> elements);
	void initialize(NakedUmlConfig config,List<FEATURE> features);
	Collection<FEATURE> getSteps();
	public void initializeSteps();
}