package net.sf.nakeduml.pomgeneration;

import java.util.Collection;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.persistence.PersistenceStep;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;

@StepDependency(requires = { PersistenceStep.class }, before = {}, after = {}, phase = PomGenerationPhase.class)
public class BasicJavaAdaptorPomStep extends PomGenerationStep {
	@Override
	public Dependency[] getDependencies() {
		Collection<Dependency> result = getBasicDependencies("-adaptor");
		addNumlAdaptor(result);
		addNumlTestAdaptor(result);
		addCdi(result);
		addJbossJeeSpec(result);
		addDependencyToRootObject("-domain", model, result);
		addArquillian(result);
		return (Dependency[]) result.toArray(new Dependency[result.size()]);
	}

	private void addNumlAdaptor(Collection<Dependency> result) {
		Dependency numlAdaptor = POMFactory.eINSTANCE.createDependency();
		numlAdaptor.setGroupId("org.nakeduml");
		numlAdaptor.setArtifactId("nakeduml-runtime-adaptor");
		numlAdaptor.setScope("compile");
		numlAdaptor.setVersion(PomGenerationPhase.NUML_VERSION);
		result.add(numlAdaptor);
	}

	@Override
	public Plugin[] getPlugins() {
		return super.getPlugins();
	}

	@Override
	protected OutputRoot getExampleTargetDir() {
		return config.getOutputRoot(JavaTextSource.OutputRootId.ADAPTOR_GEN_SRC);
	}
}
