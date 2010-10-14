package net.sf.nakeduml.jaxb;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.composition.ExtendedCompositionSemantics;
import net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = JavaTransformationPhase.class, after={ExtendedCompositionSemantics.class,PersistenceUsingHibernateStep.class})
public class JaxbStep extends AbstractJavaTransformationStep {

	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		JaxbImplementor ea = new JaxbImplementor();
		ea.initialize(workspace, javaModel, config, textWorkspace);
		ea.startVisiting(workspace);
	}

}
