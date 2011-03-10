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

@StepDependency(phase = PomGenerationPhase.class, requires = { BasicJavaIntegratedAdaptorPomStep.class })
public class IntegratedSeam3PomStep extends PomGenerationStep {
	@Override
	public Dependency[] getDependencies() {
		List<Dependency> dependencies = new ArrayList<Dependency>();
		addJbossJeeSpec(dependencies);
		addCdi(dependencies);
		Dependency slf4j = POMFactory.eINSTANCE.createDependency();
		slf4j.setGroupId("org.slf4j");
		slf4j.setArtifactId("slf4j-log4j12");
		slf4j.setVersion("1.6.1");
		dependencies.add(slf4j);
		Dependency seamPersistenceApi = POMFactory.eINSTANCE.createDependency();
		seamPersistenceApi.setGroupId("org.jboss.seam.persistence");
		seamPersistenceApi.setArtifactId("seam-persistence-api");
		seamPersistenceApi.setVersion("${seam.persistence.version}");
		seamPersistenceApi.setScope("compile");
		dependencies.add(seamPersistenceApi);
		Dependency seamPersistenceImpl = POMFactory.eINSTANCE.createDependency();
		seamPersistenceImpl.setGroupId("org.jboss.seam.persistence");
		seamPersistenceImpl.setArtifactId("seam-persistence-impl");
		seamPersistenceImpl.setVersion("${seam.persistence.version}");
		seamPersistenceImpl.setScope("runtime");
		seamPersistenceImpl.setType("jar");
		dependencies.add(seamPersistenceImpl);
		Dependency solder = POMFactory.eINSTANCE.createDependency();
		solder.setGroupId("org.jboss.seam.solder");
		solder.setArtifactId("seam-solder-api");
		solder.setVersion("${seam.solder.version}");
		solder.setScope("compile");
		Exclusion excludeLoggin = POMFactory.eINSTANCE.createExclusion();
		excludeLoggin.setGroupId("org.jboss.logging");
		excludeLoggin.setArtifactId("jboss-logging");
		solder.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		solder.getExclusions().getExclusion().add(excludeLoggin);
		dependencies.add(solder);
		Dependency solderImpl = POMFactory.eINSTANCE.createDependency();
		solderImpl.setGroupId("org.jboss.seam.solder");
		solderImpl.setArtifactId("seam-solder-impl");
		solderImpl.setVersion("${seam.solder.version}");
		solderImpl.setScope("runtime");
		dependencies.add(solderImpl);
		solderImpl = POMFactory.eINSTANCE.createDependency();
		solderImpl.setGroupId("org.jboss.seam.config");
		solderImpl.setArtifactId("seam-config-xml");
		solderImpl.setVersion("3.0.0.Beta2");
		solderImpl.setScope("test");
		dependencies.add(solderImpl);
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
		p.put("seam.persistence.version", "3.0.0-SNAPSHOT");
		p.put("seam.solder.version", "3.0.0.Beta1");
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
