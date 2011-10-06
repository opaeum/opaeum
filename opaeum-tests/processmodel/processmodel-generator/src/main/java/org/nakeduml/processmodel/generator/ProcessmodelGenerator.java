package org.opeum.processmodel.generator;

import java.io.File;
import java.util.Set;

import net.sf.opeum.feature.TransformationStep;
import net.sf.opeum.pomgeneration.MavenProjectCodeGenerator;

public class ProcessmodelGenerator extends MavenProjectCodeGenerator {


	/** Constructor for ProcessmodelGenerator
	 * 
	 * @param outputRoot 
	 * @param modelFile 
	 */
	public ProcessmodelGenerator(String outputRoot, String modelFile) {
		super(outputRoot,modelFile);
	}

	public Set<Class<? extends TransformationStep>> getIntegrationSteps() {
		return toSet(org.opeum.generation.features.HibernateIntegratedAcrossMultipleProjects.class,org.opeum.generation.features.IntegrationTestsAcrossMultipleModels.class,org.opeum.generation.features.Jbpm5IntegratedAcrossMultipleProjects.class,org.opeum.bootstrap.WarBootstrapStep.class);
	}
	
	public Set<Class<? extends TransformationStep>> getSteps() {
		return toSet(org.opeum.generation.features.ExtendedCompositionSemantics.class,net.sf.opeum.javageneration.hibernate.PersistenceUsingHibernateStep.class,org.opeum.generation.features.BpmUsingJbpm5.class,org.opeum.generation.features.IntegrationTests.class,org.opeum.generation.features.PersistenceUsingHibernate.class,net.sf.opeum.emf.extraction.StereotypeApplicationExtractor.class,net.sf.opeum.javageneration.oclexpressions.OclExpressionExecution.class);
	}
	
	static public void main(String[] args) throws Exception {
		File workspaceFile = null;
		if ( args.length>0 ) {
			workspaceFile=new File(args[0]);
		} else {
			workspaceFile=new File(".").getAbsoluteFile().getParentFile().getParentFile().getParentFile();
		}
		ProcessmodelGenerator instance = new ProcessmodelGenerator(workspaceFile.getAbsolutePath() +"/processmodel",workspaceFile.getAbsolutePath()+"/TestModels/Models/ProcessModel");
		
		instance.transformDirectory();
		instance.generateIntegrationCode();
	}

}