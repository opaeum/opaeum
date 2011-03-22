package org.nakeduml.jbpmtestintegration.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.pomgeneration.IntegratedSeam3PomStep;
import net.sf.nakeduml.pomgeneration.MavenProjectCodeGenerator;
import net.sf.nakeduml.pomgeneration.PomGenerationPhase;
import net.sf.nakeduml.pomgeneration.PomGenerationStep;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.Exclusion;
import org.apache.maven.pom.POMFactory;
import org.nakeduml.generation.features.IntegrationTestsAcrossMultipleModels;

public class JbpmtestintegrationGenerator extends MavenProjectCodeGenerator {

	/**
	 * Constructor for JbpmtestintegrationGenerator
	 * 
	 * @param outputRoot
	 * @param modelFile
	 */
	public JbpmtestintegrationGenerator(String outputRoot, String modelFile) {
		super(outputRoot, modelFile);
	}

	public Set<Class<? extends TransformationStep>> getIntegrationSteps() {
		return toSet(org.nakeduml.generation.features.Jbpm5IntegratedAcrossMultipleProjects.class,
				org.nakeduml.generation.features.IntegrationTestsAcrossMultipleModels.class,
				org.nakeduml.generation.features.HibernateIntegratedAcrossMultipleProjects.class,
				org.nakeduml.bootstrap.WarBootstrapStep.class);
	}

	public Set<Class<? extends TransformationStep>> getSteps() {
		return toSet(org.nakeduml.generation.features.PersistenceUsingHibernate.class,
				net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution.class,
				org.nakeduml.generation.features.ExtendedCompositionSemantics.class, org.nakeduml.generation.features.BpmUsingJbpm5.class,
				org.nakeduml.generation.features.IntegrationTests.class, net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor.class,
				net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep.class, AddRipDependencies.class);
	}

	static public void main(String[] args) throws Exception {
		File workspaceFile = null;
		if (args.length > 0) {
			workspaceFile = new File(args[0]);
		} else {
			workspaceFile = new File(".").getAbsoluteFile().getParentFile().getParentFile().getParentFile();
		}
		JbpmtestintegrationGenerator instance = new JbpmtestintegrationGenerator(workspaceFile.getAbsolutePath() + "/jbpmtestintegration",
				workspaceFile.getAbsolutePath() + "/TestModels/Models/jbpm");

		instance.transformDirectory();
		instance.generateIntegrationCode();
	}

	@StepDependency(phase = PomGenerationPhase.class, requires = { })
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

			Dependency googleio = POMFactory.eINSTANCE.createDependency();
			googleio.setGroupId("com.google.common");
			googleio.setArtifactId("google-guava");
			googleio.setVersion("0.99-r1");
			dependencies.add(googleio);

			Dependency sshd = POMFactory.eINSTANCE.createDependency();
			sshd.setGroupId("org.apache.sshd");
			sshd.setArtifactId("sshd-core");
			sshd.setVersion("0.5.0");
			sshd.setScope("test");
			dependencies.add(sshd);

			Dependency telnetd = POMFactory.eINSTANCE.createDependency();
			telnetd.setGroupId("net.wimpi");
			telnetd.setArtifactId("telnetd-x");
			telnetd.setVersion("2.1.1");
			telnetd.setScope("test");
			Exclusion log4j = POMFactory.eINSTANCE.createExclusion();
			log4j.setGroupId("log4j");
			log4j.setArtifactId("log4j");
			telnetd.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
			telnetd.getExclusions().getExclusion().add(log4j);
			
			dependencies.add(telnetd);
		}

	}

}