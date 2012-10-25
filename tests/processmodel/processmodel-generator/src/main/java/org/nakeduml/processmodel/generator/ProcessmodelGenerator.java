package org.opaeum.processmodel.generator;

import java.io.File;
import java.util.Set;

import net.sf.opaeum.feature.TransformationStep;
import net.sf.opaeum.pomgeneration.MavenProjectCodeGenerator;

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
		return toSet(org.opaeum.generation.features.HibernateIntegratedAcrossMultipleProjects.class,org.opaeum.generation.features.IntegrationTestsAcrossMultipleModels.class,org.opaeum.generation.features.Jbpm5IntegratedAcrossMultipleProjects.class,org.opaeum.bootstrap.WarBootstrapStep.class);
	}
	
	public Set<Class<? extends TransformationStep>> getSteps() {
		return toSet(org.opaeum.generation.features.ExtendedCompositionSemantics.class,net.sf.opaeum.javageneration.hibernate.PersistenceUsingHibernateStep.class,org.opaeum.generation.features.BpmUsingJbpm5.class,org.opaeum.generation.features.IntegrationTests.class,org.opaeum.generation.features.PersistenceUsingHibernate.class,net.sf.opaeum.emf.extraction.StereotypeApplicationExtractor.class,net.sf.opaeum.javageneration.oclexpressions.OclExpressionExecution.class);
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