package org.opeum.eclipse.starter;

import org.apache.maven.pom.ConfigurationType2;
import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;
import org.opeum.feature.StepDependency;
import org.opeum.pomgeneration.PomGenerationPhase;
import org.opeum.pomgeneration.PomGenerationStep;
import org.opeum.pomgeneration.PomUtil;
import org.opeum.textmetamodel.SourceFolderDefinition;

@StepDependency(phase = PomGenerationPhase.class)
public class GeneratorPomStep extends PomGenerationStep {

	@Override
	public SourceFolderDefinition getExampleTargetDir() {
		return config.getSourceFolderDefinition(GeneratorSourceFolderIdentifier.GENERATOR_SRC);
	}
	//TODO add to parent pom - creates a bootstrap problem maybe not
	//TODO add code generation 

	@Override
	public Dependency[] getDependencies() {
		Dependency[] result = new Dependency[2];
		result[0] = POMFactory.eINSTANCE.createDependency();
		result[0].setGroupId("org.opeum");
		result[0].setArtifactId("opeum-generators");
		result[0].setScope("compile");
		result[0].setVersion(PomGenerationPhase.NUML_VERSION);
		result[0].setType("jar");
		result[1] = POMFactory.eINSTANCE.createDependency();
		result[1].setGroupId("org.opeum");
		result[1].setArtifactId("opeum-uml2uim");
		result[1].setScope("compile");
		result[1].setVersion(PomGenerationPhase.NUML_VERSION);
		result[1].setType("jar");
//		result[2] = POMFactory.eINSTANCE.createDependency();
//		result[2].setGroupId("org.opeum");
//		result[2].setArtifactId("opeum-metamodels");
//		result[2].setScope("compile");
//		result[2].setVersion(PomGenerationPhase.NUML_VERSION);
//		result[2].setType("jar");
//		
		return result;
	}


	@Override
	public Plugin[] getPlugins() {
		Plugin[] result = new Plugin[1];
		result[0] = POMFactory.eINSTANCE.createPlugin();
		result[0].setGroupId("org.apache.maven.plugins");
		result[0].setArtifactId("maven-compiler-plugin");
		result[0].setVersion("2.3.1");
		ConfigurationType2 cfg = POMFactory.eINSTANCE.createConfigurationType2();
		PomUtil.addAnyElementWithContent(cfg.getAny(), "source", "1.6");
		PomUtil.addAnyElementWithContent(cfg.getAny(), "target", "1.6");
		result[0].setConfiguration(cfg);
		return result;
	}

}
