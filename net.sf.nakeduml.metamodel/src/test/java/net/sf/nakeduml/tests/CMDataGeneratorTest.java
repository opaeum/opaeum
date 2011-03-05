package net.sf.nakeduml.tests;

import java.util.Set;

import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javageneration.composition.ExtendedCompositionSemantics;
import net.sf.nakeduml.javageneration.hibernate.HibernateTestsStep;
import net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.pomgeneration.WarPomStep;

import org.nakeduml.projectgeneration.WarProjectConfigurationStep;

public class CMDataGeneratorTest extends net.sf.nakeduml.pomgeneration.MavenProjectTransformationConfiguration {
	protected CMDataGeneratorTest(String outputRoot, String modelDirectory) {
		super(outputRoot, modelDirectory);
	}

	public static void main(String[] args) throws Exception {
		new CMDataGeneratorTest("../nakedumltest/cmdatagenerationtests", "/usr/share/cm-data/cm-model").transformDirectory();
	}

	@Override
	protected Set<Class<? extends TransformationStep>> getSteps() {
		return toSet(PersistenceUsingHibernateStep.class, ExtendedCompositionSemantics.class, OclExpressionExecution.class, StereotypeApplicationExtractor.class,
				WarPomStep.class, WarProjectConfigurationStep.class, HibernateTestsStep.class);
	}

}
