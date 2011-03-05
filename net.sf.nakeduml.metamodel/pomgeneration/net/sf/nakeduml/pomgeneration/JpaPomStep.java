package net.sf.nakeduml.pomgeneration;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.CharArrayTextSource;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;

@StepDependency(requires = {BasicJavaDomainPomStep.class}, before = {}, after = {}, phase = PomGenerationPhase.class)
public class JpaPomStep extends PomGenerationStep {

	@Override
	public Dependency[] getDependencies() {
		Collection<Dependency> result = new ArrayList<Dependency>();
		Dependency nakedUmlUtil = POMFactory.eINSTANCE.createDependency();
		nakedUmlUtil.setGroupId("org.nakeduml");
		nakedUmlUtil.setArtifactId("nakeduml-runtime-domain");
		nakedUmlUtil.setVersion(PomGenerationPhase.NUML_VERSION);
		nakedUmlUtil.setScope("compile");
		nakedUmlUtil.setType("jar");
		result.add(nakedUmlUtil);
		return (Dependency[]) result.toArray(new Dependency[result.size()]);
	}

	@Override
	public OutputRoot getExampleTargetDir() {
		return config.getOutputRoot(CharArrayTextSource.OutputRootId.DOMAIN_GEN_RESOURCE);
	}


	

}
