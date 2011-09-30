package org.opeum.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.opeum.feature.ITransformationStep;
import org.opeum.feature.SourceFolderDefinition;
import org.opeum.feature.StepDependency;
import org.opeum.javageneration.JavaSourceFolderIdentifier;
import org.opeum.pomgeneration.PomGenerationPhase;
import org.opeum.pomgeneration.PomGenerationStep;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;

public class JbpmTest extends AbstractTestCodeGenerator {
	protected JbpmTest(String outputRoot, String modelDirectory) {
		super(outputRoot, modelDirectory);
	}

	public static void main(String[] args) throws Exception {
//		JbpmTest jbpmTest = null;
		File f = new File("org.opeum.generators/testmodels/jbpm");
		if (f.exists()) {
//			jbpmTest = new JbpmTest("opeum-test/jbpmtestintegration", "org.opeum.generators/testmodels/jbpm");
		} else {
//			jbpmTest = new JbpmTest("../opeumtest/jbpmtestintegration", "testmodels/jbpm");
		}
//		jbpmTest.generateCodeForSingleModel("jbpm.uml");
//		jbpmTest.generateIntegrationCode();
	}

	@StepDependency(phase = PomGenerationPhase.class, requires = { })
	public static class AddRipDependencies extends PomGenerationStep {
		@Override
		protected SourceFolderDefinition getExampleTargetDir() {
			return config.getSourceFolderDefinition(JavaSourceFolderIdentifier.ADAPTOR_GEN_SRC);
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

		}

	}

	@Override
	protected Set<Class<? extends ITransformationStep>> getSteps() {
		Set<Class<? extends ITransformationStep>> steps = super.getSteps();
		steps.add(AddRipDependencies.class);
		return steps;
	}

}
