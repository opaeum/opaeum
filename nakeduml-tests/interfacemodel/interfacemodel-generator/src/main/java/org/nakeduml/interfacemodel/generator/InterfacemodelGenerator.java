package org.nakeduml.interfacemodel.generator;

import java.io.File;
import java.util.Set;

import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.pomgeneration.MavenProjectCodeGenerator;

public class InterfacemodelGenerator extends MavenProjectCodeGenerator {


	/** Constructor for InterfacemodelGenerator
	 * 
	 * @param outputRoot 
	 * @param modelFile 
	 */
	public InterfacemodelGenerator(String outputRoot, String modelFile) {
		super(outputRoot,modelFile);
	}

	public Set<Class<? extends TransformationStep>> getIntegrationSteps() {
		return toSet(org.nakeduml.bootstrap.WarBootstrapStep.class,org.nakeduml.generation.features.IntegrationTestsAcrossMultipleModels.class,org.nakeduml.generation.features.HibernateIntegratedAcrossMultipleProjects.class,org.nakeduml.generation.features.Jbpm5IntegratedAcrossMultipleProjects.class);
	}
	
	public Set<Class<? extends TransformationStep>> getSteps() {
		return toSet(net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution.class,org.nakeduml.generation.features.BpmUsingJbpm5.class,org.nakeduml.generation.features.IntegrationTests.class,net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor.class,org.nakeduml.generation.features.PersistenceUsingHibernate.class,org.nakeduml.generation.features.ExtendedCompositionSemantics.class,net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep.class);
	}
	
	static public void main(String[] args) throws Exception {
		File workspaceFile = null;
		if ( args.length>0 ) {
			workspaceFile=new File(args[0]);
		} else {
			workspaceFile=new File(".").getAbsoluteFile().getParentFile().getParentFile().getParentFile();
		}
		InterfacemodelGenerator instance = new InterfacemodelGenerator(workspaceFile.getAbsolutePath() +"/interfacemodel",workspaceFile.getAbsolutePath()+"/TestModels/Models/InterfaceModel");
		
		instance.transformDirectory();
		instance.generateIntegrationCode();
	}

}