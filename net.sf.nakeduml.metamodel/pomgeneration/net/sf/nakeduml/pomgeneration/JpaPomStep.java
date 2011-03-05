package net.sf.nakeduml.pomgeneration;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.persistence.PersistenceStep;

import org.apache.maven.pom.ConfigurationType2;
import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;

@StepDependency(requires = {BasicJavaDomainPomStep.class}, before = {}, after = {}, phase = PomGenerationPhase.class)
public class JpaPomStep extends PomGenerationStep {

	@Override
	public Dependency[] getDependencies() {
		Collection<Dependency> result = new ArrayList<Dependency>();
		Dependency persistenceApi = POMFactory.eINSTANCE.createDependency();
		persistenceApi.setGroupId("javax.persistence");
		persistenceApi.setArtifactId("persistence-api");
		persistenceApi.setVersion("1.0");
		persistenceApi.setScope("provided");
		persistenceApi.setType("jar");
		result.add(persistenceApi);
		Dependency nakedUmlUtil = POMFactory.eINSTANCE.createDependency();
		nakedUmlUtil.setGroupId("net.sf.nakeduml");
		nakedUmlUtil.setArtifactId("nakedumlutil");
		nakedUmlUtil.setVersion("0.0.1");
		nakedUmlUtil.setScope("compile");
		nakedUmlUtil.setType("jar");
		return (Dependency[]) result.toArray(new Dependency[result.size()]);
	}

	@Override
	public OutputRoot getExampleTargetDir() {
		return config.getOutputRoot(CharArrayTextSource.OutputRootId.DOMAIN_GEN_RESOURCE);
	}


	

}
