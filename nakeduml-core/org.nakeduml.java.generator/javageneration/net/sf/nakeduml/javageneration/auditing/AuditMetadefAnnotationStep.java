package net.sf.nakeduml.javageneration.auditing;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = AuditGenerationPhase.class, requires = {})

public class AuditMetadefAnnotationStep extends AbstractJavaTransformationStep{
	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		AuditHibernatePackageAnnotator mda = new AuditHibernatePackageAnnotator(false);
		mda.initialize(javaModel, config, textWorkspace, context);
		mda.startVisiting(workspace);
	}

}
