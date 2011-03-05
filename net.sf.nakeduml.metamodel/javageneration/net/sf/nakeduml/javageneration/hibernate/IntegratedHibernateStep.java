package net.sf.nakeduml.javageneration.hibernate;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.filegeneration.TextFileGenerator;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = JavaTransformationPhase.class, requires = {TextFileGenerator.class}, after = {})
public class IntegratedHibernateStep extends AbstractJavaTransformationStep {
	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		MetaDefAnnotator mda = new MetaDefAnnotator(true);
		mda.initialize(javaModel, config, textWorkspace, context);
		mda.startVisiting(workspace);
		HibernateConfigGenerator hcg = new HibernateConfigGenerator(true);
		hcg.initialize(config, textWorkspace, context);
		hcg.startVisiting(workspace);
	}
}
