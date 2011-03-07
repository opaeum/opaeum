package net.sf.nakeduml.tests;

import java.io.File;
import java.util.Set;

import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javageneration.composition.ExtendedCompositionSemantics;
import net.sf.nakeduml.javageneration.hibernate.IntegratedHibernateStep;
import net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep;
import net.sf.nakeduml.javageneration.jbpm5.IntegratedJbpm5EnvironmentStep;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Step;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.pomgeneration.BasicJavaIntegratedAdaptorPomStep;
import net.sf.nakeduml.pomgeneration.WarPomStep;

import org.nakeduml.projectgeneration.WarProjectConfigurationStep;

public class JbpmTest extends net.sf.nakeduml.pomgeneration.MavenProjectTransformationConfiguration {
	protected JbpmTest(String outputRoot, String modelDirectory) {
		super(outputRoot, modelDirectory);
	}

	public static void main(String[] args) throws Exception {
		JbpmTest jbpmTest = null;
		File f = new File("net.sf.nakeduml.metamodel/testmodels/jbpm");
		if (f.exists()) {
			jbpmTest = new JbpmTest("nakedumltest/jbpmtestintegration","net.sf.nakeduml.metamodel/testmodels/jbpm");
		} else {
			jbpmTest = new JbpmTest("../nakedumltest/jbpmtestintegration","testmodels/jbpm");
		}
		jbpmTest.generateCodeForSingleModel("jbpm.uml");
		jbpmTest.generateIntegrationCode();
	}

	@Override
	protected Set<Class<? extends TransformationStep>> getSteps() {
		return toSet(PersistenceUsingHibernateStep.class, ExtendedCompositionSemantics.class, OclExpressionExecution.class, StereotypeApplicationExtractor.class,
				IntegratedHibernateStep.class, IntegratedJbpm5EnvironmentStep.class, Jbpm5Step.class, BasicJavaIntegratedAdaptorPomStep.class, WarPomStep.class, WarProjectConfigurationStep.class);
	}

}
