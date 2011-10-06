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
import org.eclipse.emf.ecore.xml.type.AnyType;

@StepDependency(requires = { }, before = {}, after = {}, phase = PomGenerationPhase.class)
public class BasicJavaAdaptorPomStep extends PomGenerationStep {
	@Override
	public Dependency[] getDependencies() {
		Collection<Dependency> result = getBasicDependencies(JavaSourceFolderIdentifier.ADAPTOR_GEN_SRC);
		addDependencyToRootObject(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, model, result);
		addSlf4jLog4j(result);
		return (Dependency[]) result.toArray(new Dependency[result.size()]);
	}
	
	private void addSlf4jLog4j(Collection<Dependency> result) {
		Dependency slf4j = POMFactory.eINSTANCE.createDependency();
		slf4j.setGroupId("org.slf4j");
		slf4j.setArtifactId("slf4j-log4j12");
		slf4j.setScope("compile");
		slf4j.setVersion("1.5.10");
		result.add(slf4j);
	}


	@Override
	public Properties getProperties(){
		Properties properties = super.getProperties();
		properties.put("opaeum.version", PomGenerationPhase.NUML_VERSION);
		return properties;
	}

	@Override
	public Plugin[] getPlugins() {
		Collection<Plugin> result = new ArrayList<Plugin>(Arrays.asList(super.getPlugins()));

		Plugin sureFire = addSurefire();	
		AnyType excludes = PomUtil.addEmptyAnyElement(sureFire.getConfiguration().getAny(), "excludes");
		
	
		PomUtil.addAnyElementWithContent(excludes.getAny(), "exclude", "**/*IntegrationTest.java");
		result.add(sureFire);
		return (Plugin[]) result.toArray(new Plugin[result.size()]);
	}

	@Override
	protected SourceFolderDefinition getExampleTargetDir() {
		return config.getSourceFolderDefinition(JavaSourceFolderIdentifier.ADAPTOR_GEN_SRC);
	}
	
	
	
}
