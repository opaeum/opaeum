package org.opeum.tests;

import java.util.Set;

import org.opeum.emf.extraction.StereotypeApplicationExtractor;
import org.opeum.feature.ITransformationStep;
import org.opeum.javageneration.MavenProjectCodeGenerator;

import org.opeum.bootstrap.WarBootstrapStep;
import org.opeum.generation.features.BpmUsingJbpm5;
import org.opeum.generation.features.ExtendedCompositionSemantics;
import org.opeum.generation.features.OclExpressionExecution;
import org.opeum.generation.features.PersistenceUsingHibernate;

public class AbstractTestCodeGenerator extends MavenProjectCodeGenerator{
	protected AbstractTestCodeGenerator(String outputRoot,String modelDirectory){
		super(outputRoot, modelDirectory);
	}
	@Override
	protected Set<Class<? extends ITransformationStep>> getSteps(){
		return toSet(PersistenceUsingHibernate.class, ExtendedCompositionSemantics.class, OclExpressionExecution.class, StereotypeApplicationExtractor.class,
				BpmUsingJbpm5.class, WarBootstrapStep.class);
	}
}
