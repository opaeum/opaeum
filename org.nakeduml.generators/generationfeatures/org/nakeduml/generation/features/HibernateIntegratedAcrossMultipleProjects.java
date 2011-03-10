package org.nakeduml.generation.features;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.filegeneration.TextFileGenerator;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.hibernate.HibernateConfigGenerator;
import net.sf.nakeduml.javageneration.hibernate.IntegratedHibernateMetaDefsStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = JavaTransformationPhase.class,requires = {IntegratedHibernateMetaDefsStep.class},after = {})
public class HibernateIntegratedAcrossMultipleProjects extends AbstractJavaTransformationStep{
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
		//For lack of other place to put it
		HibernateConfigGenerator hcg = new HibernateConfigGenerator(true);
		hcg.initialize(config, textWorkspace, context);
		hcg.startVisiting(workspace);

	}
}
