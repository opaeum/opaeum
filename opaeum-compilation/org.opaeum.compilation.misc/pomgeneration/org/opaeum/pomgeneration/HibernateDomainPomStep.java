package org.opaeum.pomgeneration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import org.opaeum.feature.StepDependency;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.textmetamodel.SourceFolderDefinition;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;

@StepDependency(requires = {},before = {},after = {},phase = PomGenerationPhase.class)
public class HibernateDomainPomStep extends PomGenerationStep{
	@Override
	public Dependency[] getDependencies(){
		Collection<Dependency> result = getBasicDependencies(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		addNakedumlHibernate(result);
		// add provided dependencies from above
		addHibernate(result);
		addHsqlDbForTest(result);

		return (Dependency[]) result.toArray(new Dependency[result.size()]);
	}
	private void addNakedumlHibernate(Collection<Dependency> result){
		//only insert when BpmUsingJbpm5 is selected
		Dependency nakedUmlBpm= POMFactory.eINSTANCE.createDependency();
		nakedUmlBpm.setGroupId("org.opaeum");
		nakedUmlBpm.setArtifactId("opaeum-runtime-bpm-hibernate");
		nakedUmlBpm.setVersion("${opaeum.version}");
		nakedUmlBpm.setScope("compile");
		nakedUmlBpm.setType("jar");
		result.add(nakedUmlBpm);
		Dependency nakedUmlUtil = POMFactory.eINSTANCE.createDependency();
		nakedUmlUtil.setGroupId("org.opaeum");
		nakedUmlUtil.setArtifactId("opaeum-runtime-hibernate");
		nakedUmlUtil.setVersion("${opaeum.version}");
		nakedUmlUtil.setScope("compile");
		nakedUmlUtil.setType("jar");
		result.add(nakedUmlUtil);
		excludeSlf4j(nakedUmlUtil);
	}
	@Override
	public Plugin[] getPlugins(){
		Collection<Plugin> result = new ArrayList<Plugin>(Arrays.asList(super.getPlugins()));
		Plugin sureFire = addSurefire();	
		result.add(sureFire);
		return (Plugin[]) result.toArray(new Plugin[result.size()]);
	}
	@Override
	protected SourceFolderDefinition getExampleTargetDir(){
		return config.getSourceFolderDefinition(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}
	@Override
	public Properties getParentPomProperties(){
		Properties props = super.getParentPomProperties();
		props.put("hibernate.version", HIBERNATE_VERSION);
		props.put("opaeum.version", PomGenerationPhase.NUML_VERSION);
		return props;
	}
}
