package net.sf.nakeduml.javageneration.hibernate;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

@StepDependency(phase = JavaTransformationPhase.class, requires = {}, after = { PersistenceUsingHibernateStep.class })
public class HibernateTestsStep extends AbstractJavaTransformationStep {

	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {

		PersistenceTestGenerator ptg = new PersistenceTestGenerator();
		ptg.initialize(javaModel, config, textWorkspace, context);
		ptg.startVisiting(workspace);
		
		HibernateConfiguratorGenerator hcg=new HibernateConfiguratorGenerator();
		hcg.initialize(javaModel, config, textWorkspace, context);
		hcg.generate(context);
	}
}
