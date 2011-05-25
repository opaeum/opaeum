package org.nakeduml.tinker.basicjava;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.BasicJavaModelStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.nakeduml.tinker.basicjava.tinker.TinkerFieldRemoverImplementor;
import org.nakeduml.tinker.composition.tinker.TinkerExtendedCompositionSemanticsJavaStep;

@StepDependency(phase = JavaTransformationPhase.class, requires={BasicJavaModelStep.class}, after={BasicJavaModelStep.class} , before={TinkerExtendedCompositionSemanticsJavaStep.class})
public class TinkerFieldRemoverStep extends AbstractJavaTransformationStep {

	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		TinkerFieldRemoverImplementor fieldRemover = new TinkerFieldRemoverImplementor();
		fieldRemover.initialize(javaModel, config, textWorkspace, context);
		fieldRemover.startVisiting(workspace);
	}

}
