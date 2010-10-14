package net.sf.nakeduml.javageneration.hibernate;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

@StepDependency(phase = JavaTransformationPhase.class, requires = { StandaloneHibernateStep.class }, after = { PersistenceUsingHibernateStep.class })
public class HibernateTestsStep extends AbstractJavaTransformationStep {
	@Override
	public void initialize(OJPackage pac, NakedUmlConfig config, TextWorkspace textWorkspace) {
		super.initialize(pac, config, textWorkspace);
	}

	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {

		PersistenceTestGenerator ptg = new PersistenceTestGenerator();
		ptg.initialize(workspace, javaModel, config, textWorkspace);
		ptg.startVisiting(workspace);
	}
}
