package org.nakeduml.generation.features;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.testgeneration.IntegratedTestJavaGenerationStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.pomgeneration.IntegratedArquillianPomStep;

import org.nakeduml.bootstrap.IntegratedArquillianBootstrapStep;

@StepDependency(phase = JavaTransformationPhase.class, requires = { IntegratedTestJavaGenerationStep.class, IntegratedArquillianBootstrapStep.class,IntegratedArquillianPomStep.class })
public class IntegrationTestsAcrossMultipleModels extends AbstractJavaTransformationStep{
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
	}
}
