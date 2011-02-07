package net.sf.nakeduml.tests;

import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.javageneration.composition.ExtendedCompositionSemantics;
import net.sf.nakeduml.javageneration.hibernate.HibernateTestsStep;
import net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.pomgeneration.HibernatePomStep;

public class AllTests {
	static Class[] options = { PersistenceUsingHibernateStep.class, ExtendedCompositionSemantics.class, OclExpressionExecution.class,
			StereotypeApplicationExtractor.class, HibernatePomStep.class/*, BusinessProcessManagementStep.class*/,HibernateTestsStep.class};

	public static void main(String[] args) throws Exception {
		AssociationClassTestCodeGenerator.transform(options);
		InterfaceTestCodeGenerator.transform(options);
		ProcessCodeGenerator.transform(options);
	}
}
