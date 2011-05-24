package org.nakeduml.processmodel.generator;

import java.io.File;
import java.util.Set;

import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.pomgeneration.MavenProjectCodeGenerator;

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
		return toSet(org.nakeduml.generation.features.HibernateIntegratedAcrossMultipleProjects.class,org.nakeduml.generation.features.IntegrationTestsAcrossMultipleModels.class,org.nakeduml.generation.features.Jbpm5IntegratedAcrossMultipleProjects.class,org.nakeduml.bootstrap.WarBootstrapStep.class);
	}
	
	public Set<Class<? extends TransformationStep>> getSteps() {
		return toSet(org.nakeduml.generation.features.ExtendedCompositionSemantics.class,net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep.class,org.nakeduml.generation.features.BpmUsingJbpm5.class,org.nakeduml.generation.features.IntegrationTests.class,org.nakeduml.generation.features.PersistenceUsingHibernate.class,net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor.class,net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution.class);
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