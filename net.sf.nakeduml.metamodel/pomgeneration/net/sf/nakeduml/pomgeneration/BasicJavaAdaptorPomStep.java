package net.sf.nakeduml.pomgeneration;

import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.persistence.PersistenceStep;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;

@StepDependency(requires = { PersistenceStep.class }, before = {}, after = {}, phase = PomGenerationPhase.class)
public class BasicJavaAdaptorPomStep extends AbstractBasicJavaPomStep {
	@Override
	public Dependency[] getDependencies() {
		Collection<Dependency> result = getBasicDependencies("-adaptor");
		Dependency numlAdaptor=POMFactory.eINSTANCE.createDependency();
		numlAdaptor.setGroupId("org.nakeduml");
		numlAdaptor.setArtifactId("nakeduml-runtime-adaptor");
		numlAdaptor.setScope("compile");
		numlAdaptor.setVersion(PomGenerationPhase.NUML_VERSION);
		result.add(numlAdaptor);
		addCdi(result);
		addDependencyToRootObject("-domain", model, result);
		return (Dependency[]) result.toArray(new Dependency[result.size()]);
	}
	protected void addCdi(Collection<Dependency> dependencies) {
		Dependency cdi = POMFactory.eINSTANCE.createDependency();
		cdi.setGroupId("javax.enterprise");
		cdi.setArtifactId("cdi-api");
		cdi.setVersion("1.0-SP1");
		cdi.setScope("provided");
		cdi.setType("jar");
		dependencies.add(cdi);
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
