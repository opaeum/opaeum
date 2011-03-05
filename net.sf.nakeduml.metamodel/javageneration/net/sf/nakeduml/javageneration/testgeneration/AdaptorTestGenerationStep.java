package net.sf.nakeduml.javageneration.testgeneration;

import org.nakeduml.projectgeneration.AdaptorArquillianConfigStep;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
@StepDependency(phase=JavaTransformationPhase.class, requires={AdaptorArquillianConfigStep.class})
public class AdaptorTestGenerationStep extends AbstractJavaTransformationStep {
	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		ArquillianTestGenerator atg=new ArquillianTestGenerator(false);
		atg.initialize(javaModel, config, textWorkspace, context);
		atg.startVisiting(workspace);
	}
}
