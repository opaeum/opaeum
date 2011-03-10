package net.sf.nakeduml.javageneration.testgeneration;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = JavaTransformationPhase.class, requires = {})
public class IntegratedTestJavaGenerationStep extends AbstractJavaTransformationStep {
	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		ArquillianTestJavaGenerator atg = new ArquillianTestJavaGenerator(true);
		atg.initialize(javaModel, config, textWorkspace, context);
		atg.startVisiting(workspace);
	}
}
