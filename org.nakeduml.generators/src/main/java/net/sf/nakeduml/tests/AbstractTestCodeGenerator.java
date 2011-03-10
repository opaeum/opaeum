package net.sf.nakeduml.tests;

import java.util.Set;

import org.nakeduml.bootstrap.WarBootstrapStep;
import org.nakeduml.generation.features.BpmUsingJbpm5;
import org.nakeduml.generation.features.ExtendedCompositionSemantics;
import org.nakeduml.generation.features.HibernateIntegratedAcrossMultipleProjects;
import org.nakeduml.generation.features.IntegrationTestsAcrossMultipleModels;
import org.nakeduml.generation.features.Jbpm5IntegratedAcrossMultipleProjects;
import org.nakeduml.generation.features.PersistenceUsingHibernate;

import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep;
import net.sf.nakeduml.javageneration.jbpm5.IntegratedJbpm5EnvironmentStep;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.pomgeneration.BasicJavaIntegratedAdaptorPomStep;
import net.sf.nakeduml.pomgeneration.MavenProjectCodeGenerator;
import net.sf.nakeduml.pomgeneration.WarPomStep;

public class AbstractTestCodeGenerator extends MavenProjectCodeGenerator{
	protected AbstractTestCodeGenerator(String outputRoot,String modelDirectory){
		super(outputRoot, modelDirectory);
	}
	@Override
	protected Set<Class<? extends TransformationStep>> getSteps(){
		return toSet(PersistenceUsingHibernateStep.class, 
				ExtendedCompositionSemantics.class, 
				OclExpressionExecution.class, 
				StereotypeApplicationExtractor.class,
				BpmUsingJbpm5.class,
				PersistenceUsingHibernate.class,
				HibernateIntegratedAcrossMultipleProjects.class, 
				Jbpm5IntegratedAcrossMultipleProjects.class, 
				IntegrationTestsAcrossMultipleModels.class, 
				WarBootstrapStep.class);
	}
}
