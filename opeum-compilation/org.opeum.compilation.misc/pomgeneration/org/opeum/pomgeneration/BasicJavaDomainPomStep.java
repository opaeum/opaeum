package org.opeum.pomgeneration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import org.opeum.feature.SourceFolderDefinition;
import org.opeum.feature.StepDependency;
import org.opeum.javageneration.JavaSourceFolderIdentifier;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;

@StepDependency(requires = {},before = {},after = {},phase = PomGenerationPhase.class)
public class BasicJavaDomainPomStep extends PomGenerationStep{
	@Override
	public Dependency[] getDependencies(){
		Collection<Dependency> result = getBasicDependencies(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		addNakedumlDomain(result);
		return (Dependency[]) result.toArray(new Dependency[result.size()]);
	}
	private void addNakedumlDomain(Collection<Dependency> result){
		Dependency nakedUmlUtil = POMFactory.eINSTANCE.createDependency();
		nakedUmlUtil.setGroupId("org.opeum");
		nakedUmlUtil.setArtifactId("opeum-runtime-domain");
		nakedUmlUtil.setVersion("${opeum.version}");
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
		props.put("opeum.version", PomGenerationPhase.NUML_VERSION);
		return props;
	}
}
