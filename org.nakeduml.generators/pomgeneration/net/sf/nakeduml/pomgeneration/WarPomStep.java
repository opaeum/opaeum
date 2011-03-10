package net.sf.nakeduml.pomgeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.CharArrayTextSource;

import org.apache.maven.pom.Activation;
import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.Exclusion;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;
import org.apache.maven.pom.PluginExecution;
import org.apache.maven.pom.Profile;
import org.apache.maven.pom.Resource;
import org.eclipse.emf.ecore.xml.type.AnyType;

@StepDependency(phase = PomGenerationPhase.class, requires = { IntegratedSeam3PomStep.class })
public class WarPomStep extends PomGenerationStep {

	@Override
	public Dependency[] getDependencies() {
		List<Dependency> dependencies = new ArrayList<Dependency>();
		Dependency integrated = POMFactory.eINSTANCE.createDependency();
		integrated.setGroupId(config.getMavenGroupId());
		integrated.setArtifactId(workspace.getDirectoryName() + "-integrated");
		integrated.setVersion(config.getMavenGroupVersion());
		integrated.setScope("compile");
		dependencies.add(integrated);
		addJbossJeeSpec(dependencies);
		addCdi(dependencies);
		addSlf4j(dependencies);
		addSeamServlet(dependencies);
		addSeamServletImpl(dependencies);
		addSolderApi(dependencies);
		addSolderImpl(dependencies);
		addSeamConfig(dependencies);
		addArquillian(dependencies);
		addNumlTestAdaptor(dependencies);
		return dependencies.toArray(new Dependency[dependencies.size()]);
	}

	private void addSeamConfig(List<Dependency> dependencies) {
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.config");
		dependency.setArtifactId("seam-config-xml");
		dependency.setVersion("3.0.0.Beta2");
		dependency.setScope("test");
		dependencies.add(dependency);
	}

	private void addSolderImpl(List<Dependency> dependencies) {
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.solder");
		dependency.setArtifactId("seam-solder-impl");
		dependency.setVersion("${seam.solder.version}");
		dependency.setScope("runtime");
		dependencies.add(dependency);
	}

	private void addSolderApi(List<Dependency> dependencies) {
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.solder");
		dependency.setArtifactId("seam-solder-api");
		dependency.setVersion("${seam.solder.version}");
		dependency.setScope("compile");
		Exclusion exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("org.jboss.logging");
		exclusion.setArtifactId("jboss-logging");
		dependency.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		dependency.getExclusions().getExclusion().add(exclusion);
		dependencies.add(dependency);
	}


	private void addSlf4j(List<Dependency> dependencies) {
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.slf4j");
		dependency.setArtifactId("slf4j-log4j12");
		dependency.setVersion("1.6.1");
		dependencies.add(dependency);
	}

	@Override
	public Properties getParentPomProperties() {
		Properties p = super.getParentPomProperties();
		p.put("jboss.home", "${env.JBOSS_HOME}");
		p.put("jboss.domain", "default");
		p.put("arquillian.version", ARQUILLIAN_VERSION);
		return p;
	}

	@Override
	public boolean hasFinalName() {
		return true;
	}

	@Override
	public String getPackaging() {
		return "war";
	}

	@Override
	public OutputRoot getExampleTargetDir() {
		return config.getOutputRoot(CharArrayTextSource.OutputRootId.WEBAPP_RESOURCE);
	}

	@Override
	public Profile[] getProfiles() {
		Profile[] profiles = new Profile[2];
		Profile profile = POMFactory.eINSTANCE.createProfile();
		profile.setId("jbossas-managed-6");
		Activation activation = POMFactory.eINSTANCE.createActivation();
		activation.setActiveByDefault(true);
		profile.setActivation(activation);
		profile.setDependencies(POMFactory.eINSTANCE.createDependenciesType2());
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.arquillian.container");
		dependency.setArtifactId("arquillian-jbossas-managed-6");
		dependency.setVersion("${arquillian.version}");
		dependency.setType("jar");
		dependency.setScope("test");
		profile.getDependencies().getDependency().add(dependency);
		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.jbossas");
		dependency.setArtifactId("jboss-server-manager");
		dependency.setVersion("1.0.3.GA");
		dependency.setType("jar");
		dependency.setScope("test");
		profile.getDependencies().getDependency().add(dependency);
		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.jbossas");
		dependency.setArtifactId("jboss-as-client");
		dependency.setVersion("6.0.0.20101110-CR1");
		dependency.setType("pom");
		dependency.setScope("test");
		Exclusion exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("cglib");
		exclusion.setArtifactId("cglib");
		dependency.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		dependency.getExclusions().getExclusion().add(exclusion);
		profile.getDependencies().getDependency().add(dependency);
		profile.setBuild(POMFactory.eINSTANCE.createBuildBase());
		profile.getBuild().setTestResources(POMFactory.eINSTANCE.createTestResourcesType());
		Resource testResource = POMFactory.eINSTANCE.createResource();
		testResource.setDirectory("src/test/resources");
		profile.getBuild().getTestResources().getTestResource().add(testResource);
		testResource = POMFactory.eINSTANCE.createResource();
		testResource.setDirectory("src/test/resources-jbossas");
		profile.getBuild().getTestResources().getTestResource().add(testResource);
		profile.getBuild().setPlugins(POMFactory.eINSTANCE.createPluginsType());
		Plugin plugin = POMFactory.eINSTANCE.createPlugin();
		plugin.setGroupId("org.apache.maven.plugins");
		plugin.setArtifactId("maven-enforcer-plugin");
		plugin.setVersion("1.0");
		plugin.setExecutions(POMFactory.eINSTANCE.createExecutionsType());
		PluginExecution pluginExecution = POMFactory.eINSTANCE.createPluginExecution();
		pluginExecution.setId("enforce-property");
		pluginExecution.setGoals(POMFactory.eINSTANCE.createGoalsType1());
		pluginExecution.getGoals().getGoal().add("enforce");
		pluginExecution.setConfiguration(POMFactory.eINSTANCE.createConfigurationType3());
		AnyType anyType = PomUtil.addEmptyAnyElement(pluginExecution.getConfiguration().getAny(), "rules");
		anyType = PomUtil.addEmptyAnyElement(anyType.getAny(), "requireProperty");
		PomUtil.addAnyElementWithContent(anyType.getAny(), "property", "jboss.home");
		PomUtil.addAnyElementWithContent(pluginExecution.getConfiguration().getAny(), "fail", "true");
		plugin.getExecutions().getExecution().add(pluginExecution);
		profile.getBuild().getPlugins().getPlugin().add(plugin);
		plugin = POMFactory.eINSTANCE.createPlugin();
		plugin.setGroupId("org.apache.maven.plugins");
		plugin.setArtifactId("maven-surefire-plugin");
		plugin.setVersion("2.7.1");
		plugin.setConfiguration(POMFactory.eINSTANCE.createConfigurationType2());
		PomUtil.addAnyElementWithContent(pluginExecution.getConfiguration().getAny(), "skip", "true");
		plugin.setExecutions(POMFactory.eINSTANCE.createExecutionsType());
		pluginExecution = POMFactory.eINSTANCE.createPluginExecution();
		pluginExecution.setId("surefire-it");
		pluginExecution.setPhase("integration-test");
		pluginExecution.setGoals(POMFactory.eINSTANCE.createGoalsType1());
		pluginExecution.getGoals().getGoal().add("test");
		pluginExecution.setConfiguration(POMFactory.eINSTANCE.createConfigurationType3());
		PomUtil.addAnyElementWithContent(pluginExecution.getConfiguration().getAny(), "skip", "false");
		plugin.getExecutions().getExecution().add(pluginExecution);
		profile.getBuild().getPlugins().getPlugin().add(plugin);
		profiles[0] = profile;
		profile = POMFactory.eINSTANCE.createProfile();
		profile.setId("copy-war");
		profile.setBuild(POMFactory.eINSTANCE.createBuildBase());
		profile.getBuild().setPlugins(POMFactory.eINSTANCE.createPluginsType());
		plugin = POMFactory.eINSTANCE.createPlugin();
		plugin.setGroupId("org.apache.maven.plugins");
		plugin.setArtifactId("maven-antrun-plugin");
		plugin.setVersion("1.1");
		plugin.setGoals(POMFactory.eINSTANCE.createGoalsType());
		profile.getBuild().getPlugins().getPlugin().add(plugin);
		PomUtil.addAnyElementWithContent(plugin.getGoals().getAny(), "goal", "run");
		plugin.setConfiguration(POMFactory.eINSTANCE.createConfigurationType2());
		AnyType taskAnyType = PomUtil.addEmptyAnyElement(plugin.getConfiguration().getAny(), "tasks");
		anyType = PomUtil.addEmptyAnyElement(taskAnyType.getAny(), "delete");
		PomUtil.addAnyAttribute(anyType, "dir", "${jboss.home}/server/${jboss.domain}/deploy/${project.build.finalName}.war");
		anyType = PomUtil.addEmptyAnyElement(taskAnyType.getAny(), "copy");
		PomUtil.addAnyAttribute(anyType, "todir", "${jboss.home}/server/${jboss.domain}/deploy/${project.build.finalName}.war/");
		anyType = PomUtil.addEmptyAnyElement(anyType.getAny(), "fileset");
		PomUtil.addAnyAttribute(anyType, "dir", "target/${project.build.finalName}");
		anyType = PomUtil.addEmptyAnyElement(anyType.getAny(), "include");
		PomUtil.addAnyAttribute(anyType, "name", "**/*");
		profiles[1] = profile;
		return profiles;
	}
}
