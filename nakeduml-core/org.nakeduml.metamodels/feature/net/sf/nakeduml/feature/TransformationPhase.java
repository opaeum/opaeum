package net.sf.nakeduml.feature;

import java.util.List;

public interface TransformationPhase<FEATURE extends TransformationStep, ELEMENT> {
	void initialize(NakedUmlConfig config);
	/**
	 * Executes the entire phase
	 * @param features
	 * @return OutputModels that will overwrite /replace any existing models in the context
	 */
	public Object[] execute(List<FEATURE> features, TransformationContext context);
	Object processSingleElement(List<FEATURE> features,TransformationContext context, ELEMENT element);
}
