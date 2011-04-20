package net.sf.nakeduml.pomgeneration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.persistence.PersistenceStep;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;
import org.eclipse.emf.ecore.xml.type.AnyType;

@StepDependency(requires = { PersistenceStep.class }, before = {}, after = {}, phase = PomGenerationPhase.class)
public class BasicJavaAdaptorPomStep extends PomGenerationStep {
	@Override
	public Dependency[] getDependencies() {
		Collection<Dependency> result = getBasicDependencies("-adaptor");
		addDependencyToRootObject("-domain", model, result);
		addNumlAdaptor(result);
		addNumlTestAdaptor(result);
		//Provided dependencies from above projects
		addCdi(result);
		addJbossJeeSpec(result);
		addHibernate(result);
		addSlf4jLog4j(result);
		addHsqlDbForTest(result);
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

	private void addNumlAdaptor(Collection<Dependency> result) {
		Dependency numlAdaptor = POMFactory.eINSTANCE.createDependency();
		numlAdaptor.setGroupId("org.nakeduml");
		numlAdaptor.setArtifactId("nakeduml-runtime-adaptor");
		numlAdaptor.setScope("compile");
		numlAdaptor.setVersion("${numl.version}");
		result.add(numlAdaptor);
		excludeSlf4j(numlAdaptor);
	}

	@Override
	public Properties getProperties(){
		Properties properties = super.getProperties();
		properties.put("numl.version", PomGenerationPhase.NUML_VERSION);
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
	protected OutputRoot getExampleTargetDir() {
		return config.getOutputRoot(JavaTextSource.OutputRootId.ADAPTOR_GEN_SRC);
	}
	
	
	
}
