package net.sf.nakeduml.javageneration.hibernate;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.auditing.AuditMetaDefAnnotator;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = JavaTransformationPhase.class, requires = {}, after = {})
public class IntegratedHibernateStep extends AbstractJavaTransformationStep {
	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		AuditMetaDefAnnotator mda = new AuditMetaDefAnnotator(true);
		mda.initialize(javaModel, config, textWorkspace, context);
		mda.startVisiting(workspace);
		HibernateConfigGenerator hcg = new HibernateConfigGenerator(true);
		hcg.initialize(config, textWorkspace, context);
		hcg.startVisiting(workspace);
	}
}
