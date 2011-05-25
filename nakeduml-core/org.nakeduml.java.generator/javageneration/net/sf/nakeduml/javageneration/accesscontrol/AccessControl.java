package net.sf.nakeduml.javageneration.accesscontrol;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.BasicJavaModelStep;
import net.sf.nakeduml.javageneration.composition.ExtendedCompositionSemanticsJavaStep;
import net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
@StepDependency(phase = JavaTransformationPhase.class,requires = {BasicJavaModelStep.class,ExtendedCompositionSemanticsJavaStep.class, PersistenceUsingHibernateStep.class},
		after = {BasicJavaModelStep.class,ExtendedCompositionSemanticsJavaStep.class},before={PersistenceUsingHibernateStep.class})
public class AccessControl extends AbstractJavaTransformationStep{
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
		SecureObjectImplementor soi = new SecureObjectImplementor();
		soi.initialize(javaModel, config, textWorkspace, context);
		soi.startVisiting(workspace);
		AbstractUserRoleImplementor aui = new AbstractUserRoleImplementor();
		aui.initialize(javaModel, config, textWorkspace, context);
		aui.startVisiting(workspace);
		AccessControlAnnotator aca = new AccessControlAnnotator();
		aca.initialize(javaModel, config, textWorkspace, context);
		aca.startVisiting(workspace);
	}
}
