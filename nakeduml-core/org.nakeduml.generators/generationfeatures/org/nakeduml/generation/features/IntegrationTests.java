package org.nakeduml.generation.features;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.testgeneration.AdaptorTestJavaGenerationStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.pomgeneration.AdaptorArquillianPomStep;

import org.nakeduml.bootstrap.AdaptorArquillianBootstrapStep;

@StepDependency(phase = JavaTransformationPhase.class,requires = {AdaptorArquillianPomStep.class,AdaptorArquillianBootstrapStep.class,AdaptorTestJavaGenerationStep.class})
public class IntegrationTests extends AbstractJavaTransformationStep{
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
	}
}
