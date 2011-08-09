package net.sf.nakeduml.tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.javageneration.composition.ExtendedCompositionSemantics;
import net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.pomgeneration.HibernatePomStep;

public class AssociationClassTestCodeGenerator extends AbstractMavenProjectProcess {
	public static void main(String[] args) throws Exception {
		transform(PersistenceUsingHibernateStep.class, ExtendedCompositionSemantics.class,
				OclExpressionExecution.class, StereotypeApplicationExtractor.class,HibernatePomStep.class);
	}

	public static void transform(Class<? extends TransformationStep> ... classes) throws Exception, IOException, FileNotFoundException {
		transform("../nakedumltest/assocationclasstests", "testmodels/AssociationClassTests.uml",classes);
	}

}
