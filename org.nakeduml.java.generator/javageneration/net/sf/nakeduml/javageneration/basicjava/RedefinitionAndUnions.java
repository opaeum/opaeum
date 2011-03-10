package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.composition.ExtendedCompositionSemanticsJavaStep;
import net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep;
import net.sf.nakeduml.javageneration.persistence.PersistenceStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
@StepDependency(phase=JavaTransformationPhase.class, after={BasicJavaModelStep.class, PersistenceStep.class, PersistenceUsingHibernateStep.class, ExtendedCompositionSemanticsJavaStep.class})
public class RedefinitionAndUnions extends AbstractJavaTransformationStep {
	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		RedefinitionImplementor rdi = new RedefinitionImplementor();
		rdi.initialize(javaModel, config, textWorkspace, context);
		rdi.startVisiting(workspace);
		DerivedUnionImplementor dui = new DerivedUnionImplementor();
		dui.initialize(javaModel, config, textWorkspace, context);
		dui.startVisiting(workspace);
	}
}
