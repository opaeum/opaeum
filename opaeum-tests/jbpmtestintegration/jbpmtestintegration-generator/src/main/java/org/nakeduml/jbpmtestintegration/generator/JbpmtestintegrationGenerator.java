package org.opaeum.jbpmtestintegration.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.sf.opaeum.feature.OutputRoot;
import net.sf.opaeum.feature.StepDependency;
import net.sf.opaeum.feature.TransformationStep;
import net.sf.opaeum.javageneration.JavaTextSource;
import net.sf.opaeum.pomgeneration.IntegratedSeam3PomStep;
import net.sf.opaeum.pomgeneration.MavenProjectCodeGenerator;
import net.sf.opaeum.pomgeneration.PomGenerationPhase;
import net.sf.opaeum.pomgeneration.PomGenerationStep;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.Exclusion;
import org.apache.maven.pom.POMFactory;
import org.opaeum.generation.features.IntegrationTestsAcrossMultipleModels;

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
		return toSet(org.opaeum.generation.features.Jbpm5IntegratedAcrossMultipleProjects.class,
				org.opaeum.generation.features.IntegrationTestsAcrossMultipleModels.class,
				org.opaeum.generation.features.HibernateIntegratedAcrossMultipleProjects.class,
				org.opaeum.bootstrap.WarBootstrapStep.class);
	}

	public Set<Class<? extends TransformationStep>> getSteps() {
		return toSet(org.opaeum.generation.features.PersistenceUsingHibernate.class,
				net.sf.opaeum.javageneration.oclexpressions.OclExpressionExecution.class,
				org.opaeum.generation.features.ExtendedCompositionSemantics.class, org.opaeum.generation.features.BpmUsingJbpm5.class,
				org.opaeum.generation.features.IntegrationTests.class, net.sf.opaeum.emf.extraction.StereotypeApplicationExtractor.class,
				net.sf.opaeum.javageneration.hibernate.PersistenceUsingHibernateStep.class, AddRipDependencies.class);
	}

	static public void main(String[] args) throws Exception {
		File workspaceFile = null;
		if (args.length > 0) {
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println(args[0]);
			workspaceFile = new File(args[0]).getAbsoluteFile().getParentFile().getParentFile();
			System.out.println(workspaceFile.getAbsolutePath());
		} else {
			System.out.println("ooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
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