package net.sf.nakeduml.pomgeneration;

import java.util.Collection;
import java.util.Properties;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaTextSource;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;

@StepDependency(requires = {},before = {},after = {},phase = PomGenerationPhase.class)
public class BasicJavaDomainPomStep extends PomGenerationStep{
	@Override
	public Dependency[] getDependencies(){
		Collection<Dependency> result = getBasicDependencies("-domain");
		addNakedumlDomain(result);
		// add provided dependencies from above
		addHibernate(result);

		return (Dependency[]) result.toArray(new Dependency[result.size()]);
	}
	private void addNakedumlDomain(Collection<Dependency> result){
		Dependency nakedUmlUtil = POMFactory.eINSTANCE.createDependency();
		nakedUmlUtil.setGroupId("org.nakeduml");
		nakedUmlUtil.setArtifactId("nakeduml-runtime-domain");
		nakedUmlUtil.setVersion("${numl.version}");
		nakedUmlUtil.setScope("compile");
		nakedUmlUtil.setType("jar");
		result.add(nakedUmlUtil);
		excludeSlf4j(nakedUmlUtil);
	}
	@Override
	public Plugin[] getPlugins(){
		return super.getPlugins();
	}
	@Override
	protected OutputRoot getExampleTargetDir(){
		return config.getOutputRoot(JavaTextSource.OutputRootId.DOMAIN_GEN_SRC);
	}
	@Override
	public Properties getParentPomProperties(){
		Properties props = super.getParentPomProperties();
		props.put("hibernate.version", "3.6.0.Final");
		props.put("numl.version", PomGenerationPhase.NUML_VERSION);
		return props;
	}
}
