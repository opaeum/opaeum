package net.sf.nakeduml.javageneration.auditing;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = JavaTransformationPhase.class, requires = {}, after = { })
public class IntegratedAuditMetaDefStep extends AbstractJavaTransformationStep {
	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		AuditHibernatePackageAnnotator mda = new AuditHibernatePackageAnnotator(true);
		mda.initialize(javaModel, config, textWorkspace, context);
		mda.startVisiting(workspace);
	}
}
