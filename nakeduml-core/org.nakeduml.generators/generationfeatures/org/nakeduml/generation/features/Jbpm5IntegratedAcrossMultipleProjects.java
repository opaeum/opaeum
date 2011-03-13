package org.nakeduml.generation.features;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.jbpm5.IntegratedJbpm5EnvironmentStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.pomgeneration.IntegratedSeam3PomStep;
@StepDependency(phase = JavaTransformationPhase.class, requires =  {IntegratedJbpm5EnvironmentStep.class,  IntegratedSeam3PomStep.class}, after = {})

public class Jbpm5IntegratedAcrossMultipleProjects extends AbstractJavaTransformationStep{
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
		// TODO Auto-generated method stub
	}
}
