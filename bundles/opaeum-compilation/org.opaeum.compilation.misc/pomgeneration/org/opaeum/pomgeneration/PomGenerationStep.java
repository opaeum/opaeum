package org.opaeum.pomgeneration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import org.apache.maven.pom.Activation;
import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.Exclusion;
import org.apache.maven.pom.ExclusionsType;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;
import org.apache.maven.pom.PluginExecution;
import org.apache.maven.pom.Profile;
import org.apache.maven.pom.Resource;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.metamodel.workspace.MigrationWorkspace;
import org.opaeum.textmetamodel.ISourceFolderIdentifier;
import org.opaeum.textmetamodel.SourceFolderDefinition;

public abstract class PomGenerationStep implements ITransformationStep{
	protected static final String HIBERNATE_VERSION = "3.4.0.GA";
	public static final String ARQUILLIAN_VERSION = "1.0.0.Alpha4";
	protected OpaeumConfig config;
	protected EmfWorkspace workspace;
	protected Package model;
	private boolean shouldAppendVersionSuffix;
	protected MigrationWorkspace migrationWorkspace;
	protected abstract SourceFolderDefinition getExampleTargetDir();
	public boolean isIntegrationStep(){
		return false;
	}
	public void appendVersionSuffix(boolean b){
		this.shouldAppendVersionSuffix = b;
	}
	public void initialize(OpaeumConfig config,EmfWorkspace workspace,MigrationWorkspace migrationWorkspace){
		this.config = config;
		this.workspace = workspace;
		this.migrationWorkspace = migrationWorkspace;
	}
	public String getVersionVariable(){
		return "${" + workspace.getIdentifier() + ".version}";
	}
	public String getPackaging(){
		return "jar";
	}
	public Dependency[] getDependencies(){
		return new Dependency[0];
	}
	public Plugin[] getPlugins(){
		return new Plugin[0];
	}
	protected Plugin excludeIntegrationTests(){
		Plugin sureFire = addSurefire();
		AnyType excludes = PomUtil.addEmptyAnyElement(sureFire.getConfiguration().getAny(), "excludes");
		// TODO make none
		// PomUtil.addAnyElementWithContent(excludes.getAny(), "exclude", "none");
		PomUtil.addAnyElementWithContent(excludes.getAny(), "exclude", "**/*IntegrationTest.java");
		return sureFire;
	}
	public final String getProjectName(){
		String identifier = model ==null? null:EmfPackageUtil.getIdentifier(this.model);
		String projectName = getExampleTargetDir().generateProjectName(workspace.getIdentifier(), config.getMavenGroupId(),
				identifier);
		if(shouldAppendVersionSuffix){
			projectName = projectName + config.getMavenGroupVersionSuffix();
		}
		return projectName;
	}
	public boolean hasFinalName(){
		return false;
	}
	public Properties getParentPomProperties(){
		return new Properties();
	}
	public Profile[] getProfiles(){
		return new Profile[0];
	}
	public void setModel(Package model){
		this.model = model;
	}
	public Properties getProperties(){
		return new Properties();
	}
	protected void addHsqlDbForTest(Collection<Dependency> dependencies){
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("hsqldb");
		dependency.setArtifactId("hsqldb");
		dependency.setVersion("1.8.0.10");
		dependency.setType("jar");
		dependency.setScope("test");
		dependencies.add(dependency);
	}
	protected void addHibernate(Collection<Dependency> dependencies){
		Dependency hibernate = POMFactory.eINSTANCE.createDependency();
		hibernate.setGroupId("org.hibernate");
		hibernate.setArtifactId("hibernate-core");
		hibernate.setVersion("3.3.2.GA");
		hibernate.setType("jar");
		hibernate.setScope("provided");
		// Clashes with slf4j in weld-core-test and weld-se
		excludeSlf4j(hibernate);
		// Clashes with antlr in Drools
		excludeAntlr(hibernate);
		// To Keep Eclipse classpath clean
		excludeJta(hibernate);
		dependencies.add(hibernate);
		Dependency validation = POMFactory.eINSTANCE.createDependency();
		validation.setGroupId("org.hibernate");
		validation.setArtifactId("hibernate-validator");
		validation.setVersion("4.0.0.GA");
		validation.setType("jar");
		validation.setScope("provided");
		dependencies.add(validation);
		Dependency annotations = POMFactory.eINSTANCE.createDependency();
		annotations.setGroupId("org.hibernate");
		annotations.setArtifactId("hibernate-annotations");
		annotations.setVersion("3.4.0.GA");
		annotations.setScope("provided");
		annotations.setType("jar");
		dependencies.add(annotations);
	}
	private void excludeJta(Dependency dependency){
		Exclusion jta = POMFactory.eINSTANCE.createExclusion();
		jta.setGroupId("javax.transaction");
		jta.setArtifactId("jta");
		dependency.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		dependency.getExclusions().getExclusion().add(jta);
	}
	protected void excludeSlf4j(Dependency dependency){
		Exclusion excludeSlf4j = POMFactory.eINSTANCE.createExclusion();
		excludeSlf4j.setGroupId("org.slf4j");
		excludeSlf4j.setArtifactId("slf4j-api");
		dependency.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		dependency.getExclusions().getExclusion().add(excludeSlf4j);
	}
	protected void excludeAntlr(Dependency dependency){
		Exclusion antlr = POMFactory.eINSTANCE.createExclusion();
		antlr.setGroupId("antlr");
		antlr.setArtifactId("anltr");
		dependency.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		dependency.getExclusions().getExclusion().add(antlr);
	}
	protected void addArquillian(Collection<Dependency> dependencies){
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.arquillian");
		dependency.setArtifactId("arquillian-junit");
		dependency.setVersion("${arquillian.version}");
		dependency.setType("jar");
		dependency.setScope("test");
		dependencies.add(dependency);
	}
	// TODO move to BasicWarPomStep when we have figured out how to do integreation
	// tests from an ejb
	protected void addSeamServlet(Collection<Dependency> dependencies){
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.servlet");
		dependency.setArtifactId("seam-servlet-api");
		dependency.setVersion("${seam.servlet.version}");
		dependency.setScope("compile");
		dependencies.add(dependency);
	}
	protected void addSeamServletImpl(Collection<Dependency> dependencies){
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.servlet");
		dependency.setArtifactId("seam-servlet-impl");
		dependency.setVersion("${seam.servlet.version}");
		dependency.setScope("runtime");
		dependencies.add(dependency);
		dependency.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		exludeSeamSolder(dependency.getExclusions());
	}
	protected void addSeamConfig(Collection<Dependency> dependencies){
		Dependency seamConfig = POMFactory.eINSTANCE.createDependency();
		seamConfig.setGroupId("org.jboss.seam.config");
		seamConfig.setArtifactId("seam-config-xml");
		seamConfig.setVersion("3.0.0.Final");
		seamConfig.setScope("test");
		seamConfig.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		ExclusionsType exclusions = seamConfig.getExclusions();
		exludeSeamSolder(exclusions);
		dependencies.add(seamConfig);
	}
	private void exludeSeamSolder(ExclusionsType exclusions){
		Exclusion excludeSolder = POMFactory.eINSTANCE.createExclusion();
		excludeSolder.setArtifactId("seam-solder");
		excludeSolder.setGroupId("org.jboss.seam.solder");
		exclusions.getExclusion().add(excludeSolder);
	}
	protected void addCdi(Collection<Dependency> dependencies){
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("javax.enterprise");
		dependency.setArtifactId("cdi-api");
		dependency.setVersion("1.0-SP1");
		dependency.setScope("provided");
		dependency.setType("jar");
		dependencies.add(dependency);
	}
	protected void addJbossJee6Spec(Collection<Dependency> dependencies){
		Dependency jboss = POMFactory.eINSTANCE.createDependency();
		jboss.setGroupId("org.jboss.ejb3");
		jboss.setArtifactId("jboss-ejb3-ext-api");
		jboss.setVersion("1.1.1");
		jboss.setScope("provided");
		dependencies.add(jboss);
		Dependency spec = POMFactory.eINSTANCE.createDependency();
		spec.setGroupId("org.jboss.spec");
		spec.setArtifactId("jboss-javaee-6.0");
		spec.setVersion("1.0.0.Final");
		spec.setScope("provided");
		spec.setType("pom");
		dependencies.add(spec);
	}
	protected void addNumlTestAdaptor(Collection<Dependency> result){
		Dependency opaeumAdaptor = POMFactory.eINSTANCE.createDependency();
		opaeumAdaptor.setGroupId("org.opaeum");
		opaeumAdaptor.setArtifactId("opaeum-test-adaptor");
		opaeumAdaptor.setScope("test");
		opaeumAdaptor.setVersion("${opaeum.version}");
		result.add(opaeumAdaptor);
	}
	protected Collection<Dependency> getTestDepedencies(){
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
	protected Collection<Dependency> getBasicDependencies(ISourceFolderIdentifier identifier){
		Collection<Dependency> result = getTestDepedencies();
		if(getExampleTargetDir().isOneProjectPerWorkspace()){
		}else{
			Collection<Package> imports = this.model.getImportedPackages();
			for(Package imp:imports){
				if(EmfPackageUtil.isRootObject(imp)){
					addDependencyToRootObject(identifier, imp, result);
				}
			}
		}
		return result;
	}
	protected void addDependencyToRootObject(ISourceFolderIdentifier identifier,Package rootObject,Collection<Dependency> result){
		if(!config.getSourceFolderStrategy().isSingleProjectStrategy()){
			SourceFolderDefinition sourceFolderDefinition = config.getSourceFolderDefinition(identifier);
			String versionSuffix = shouldAppendVersionSuffix ? config.getMavenGroupVersionSuffix() : "";
			if(sourceFolderDefinition.isOneProjectPerWorkspace()){
				Dependency d = POMFactory.eINSTANCE.createDependency();
				d.setGroupId(config.getMavenGroupId());
				d.setVersion(getVersionVariable());
				d.setScope("compile");
				d.setType("jar");
				d.setArtifactId(workspace.getIdentifier() + sourceFolderDefinition.getProjectQualifier() + versionSuffix);
				result.add(d);
			}else{
				if(workspace.isPrimaryModel(rootObject)){
					Dependency d = POMFactory.eINSTANCE.createDependency();
					d.setGroupId(config.getMavenGroupId());
					d.setVersion(getVersionVariable());
					d.setScope("compile");
					d.setType("jar");
					d.setArtifactId(EmfPackageUtil.getIdentifier(rootObject) + sourceFolderDefinition.getProjectQualifier() + versionSuffix);
					result.add(d);
				}else{
					// TODO Model level stereotype, or opaeumconfig.properties get group
					// id and version artifactid=filename
				}
			}
		}
	}
	public Profile createArquillianProfile(){
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
		AnyType includes = PomUtil.addEmptyAnyElement(pluginExecution.getConfiguration().getAny(), "includes");
		PomUtil.addAnyElementWithContent(includes.getAny(), "include", "**/*IntegrationTest.java");
		plugin.getExecutions().getExecution().add(pluginExecution);
		profile.getBuild().getPlugins().getPlugin().add(plugin);
		return profile;
	}
	protected Plugin addSurefire(){
		Plugin plugin;
		plugin = POMFactory.eINSTANCE.createPlugin();
		plugin.setGroupId("org.apache.maven.plugins");
		plugin.setArtifactId("maven-surefire-plugin");
		plugin.setVersion("2.7.1");
		plugin.setConfiguration(POMFactory.eINSTANCE.createConfigurationType2());
		return plugin;
	}
	public void release(){
		this.migrationWorkspace = null;
		this.workspace = null;
	}
}
