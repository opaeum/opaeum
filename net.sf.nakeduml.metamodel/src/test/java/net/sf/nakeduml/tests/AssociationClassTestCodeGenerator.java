package net.sf.nakeduml.tests;

import java.util.Set;

import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javageneration.composition.ExtendedCompositionSemantics;
import net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.pomgeneration.HibernatePomStep;

public class AssociationClassTestCodeGenerator extends net.sf.nakeduml.pomgeneration.MavenProjectTransformationConfiguration {
	protected AssociationClassTestCodeGenerator(String outputRoot, String modelDirectory) {
		super(outputRoot, modelDirectory);
	}

	public static void main(String[] args) throws Exception {
		new AssociationClassTestCodeGenerator("../nakedumltest/assocationclasstests", "testmodels/")
				.generateCodeForSingleModel("AssociationClassTests.uml");
	}

	@Override
	protected Set<Class<? extends TransformationStep>> getSteps() {
		return toSet(PersistenceUsingHibernateStep.class, ExtendedCompositionSemantics.class, OclExpressionExecution.class,
				StereotypeApplicationExtractor.class, HibernatePomStep.class);
	}
}
