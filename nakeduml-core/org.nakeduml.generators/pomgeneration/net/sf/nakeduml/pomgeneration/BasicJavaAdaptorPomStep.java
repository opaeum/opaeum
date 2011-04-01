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
		addSeamScheduling(result);
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
//		Collection<Plugin> result = new ArrayList<Plugin>(Arrays.asList(super.getPlugins()));
//		result.add(excludeIntegrationTests());
		
		Collection<Plugin> result = new ArrayList<Plugin>(Arrays.asList(super.getPlugins()));
		Plugin sureFire = addSurefire();	
		AnyType excludes = PomUtil.addEmptyAnyElement(sureFire.getConfiguration().getAny(), "excludes");
		//TODO remove next line
		PomUtil.addAnyElementWithContent(excludes.getAny(), "exclude", "**/*WorkspaceMmlGeneratorTest.java");
		PomUtil.addAnyElementWithContent(excludes.getAny(), "exclude", "**/*IntegrationTest.java");
		result.add(sureFire);
		return (Plugin[]) result.toArray(new Plugin[result.size()]);
	}

	@Override
	protected OutputRoot getExampleTargetDir() {
		return config.getOutputRoot(JavaTextSource.OutputRootId.ADAPTOR_GEN_SRC);
	}
	
	private void addWeldCoreTest(Collection<Dependency> result) {
		Dependency weldCoreTest = POMFactory.eINSTANCE.createDependency();
		weldCoreTest.setGroupId("org.jboss.weld");
		weldCoreTest.setArtifactId("weld-core-test");
		weldCoreTest.setScope("test");
		weldCoreTest.setVersion("${weld.core.test.version}");
		result.add(weldCoreTest);
		Dependency servlet = POMFactory.eINSTANCE.createDependency();
		servlet.setGroupId("javax.servlet");
		servlet.setArtifactId("servlet-api");
		servlet.setScope("test");
		servlet.setVersion("2.5");
		result.add(servlet);
		Dependency jbossTestHarness = POMFactory.eINSTANCE.createDependency();
		jbossTestHarness.setGroupId("org.jboss.test-harness");
		jbossTestHarness.setArtifactId("jboss-test-harness");
		jbossTestHarness.setScope("test");
		jbossTestHarness.setVersion("1.1.0-SNAPSHOT");
		result.add(jbossTestHarness);
		
		Dependency weldSe = POMFactory.eINSTANCE.createDependency();
		weldSe.setGroupId("org.jboss.weld");
		weldSe.setArtifactId("weld-se");
		weldSe.setScope("test");
		weldSe.setVersion("1.0.1-Final");
		result.add(weldSe);
	}
	
	private void addSeamScheduling(Collection<Dependency> dependencies) {
		Dependency seamScheduling = POMFactory.eINSTANCE.createDependency();
		seamScheduling.setGroupId("org.jboss.seam");
		seamScheduling.setArtifactId("scheduling");
		seamScheduling.setVersion("${seam.scheduling.version}");
		seamScheduling.setScope("compile");
		seamScheduling.setType("jar");
		dependencies.add(seamScheduling);		
	}
	
}
