package net.sf.nakeduml.tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javageneration.composition.ExtendedCompositionSemantics;
import net.sf.nakeduml.javageneration.hibernate.HibernateConfigGenerator;
import net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.pomgeneration.ProjectWarPomStep;

import org.nakeduml.projectgeneration.WarProjectGenerationStep;

public class DefaultDataGeneratorTest extends net.sf.nakeduml.pomgeneration.AbstractMavenProjectProcess {
	public static void main(String[] args) throws Exception {
		transform(PersistenceUsingHibernateStep.class, ExtendedCompositionSemantics.class, OclExpressionExecution.class, StereotypeApplicationExtractor.class,
				ProjectWarPomStep.class, WarProjectGenerationStep.class, HibernateConfigGenerator.class);
	}

	public static void transform(Class<? extends TransformationStep>... classes) throws Exception, IOException, FileNotFoundException {
		transform("../nakedumltest/datagenerationtests", "testmodels/DataGenerationTests.uml", false, classes);
	}

}
