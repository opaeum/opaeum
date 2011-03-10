package net.sf.nakeduml.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;
import org.nakeduml.bootstrap.WarBootstrapStep;
import org.nakeduml.generation.features.BpmUsingJbpm5;
import org.nakeduml.generation.features.ExtendedCompositionSemantics;
import org.nakeduml.generation.features.HibernateIntegratedAcrossMultipleProjects;
import org.nakeduml.generation.features.IntegrationTestsAcrossMultipleModels;
import org.nakeduml.generation.features.Jbpm5IntegratedAcrossMultipleProjects;
import org.nakeduml.generation.features.PersistenceUsingHibernate;

import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.pomgeneration.IntegratedSeam3PomStep;
import net.sf.nakeduml.pomgeneration.PomGenerationPhase;
import net.sf.nakeduml.pomgeneration.PomGenerationStep;

public class JbpmTest extends AbstractTestCodeGenerator{
	protected JbpmTest(String outputRoot,String modelDirectory){
		super(outputRoot, modelDirectory);
	}
	public static void main(String[] args) throws Exception{
		JbpmTest jbpmTest = null;
		File f = new File("org.nakeduml.generators/testmodels/jbpm");
		if(f.exists()){
			jbpmTest = new JbpmTest("nakedumltest/jbpmtestintegration", "org.nakeduml.generators/testmodels/jbpm");
		}else{
			jbpmTest = new JbpmTest("../nakedumltest/jbpmtestintegration", "testmodels/jbpm");
		}
		jbpmTest.generateCodeForSingleModel("jbpm.uml");
		jbpmTest.generateIntegrationCode();
	}
	
	@StepDependency(phase = PomGenerationPhase.class, requires = { IntegratedSeam3PomStep.class })
	public static class AddRipDependencies extends PomGenerationStep {
		@Override
		protected OutputRoot getExampleTargetDir() {
			return config.getOutputRoot(JavaTextSource.OutputRootId.ADAPTOR_GEN_SRC);
		}
		
		@Override
		public Dependency[] getDependencies() {
			List<Dependency> dependencies = new ArrayList<Dependency>();
			addRipDependencies(dependencies);
			return dependencies.toArray(new Dependency[dependencies.size()]);
		}
		
		protected void addRipDependencies(Collection<Dependency> dependencies) {
			Dependency commonsPool = POMFactory.eINSTANCE.createDependency();
			commonsPool.setGroupId("commons-pool");
			commonsPool.setArtifactId("commons-pool");
			commonsPool.setVersion("1.5.5");
			dependencies.add(commonsPool);

			Dependency commonsNet = POMFactory.eINSTANCE.createDependency();
			commonsNet.setGroupId("commons-net");
			commonsNet.setArtifactId("commons-net");
			commonsNet.setVersion("2.0");
			dependencies.add(commonsNet);
			
			Dependency jcraft = POMFactory.eINSTANCE.createDependency();
			jcraft.setGroupId("com.jcraft");
			jcraft.setArtifactId("jsch");
			jcraft.setVersion("0.1.42");
			dependencies.add(jcraft);
}
		
	}
	
	@SuppressWarnings("unchecked")
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
				WarBootstrapStep.class,
				AddRipDependencies.class);
	}
	
}
