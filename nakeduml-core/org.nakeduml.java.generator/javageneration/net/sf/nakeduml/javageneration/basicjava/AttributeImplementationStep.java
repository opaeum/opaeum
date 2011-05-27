package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = JavaTransformationPhase.class, requires = { Java5ModelGenerationStep.class }, after = { Java5ModelGenerationStep.class })
public class AttributeImplementationStep extends AbstractJavaTransformationStep {
	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		AttributeImplementor ai = new AttributeImplementor();
		ai.initialize(javaModel, config, textWorkspace, context);
		ai.startVisiting(workspace);
	}
}
