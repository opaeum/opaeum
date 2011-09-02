package net.sf.nakeduml.tests;

import java.util.Set;

import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.javageneration.MavenProjectCodeGenerator;

import org.nakeduml.bootstrap.WarBootstrapStep;
import org.nakeduml.generation.features.BpmUsingJbpm5;
import org.nakeduml.generation.features.ExtendedCompositionSemantics;
import org.nakeduml.generation.features.OclExpressionExecution;
import org.nakeduml.generation.features.PersistenceUsingHibernate;

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
