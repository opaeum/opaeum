package net.sf.nakeduml.javageneration.userinteraction;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.accesscontrol.AbstractUserRoleImplementor;
import net.sf.nakeduml.javageneration.composition.ExtendedCompositionSemantics;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

/**
 */
@StepDependency(phase = JavaTransformationPhase.class,requires = {ExtendedCompositionSemantics.class},after = {ExtendedCompositionSemantics.class})
public class UserInteractionSupport extends AbstractJavaTransformationStep{
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
		CompositionTraversalAnnotator cta = new CompositionTraversalAnnotator();
		cta.initialize(workspace, javaModel, config, textWorkspace);
		cta.startVisiting(workspace);
		ParticipationAnnotator pa = new ParticipationAnnotator();
		pa.initialize(workspace, javaModel, config, textWorkspace);
		pa.startVisiting(workspace);
		AbstractUserRoleImplementor aui = new AbstractUserRoleImplementor();
		aui.initialize(workspace, javaModel, config, textWorkspace);
		aui.startVisiting(workspace);
		// TODO UserInteractionSpecification and ParticipationSpecification
		// annotator
	}
}
