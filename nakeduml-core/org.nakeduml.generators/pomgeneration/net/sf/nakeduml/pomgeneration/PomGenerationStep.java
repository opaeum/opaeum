package net.sf.nakeduml.pomgeneration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.model.IImportedElement;

import org.apache.maven.pom.Activation;
import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.Exclusion;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;
import org.apache.maven.pom.PluginExecution;
import org.apache.maven.pom.Profile;
import org.apache.maven.pom.Resource;
import org.eclipse.emf.ecore.xml.type.AnyType;

public abstract class PomGenerationStep implements TransformationStep {
	public static final String ARQUILLIAN_VERSION = "1.0.0.Alpha4";
	protected NakedUmlConfig config;
	protected INakedModelWorkspace workspace;
	protected INakedRootObject model;

	protected abstract OutputRoot getExampleTargetDir();

	public void initialize(NakedUmlConfig config, INakedModelWorkspace workspace) {
		this.config = config;
		this.workspace = workspace;
	}
	public String getVersionVariable(){
		return "${" +workspace.getDirectoryName() + ".version}";
	}
	public String getPackaging() {
		return "jar";
	}

	public Dependency[] getDependencies() {
		return new Dependency[0];
	}

	public Plugin[] getPlugins() {
		return new Plugin[0];
	}

	public boolean useWorkspaceName() {
		return this.getExampleTargetDir().useWorkspaceName();
	}

	public final String getProjectName() {
		if (useWorkspaceName()) {
			return this.workspace.getDirectoryName() + getExampleTargetDir().getProjectSuffix();
		} else {
			return this.model.getFileName() + getExampleTargetDir().getProjectSuffix();
		}
	}

	public boolean hasFinalName() {
		return false;
	}


	public Properties getParentPomProperties() {
		return new Properties();
	}

	public Profile[] getProfiles() {
		return new Profile[0];
	}

	public void setModel(INakedRootObject model) {
		this.model = model;
	}

	public Properties getProperties() {
		return new Properties();
	}
	protected void addHibernate(Collection<Dependency> dependencies){
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.hibernate");
		dependency.setArtifactId("hibernate-core");
		dependency.setVersion("${hibernate.version}");
		dependency.setType("jar");
		dependency.setScope("provided");
		//Clashes with slf4j in weld-core-test and weld-se
		excludeSlf4j(dependency);
		dependencies.add(dependency);
	}

	protected void excludeSlf4j(Dependency dependency) {
		Exclusion excludeSlf4j = POMFactory.eINSTANCE.createExclusion();
		excludeSlf4j.setGroupId("org.slf4j");
		excludeSlf4j.setArtifactId("slf4j-api");
		dependency.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		dependency.getExclusions().getExclusion().add(excludeSlf4j);
	}
	protected void addArquillian(Collection<Dependency> dependencies) {
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.arquillian");
		dependency.setArtifactId("arquillian-junit");
		dependency.setVersion("${arquillian.version}");
		dependency.setType("jar");
		dependency.setScope("test");
		
		dependencies.add(dependency);
	}
//TODO move to WarPomStep when we have figured out how to do integreation tests from an ejb
	protected void addSeamServlet(Collection<Dependency> dependencies) {
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.servlet");
		dependency.setArtifactId("seam-servlet-api");
		dependency.setVersion("${seam.servlet.version}");
		dependency.setScope("compile");
		dependencies.add(dependency);
	}
	protected void addSeamServletImpl(Collection<Dependency> dependencies) {
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.servlet");
		dependency.setArtifactId("seam-servlet-impl");
		dependency.setVersion("${seam.servlet.version}");
		dependency.setScope("runtime");
		dependencies.add(dependency);
		Exclusion solder = POMFactory.eINSTANCE.createExclusion();
		solder.setGroupId("org.jboss.seam.solder");
		solder.setArtifactId("seam-solder");
		dependency.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		dependency.getExclusions().getExclusion().add(solder);
	}
	
	protected void addSeamSolderImpl(Collection<Dependency> dependencies) {
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
	}

	protected void addSeamSolderApi(Collection<Dependency> dependencies) {
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
	}	

	protected void addCdi(Collection<Dependency> dependencies) {
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("javax.enterprise");
		dependency.setArtifactId("cdi-api");
		dependency.setVersion("1.0-SP1");
		dependency.setScope("provided");
		dependency.setType("jar");
		dependencies.add(dependency);
	}

	protected void addJbossJeeSpec(Collection<Dependency> dependencies) {
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.spec");
		dependency.setArtifactId("jboss-javaee-6.0");
		dependency.setVersion("1.0.0.Final");
		dependency.setScope("provided");
		dependency.setType("pom");
		dependencies.add(dependency);
	}

	protected void addNumlTestAdaptor(Collection<Dependency> result) {
		Dependency numlAdaptor=POMFactory.eINSTANCE.createDependency();
		numlAdaptor.setGroupId("org.nakeduml");
		numlAdaptor.setArtifactId("nakeduml-test-adaptor");
		numlAdaptor.setScope("test");
		numlAdaptor.setVersion("${numl.version}");
		result.add(numlAdaptor);
	}

	protected Collection<Dependency> getTestDepedencies() {
		Collection<Dependency> result = new ArrayList<Dependency>();
		Dependency jMock = POMFactory.eINSTANCE.createDependency();
		jMock.setGroupId("org.jmock");
		jMock.setArtifactId("jmock");
		jMock.setVersion("2.5.1");
		jMock.setType("jar");
		jMock.setScope("test");
		result.add(jMock);
		Dependency jMockLegacy = POMFactory.eINSTANCE.createDependency();
		jMockLegacy.setGroupId("org.jmock");
		jMockLegacy.setArtifactId("jmock-legacy");
		jMockLegacy.setVersion("2.5.1");
		jMockLegacy.setScope("test");
		jMockLegacy.setType("jar");
		result.add(jMockLegacy);
		Dependency testNg = POMFactory.eINSTANCE.createDependency();
		testNg.setGroupId("junit");
		testNg.setArtifactId("junit");
		testNg.setVersion("4.8.2");
		testNg.setScope("test");
		testNg.setType("jar");
		result.add(testNg);
		return result;
	}

	protected Collection<Dependency> getBasicDependencies(String projectSuffix) {
		Collection<Dependency> result = getTestDepedencies();
		Collection<IImportedElement> imports = this.model.getImports();
		for (IImportedElement imp : imports) {
			if (imp.getElement() instanceof INakedRootObject) {
				addDependencyToRootObject(projectSuffix, (INakedRootObject) imp.getElement(),result);
			}
		}
		return result;
	}

	protected void addDependencyToRootObject(String projectSuffix, INakedRootObject rootObject, Collection<Dependency> result) {
		if (workspace.isPrimaryModel(rootObject)) {
			Dependency d = POMFactory.eINSTANCE.createDependency();
			d.setGroupId(config.getMavenGroupId());
			d.setArtifactId(rootObject.getFileName()+projectSuffix);
			d.setVersion(getVersionVariable());
			d.setScope("compile");
			d.setType("jar");
			result.add(d);
		}else{
			//TODO Model level stereotype, or numlconfig.properties get group id and version artifactid=filename
		}
	}

	public Profile createArquillianProfile() {
		Profile profile = POMFactory.eINSTANCE.createProfile();
		profile.setId("jbossas-managed-6");
		Activation activation = POMFactory.eINSTANCE.createActivation();
		activation.setActiveByDefault(false);
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
		
		plugin = addSurefire();
		PomUtil.addAnyElementWithContent(pluginExecution.getConfiguration().getAny(), "skip", "true");
		plugin.setExecutions(POMFactory.eINSTANCE.createExecutionsType());
		pluginExecution = POMFactory.eINSTANCE.createPluginExecution();
		pluginExecution.setId("surefire-it");
		pluginExecution.setPhase("integration-test");
		pluginExecution.setGoals(POMFactory.eINSTANCE.createGoalsType1());
		pluginExecution.getGoals().getGoal().add("test");
		pluginExecution.setConfiguration(POMFactory.eINSTANCE.createConfigurationType3());

		AnyType excludes = PomUtil.addEmptyAnyElement(pluginExecution.getConfiguration().getAny(), "excludes");
		PomUtil.addAnyElementWithContent(excludes.getAny(), "exclude", "none");
		AnyType includes = PomUtil.addEmptyAnyElement(pluginExecution.getConfiguration().getAny(), "includes");
		PomUtil.addAnyElementWithContent(includes.getAny(), "include", "**/*IntegrationTest.java");
		plugin.getExecutions().getExecution().add(pluginExecution);
		profile.getBuild().getPlugins().getPlugin().add(plugin);
		return profile;
	}

	protected Plugin addSurefire() {
		Plugin plugin;
		plugin = POMFactory.eINSTANCE.createPlugin();
		plugin.setGroupId("org.apache.maven.plugins");
		plugin.setArtifactId("maven-surefire-plugin");
		plugin.setVersion("2.7.1");
		plugin.setConfiguration(POMFactory.eINSTANCE.createConfigurationType2());
		return plugin;
	}
}
