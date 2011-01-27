package net.sf.nakeduml.pomgeneration;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.persistence.PersistenceStep;

import org.apache.maven.pom.ConfigurationType2;
import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;

@StepDependency(requires = { PersistenceStep.class }, before = {}, after = {}, phase = PomGenerationPhase.class)
public class JpaPomStep extends PomGenerationStep {

	@Override
	public Dependency[] getDependencies() {
		Dependency[] result = new Dependency[5];
		result[0] = POMFactory.eINSTANCE.createDependency();
		result[0].setGroupId("javax.persistence");
		result[0].setArtifactId("persistence-api");
		result[0].setVersion("1.0");
		result[0].setScope("compile");
		result[0].setType("jar");
		result[1] = POMFactory.eINSTANCE.createDependency();
		result[1].setGroupId("javax.transaction");
		result[1].setArtifactId("jta");
		result[1].setVersion("1.1");
		result[1].setScope("compile");
		result[1].setType("jar");
		result[2] = POMFactory.eINSTANCE.createDependency();
		result[2].setGroupId("net.sf.nakeduml");
		result[2].setArtifactId("nakedumlutil");
		result[2].setVersion("0.0.1");
		result[2].setScope("compile");
		result[2].setType("jar");
		result[3] = POMFactory.eINSTANCE.createDependency();
		result[3].setGroupId("net.sf.nakeduml");
		result[3].setArtifactId("nakedumlutil");
		result[3].setVersion("0.0.1");
		result[3].setScope("compile");
		result[3].setType("jar");
		result[4] = POMFactory.eINSTANCE.createDependency();
		result[4].setGroupId("org.testng");
		result[4].setArtifactId("testng");
		result[4].setVersion("5.14");
		result[4].setScope("compile");
		result[4].setType("jar");
		return result;
	}

	@Override
	public String getTargetDir() {
		return JavaTextSource.JPA_ROOT;
	}

	@Override
	public String getArtifactSuffix() {
		return "JPA";
	}

	@Override
	public Plugin[] getPlugins() {
		Plugin[] result = new Plugin[1];
		result[0] = POMFactory.eINSTANCE.createPlugin();
		result[0].setGroupId("org.apache.maven.plugins");
		result[0].setArtifactId("maven-compiler-plugin");
		result[0].setVersion("2.3.1");
		ConfigurationType2 cfg = POMFactory.eINSTANCE.createConfigurationType2();
		addAnyElement(cfg.getAny(), "source", "1.6");
		addAnyElement(cfg.getAny(), "target", "1.6");
		result[0].setConfiguration(cfg);
		return result;
	}

}
