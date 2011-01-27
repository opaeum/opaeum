package net.sf.nakeduml.pomgeneration;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.persistence.PersistenceStep;

import org.apache.maven.pom.Activation;
import org.apache.maven.pom.ConfigurationType2;
import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.ExecutionsType;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;
import org.apache.maven.pom.PluginExecution;
import org.apache.maven.pom.Profile;
import org.apache.maven.pom.Resource;
import org.eclipse.emf.ecore.xml.type.AnyType;

@StepDependency(requires = { PersistenceStep.class }, before = {}, after = {}, phase = PomGenerationPhase.class)
public class ProjectEjbPomStep extends PomGenerationStep {

	@Override
	public boolean hasFinalName() {
		return true;
	}

	@Override
	public Profile[] getProfiles() {
		Profile[] profiles = new Profile[1];
		
		Profile profile = POMFactory.eINSTANCE.createProfile();
		profile.setId("jbossas-managed-6");
		Activation activation = POMFactory.eINSTANCE.createActivation();
		activation.setActiveByDefault(true);
		profile.setActivation(activation);
		
		profile.setDependencies(POMFactory.eINSTANCE.createDependenciesType2());
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.arquillian.container");
		dependency.setArtifactId("arquillian-jbossas-managed-6");
		profile.getDependencies().getDependency().add(dependency);
		
		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.jbossas");
		dependency.setArtifactId("jboss-server-manager");
		profile.getDependencies().getDependency().add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.jbossas");
		dependency.setArtifactId("jboss-as-client");
		dependency.setType("pom");
		dependency.setScope("test");
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
		pluginExecution.getGoals(). getGoal().add("enforce");
		
		pluginExecution.setConfiguration(POMFactory.eINSTANCE.createConfigurationType3());
		AnyType anyType = addAnyElement(pluginExecution.getConfiguration().getAny(), "rules");
		anyType = addAnyElement(anyType.getAny(),"requireProperty");
		addAnyElement(anyType.getAny(),"property", "jboss.home");

		addAnyElement(pluginExecution.getConfiguration().getAny(), "fail", "true");
		
		plugin.getExecutions().getExecution().add(pluginExecution);
		profile.getBuild().getPlugins().getPlugin().add(plugin);

		
		plugin = POMFactory.eINSTANCE.createPlugin();
		plugin.setGroupId("org.apache.maven.plugins");
		plugin.setArtifactId("maven-surefire-plugin");
		plugin.setVersion("2.7.1");
		
		plugin.setConfiguration(POMFactory.eINSTANCE.createConfigurationType2());
		addAnyElement(pluginExecution.getConfiguration().getAny(), "skip", "true");
		
		plugin.setExecutions(POMFactory.eINSTANCE.createExecutionsType());
		pluginExecution = POMFactory.eINSTANCE.createPluginExecution();
		pluginExecution.setId("surefire-it");
		pluginExecution.setPhase("integration-test");
		pluginExecution.setGoals(POMFactory.eINSTANCE.createGoalsType1());
		pluginExecution.getGoals(). getGoal().add("test");
		
		pluginExecution.setConfiguration(POMFactory.eINSTANCE.createConfigurationType3());
		addAnyElement(pluginExecution.getConfiguration().getAny(), "skip", "false");
		
		plugin.getExecutions().getExecution().add(pluginExecution);
		profile.getBuild().getPlugins().getPlugin().add(plugin);
		
		profiles[0] = profile;
		return profiles;
		
	}

	@Override
	public boolean hasParent() {
		return true;
	}

	public String getParentGroupId() {
		return super.getGroupId();
	}

	public String getParentArtifactId() {
		return super.getName();
	}

	public String getParentVersion() {
		return "0.0.1";
	}

	@Override
	public String getPackaging() {
		return "ejb";
	}

	@Override
	public Dependency[] getDependencies() {
		
		List<Dependency> dependencies = new ArrayList<Dependency>();
		
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.arquillian");
		dependency.setArtifactId("arquillian-junit");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("junit");
		dependency.setArtifactId("junit");
		dependencies.add(dependency);
		
		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("javax.enterprise");
		dependency.setArtifactId("cdi-api");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.spec");
		dependency.setArtifactId("jboss-javaee-6.0");
		dependency.setType("pom");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.slf4j");
		dependency.setArtifactId("slf4j-log4j12");
		dependencies.add(dependency);
		
		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.servlet");
		dependency.setArtifactId("seam-servlet-api");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.servlet");
		dependency.setArtifactId("seam-servlet-impl");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.persistence");
		dependency.setArtifactId("seam-persistence-api");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.persistence");
		dependency.setArtifactId("seam-persistence-impl");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.config");
		dependency.setArtifactId("seam-config-xml");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.solder");
		dependency.setArtifactId("seam-solder-api");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.solder");
		dependency.setArtifactId("seam-solder-impl");
		dependencies.add(dependency);
		
		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.nakeduml");
		dependency.setArtifactId("util");
		dependency.setType("ejb");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.hibernate");
		dependency.setArtifactId("hibernate-entitymanager");
		dependency.setType("jar");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.hibernate");
		dependency.setArtifactId("hibernate-validator");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jbpm");
		dependency.setArtifactId("jbpm-flow");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jbpm");
		dependency.setArtifactId("jbpm-flow-builder");
		dependencies.add(dependency);
		
		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jbpm");
		dependency.setArtifactId("jbpm-bpmn2");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jbpm");
		dependency.setArtifactId("jbpm-bam");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jbpm");
		dependency.setArtifactId("jbpm-persistence-jpa");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.drools");
		dependency.setArtifactId("drools-api");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.drools");
		dependency.setArtifactId("drools-compiler");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.drools");
		dependency.setArtifactId("drools-core");
		dependencies.add(dependency);

		return dependencies.toArray(new Dependency[dependencies.size()]);
	}

	@Override
	public String getTargetDir() {
		return JavaTextSource.NAKED_PROJECT_EJB_ROOT;
	}

	@Override
	public String getArtifactSuffix() {
		return "-ejb";
	}

	@Override
	public Plugin[] getPlugins() {
		Plugin[] result = new Plugin[3];
		result[0] = POMFactory.eINSTANCE.createPlugin();
		result[0].setGroupId("org.apache.maven.plugins");
		result[0].setArtifactId("maven-compiler-plugin");
		result[1] = POMFactory.eINSTANCE.createPlugin();
		result[1].setGroupId("org.apache.maven.plugins");
		result[1].setArtifactId("maven-ejb-plugin");
		result[1].setVersion("2.3");
		ConfigurationType2 cfg = POMFactory.eINSTANCE.createConfigurationType2();
		addAnyElement(cfg.getAny(), "ejbVersion", "3.0");
		result[1].setConfiguration(cfg);

		result[2] = POMFactory.eINSTANCE.createPlugin();
		result[2].setGroupId("org.codehaus.mojo");
		result[2].setArtifactId("build-helper-maven-plugin");

		ExecutionsType execution = POMFactory.eINSTANCE.createExecutionsType();
		PluginExecution pluginExecution = POMFactory.eINSTANCE.createPluginExecution();
		execution.getExecution().add(pluginExecution);
		pluginExecution.setId("add-source");
		pluginExecution.setPhase("generate-sources");
		pluginExecution.setGoals(POMFactory.eINSTANCE.createGoalsType1());
		pluginExecution.getGoals().getGoal().add("add-source");
		
		pluginExecution.setConfiguration(POMFactory.eINSTANCE.createConfigurationType3());
		AnyType anyType = addAnyElement(pluginExecution.getConfiguration().getAny(), "sources");
		addAnyElement(anyType.getAny(),"source", "src/main/generated-java");
		addAnyElement(anyType.getAny(),"source", "src/main/generated-resources");
		
		result[2].setExecutions(execution);

		
		return result;
	}

	@Override
	public String getName() {
		return super.getName() + "-ejb";
	}

}
