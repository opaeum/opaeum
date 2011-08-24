package org.nakeduml.tinker.activity;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.nakeduml.tinker.composition.tinker.TinkerExtendedCompositionSemanticsJavaStep;

@StepDependency(phase = JavaTransformationPhase.class, requires = { TinkerExtendedCompositionSemanticsJavaStep.class }, after = { TinkerExtendedCompositionSemanticsJavaStep.class })
public class TinkerActivityStep extends AbstractJavaTransformationStep {

	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		TinkerActivityGenerator tinkerActivityGenerator = new TinkerActivityGenerator();
		tinkerActivityGenerator.initialize(javaModel, config, textWorkspace, context);
		tinkerActivityGenerator.startVisiting(workspace);
		TinkerActionGenerator tinkerActionGenerator = new TinkerActionGenerator();
		tinkerActionGenerator.initialize(javaModel, config, textWorkspace, context);
		tinkerActionGenerator.startVisiting(workspace);
	}

}
