package net.sf.nakeduml.tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javageneration.hibernate.HibernateTestsStep;
import net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.pomgeneration.HibernatePomStep;

public class ProcessCodeGenerator extends net.sf.nakeduml.pomgeneration.AbstractMavenProjectProcess {
	public static void main(String[] args) throws Exception {
		transform(OclExpressionExecution.class, StereotypeApplicationExtractor.class/*, BusinessProcessManagementStep.class*/,
				PersistenceUsingHibernateStep.class, HibernatePomStep.class, HibernateTestsStep.class);
	}

	public static void transform(Class<? extends TransformationStep>... classes) throws Exception, IOException, FileNotFoundException {
		transform("../nakedumltest/processmodel", "testmodels/processmodel.uml", false, classes);
	}
}
