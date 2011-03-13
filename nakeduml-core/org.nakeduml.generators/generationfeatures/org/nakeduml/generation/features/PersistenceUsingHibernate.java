package org.nakeduml.generation.features;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.hibernate.HibernateConfigGenerator;
import net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.pomgeneration.BasicJavaDomainPomStep;

@StepDependency(phase = JavaTransformationPhase.class,requires = { PersistenceUsingHibernateStep.class,BasicJavaDomainPomStep.class},after = {})
public class PersistenceUsingHibernate extends AbstractJavaTransformationStep{
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
		HibernateConfigGenerator hcg = new HibernateConfigGenerator(false);
		hcg.initialize(config, textWorkspace, context);
		hcg.startVisiting(workspace);

	}
}
