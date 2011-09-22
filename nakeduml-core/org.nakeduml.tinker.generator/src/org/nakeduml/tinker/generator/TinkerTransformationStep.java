package org.nakeduml.tinker.generator;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaFeature;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;

@StepDependency(phase = JavaTransformationPhase.class, requires = { TinkerCodeGenerator.class })
public class TinkerTransformationStep extends JavaFeature {

	public TinkerTransformationStep() {
	}
}
