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
		addJbpmJpa(result);
		addNumlAdaptor(result);
		addNumlTestAdaptor(result);
		addCdi(result);
		addJbossJeeSpec(result);
		addDependencyToRootObject("-domain", model, result);
		addArquillian(result);
		return (Dependency[]) result.toArray(new Dependency[result.size()]);
	}

	private void addJbpmJpa(Collection<Dependency> result) {
		Dependency numlAdaptor = POMFactory.eINSTANCE.createDependency();
		numlAdaptor.setGroupId("org.jbpm");
		numlAdaptor.setArtifactId("jbpm-persistence-jpa");
		numlAdaptor.setScope("compile");
		numlAdaptor.setVersion("5.1-SNAPSHOT");
		result.add(numlAdaptor);
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
