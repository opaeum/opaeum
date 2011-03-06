package net.sf.nakeduml.pomgeneration;

import java.util.Collection;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.persistence.PersistenceStep;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.Plugin;

@StepDependency(requires = { PersistenceStep.class }, before = {}, after = {}, phase = PomGenerationPhase.class)
public class BasicJavaDomainPomStep extends PomGenerationStep{
	//TODO properties  - jmock
	@Override
	public Dependency[] getDependencies() {
		Collection<Dependency> result = getBasicDependencies("-domain");
		return (Dependency[]) result.toArray(new Dependency[result.size()]);
	}

	@Override
	public Plugin[] getPlugins() {
		return super.getPlugins();
	}

	@Override
	protected OutputRoot getExampleTargetDir() {
		return config.getOutputRoot(JavaTextSource.OutputRootId.DOMAIN_GEN_SRC);
	}
}
