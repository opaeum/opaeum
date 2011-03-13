package net.sf.nakeduml.javageneration.userinteraction;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.accesscontrol.AbstractUserRoleImplementor;
import net.sf.nakeduml.javageneration.composition.ExtendedCompositionSemanticsJavaStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

/**
 */
@StepDependency(phase = JavaTransformationPhase.class,requires = {ExtendedCompositionSemanticsJavaStep.class},after = {ExtendedCompositionSemanticsJavaStep.class})
public class UserInteractionSupport extends AbstractJavaTransformationStep{
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
		CompositionTraversalAnnotator cta = new CompositionTraversalAnnotator();
		cta.initialize(javaModel, config, textWorkspace, context);
		cta.startVisiting(workspace);
		ParticipationAnnotator pa = new ParticipationAnnotator();
		pa.initialize(javaModel, config, textWorkspace, context);
		pa.startVisiting(workspace);
		AbstractUserRoleImplementor aui = new AbstractUserRoleImplementor();
		aui.initialize(javaModel, config, textWorkspace, context);
		aui.startVisiting(workspace);
		// TODO UserInteractionSpecification and ParticipationSpecification
		// annotator
	}
}
