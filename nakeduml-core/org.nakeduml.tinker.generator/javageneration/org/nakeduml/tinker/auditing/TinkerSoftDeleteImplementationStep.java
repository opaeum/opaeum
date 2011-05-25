package org.nakeduml.tinker.auditing;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.nakeduml.tinker.basicjava.tinker.TinkerSoftDeleteTransformation;
import org.nakeduml.tinker.composition.tinker.TinkerExtendedCompositionSemanticsJavaStep;

@StepDependency(phase = TinkerAuditGenerationPhase.class,requires=TinkerExtendedCompositionSemanticsJavaStep.class,after=TinkerExtendedCompositionSemanticsJavaStep.class)
public class TinkerSoftDeleteImplementationStep extends AbstractJavaTransformationStep {

	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		TinkerSoftDeleteTransformation tinkerSoftDeleteTransformation = new TinkerSoftDeleteTransformation();
		tinkerSoftDeleteTransformation.initialize(javaModel, config, textWorkspace, context);
		tinkerSoftDeleteTransformation.startVisiting(workspace);
	}

}
