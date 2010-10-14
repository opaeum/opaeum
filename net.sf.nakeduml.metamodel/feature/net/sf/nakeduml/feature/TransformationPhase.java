package net.sf.nakeduml.feature;

import java.util.List;

public interface TransformationPhase<FEATURE extends TransformationStep> {
	void initialize(NakedUmlConfig config);
	/**
	 * Executes the entire phase
	 * @param features
	 * @return OutputModels that will overwrite /replace any existing models in the context
	 */
	public Object[] execute(List<FEATURE> features );
}
