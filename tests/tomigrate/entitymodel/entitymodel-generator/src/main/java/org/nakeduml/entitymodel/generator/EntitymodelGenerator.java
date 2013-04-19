package org.opaeum.entitymodel.generator;

import java.io.File;
import java.util.Set;

import net.sf.opaeum.feature.TransformationStep;
import net.sf.opaeum.pomgeneration.MavenProjectCodeGenerator;

public class EntitymodelGenerator extends MavenProjectCodeGenerator {


	/** Constructor for EntitymodelGenerator
	 * 
	 * @param outputRoot 
	 * @param modelFile 
	 */
	public EntitymodelGenerator(String outputRoot, String modelFile) {
		super(outputRoot,modelFile);
	}

	public Set<Class<? extends TransformationStep>> getIntegrationSteps() {
		return toSet(org.opaeum.bootstrap.WarBootstrapStep.class,org.opaeum.generation.features.IntegrationTestsAcrossMultipleModels.class,org.opaeum.generation.features.HibernateIntegratedAcrossMultipleProjects.class,org.opaeum.generation.features.Jbpm5IntegratedAcrossMultipleProjects.class);
	}
	
	public Set<Class<? extends TransformationStep>> getSteps() {
		return toSet(net.sf.opaeum.javageneration.oclexpressions.OclExpressionExecution.class,org.opaeum.generation.features.BpmUsingJbpm5.class,org.opaeum.generation.features.IntegrationTests.class,net.sf.opaeum.emf.extraction.StereotypeApplicationExtractor.class,org.opaeum.generation.features.PersistenceUsingHibernate.class,org.opaeum.generation.features.ExtendedCompositionSemantics.class,net.sf.opaeum.javageneration.hibernate.PersistenceUsingHibernateStep.class);
	}
	
	static public void main(String[] args) throws Exception {
		File workspaceFile = null;
		if ( args.length>0 ) {
			workspaceFile=new File(args[0]);
		} else {
			workspaceFile=new File(".").getAbsoluteFile().getParentFile().getParentFile().getParentFile();
		}
		EntitymodelGenerator instance = new EntitymodelGenerator(workspaceFile.getAbsolutePath() +"/entitymodel",workspaceFile.getAbsolutePath()+"/TestModels/Models/EntityModel");
		
		instance.transformDirectory();
		instance.generateIntegrationCode();
	}

}