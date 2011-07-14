package org.nakeduml.tinker.passbyvalue;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.nakeduml.tinker.composition.tinker.TinkerExtendedCompositionSemanticsJavaStep;

@StepDependency(phase = TinkerPassByValuePhase.class,requires=TinkerExtendedCompositionSemanticsJavaStep.class,after=DtoImplementationStep.class)
public class TinkerPassByValueImplementationStep extends AbstractJavaTransformationStep {

	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		DtoOutOfControllerImplementor dtoOutOfControllerImplementor = new DtoOutOfControllerImplementor();
		dtoOutOfControllerImplementor.initialize(javaModel, config, textWorkspace);
		dtoOutOfControllerImplementor.generate(workspace, context);
		DtoWsControllerImplementor wsControllerImplementor = new DtoWsControllerImplementor();
		wsControllerImplementor.initialize(javaModel, config, textWorkspace);
		wsControllerImplementor.generate(workspace, context);
	}

}
