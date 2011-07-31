package net.sf.nakeduml.feature;

import java.util.Collection;
import java.util.List;

public interface TransformationPhase<FEATURE extends TransformationStep,ELEMENT>{
	void initialize(NakedUmlConfig config);
	/**
	 * Executes the entire phase
	 * 
	 * @param features
	 * @return OutputModels that will overwrite /replace any existing models in the context
	 */
	public Object[] execute(List<FEATURE> features,TransformationContext context);
	/**
	 * Executes all transformation features but for the selected elements only. Returns all elements, possibly even from another metamodel
	 * that need to be evaluated as a result
	 * 
	 * @param context
	 * @param element
	 * @return
	 */
	Collection<?> processElements(TransformationContext context,Collection<ELEMENT> elements);
}
