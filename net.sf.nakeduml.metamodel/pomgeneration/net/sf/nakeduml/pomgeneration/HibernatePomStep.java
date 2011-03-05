package net.sf.nakeduml.pomgeneration;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaTextSource;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;

@StepDependency(requires={JpaPomStep.class},before={},after={JpaPomStep.class}, phase = PomGenerationPhase.class)
public class HibernatePomStep extends PomGenerationStep {

	@Override
	public Dependency[] getDependencies() {
		Dependency[] result = new Dependency[0];
		return result;
	}

	@Override
	protected OutputRoot getExampleTargetDir() {
		return config.getOutputRoot(JavaTextSource.OutputRootId.DOMAIN_GEN_SRC);
	}

	@Override
	public Plugin[] getPlugins() {
		Plugin[] result = new Plugin[0];
		return result;
	}

}
