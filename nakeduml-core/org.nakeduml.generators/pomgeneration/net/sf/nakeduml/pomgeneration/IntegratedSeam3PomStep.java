package net.sf.nakeduml.pomgeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.CharArrayTextSource;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;

@StepDependency(phase = PomGenerationPhase.class, requires = { BasicJavaIntegratedAdaptorPomStep.class })
public class IntegratedSeam3PomStep extends PomGenerationStep {
	@Override
	public Dependency[] getDependencies() {
		List<Dependency> dependencies = new ArrayList<Dependency>();
		addJbossJeeSpec(dependencies);
		addCdi(dependencies);
		addSeamConfig(dependencies);
		Dependency nakedUmlUtil = POMFactory.eINSTANCE.createDependency();
		nakedUmlUtil.setGroupId("org.nakeduml");
		nakedUmlUtil.setArtifactId("nakeduml-runtime-adaptor");
		nakedUmlUtil.setVersion("${numl.version}");
		nakedUmlUtil.setType("jar");
		nakedUmlUtil.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		super.addArquillian(dependencies);
		return dependencies.toArray(new Dependency[dependencies.size()]);
	}

	@Override
	public Properties getParentPomProperties() {
		Properties p = super.getParentPomProperties();
		p.put("jboss.home", "${env.JBOSS_HOME}");
		p.put("jboss.domain", "default");
		p.put("numl.version",PomGenerationPhase.NUML_VERSION );
		return p;
	}

	@Override
	public boolean hasFinalName() {
		return true;
	}

	@Override
	public String getPackaging() {
		return "jar";
	}

	@Override
	public OutputRoot getExampleTargetDir() {
		return config.getOutputRoot(CharArrayTextSource.OutputRootId.INTEGRATED_ADAPTOR_GEN_RESOURCE);
	}




}
