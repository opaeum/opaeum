package org.opaeum.pomgeneration;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.maven.pom.ConfigurationType2;
import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.DocumentRoot;
import org.apache.maven.pom.Model;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.POMPackage;
import org.apache.maven.pom.Plugin;
import org.apache.maven.pom.Profile;
import org.apache.maven.pom.util.POMResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.opaeum.bootstrap.BootstrapGenerationPhase;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.emf.extraction.AbstractEmfPhase;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.InputModel;
import org.opaeum.feature.IntegrationPhase;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.PhaseDependency;
import org.opaeum.feature.StrategyCalculator;
import org.opaeum.feature.TransformationContext;
import org.opaeum.feature.TransformationPhase;
import org.opaeum.feature.TransformationProcess.TransformationProgressLog;
import org.opaeum.filegeneration.FileGenerationPhase;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.migration.MigrationGenerationPhase;
import org.opaeum.metamodel.workspace.MigrationWorkspace;
import org.opaeum.textmetamodel.SourceFolder;
import org.opaeum.textmetamodel.TextProject;
import org.opaeum.textmetamodel.TextWorkspace;

@PhaseDependency(after = {
		JavaTransformationPhase.class,BootstrapGenerationPhase.class,MigrationGenerationPhase.class
},before = {
	FileGenerationPhase.class
})
public class PomGenerationPhase extends AbstractEmfPhase implements TransformationPhase<PomGenerationStep,Element>,IntegrationPhase{
	@InputModel
	private EmfWorkspace workspace;
	@InputModel
	private TextWorkspace textWorkspace;
	@InputModel(optional = true)
	private MigrationWorkspace migrationWorkspace;
	private OpaeumConfig config;
	private Map<String,DocumentRoot> rootMap = new HashMap<String,DocumentRoot>();
	private DocumentRoot parentPom;
	private SortedSet<String> ignores = new TreeSet<String>();
	private Collection<PomGenerationStep> features;
	private boolean isGeneratingRelease;
	public static final String NUML_VERSION = "1.0.0-SNAPSHOT";
	@Override
	public void initialize(OpaeumConfig config,List<PomGenerationStep> features){
		this.features = features;
		this.config = config;
		if(config.generateMavenPoms()){
			parentPom = buildPomRoot(config.getOutputRoot(), config.getApplicationIdentifier(), "pom", true);
			readIgnoreFile(config);
			Plugin compiler = POMFactory.eINSTANCE.createPlugin();
			compiler.setGroupId("org.apache.maven.plugins");
			compiler.setArtifactId("maven-compiler-plugin");
			compiler.setVersion("2.3.2");
			ConfigurationType2 cfg = POMFactory.eINSTANCE.createConfigurationType2();
			PomUtil.addAnyElementWithContent(cfg.getAny(), "source", "1.6");
			PomUtil.addAnyElementWithContent(cfg.getAny(), "target", "1.6");
			compiler.setConfiguration(cfg);
			addPluginManagementPluginIfNew(compiler);
			Plugin buildHelper = POMFactory.eINSTANCE.createPlugin();
			buildHelper.setGroupId("org.codehaus.mojo");
			buildHelper.setArtifactId("build-helper-maven-plugin");
			buildHelper.setVersion("1.5");
			addPluginManagementPluginIfNew(buildHelper);
			Plugin eclipse = POMFactory.eINSTANCE.createPlugin();
			eclipse.setGroupId("org.apache.maven.plugins");
			eclipse.setArtifactId("maven-eclipse-plugin");
			eclipse.setVersion("2.8");
			PomUtil.addAnyElementWithContent(cfg.getAny(), "downloadSources", "true");
			PomUtil.addAnyElementWithContent(cfg.getAny(), "downloadJavadocs", "true");
			PomUtil.addAnyElementWithContent(cfg.getAny(), "nakedWorkspace", "${basedir}");
			PomUtil.addAnyElementWithContent(cfg.getAny(), "projectNameTemplate", "[artifactId]");
			addPluginManagementPluginIfNew(eclipse);
			PomUtil.addRepositoryIfNew(parentPom, "repository.jboss.org", "https://repository.jboss.org/nexus/content/groups/public", "JBoss Repository");
			PomUtil.addRepositoryIfNew(parentPom, "java.net", "http://download.java.net/maven/2/", "java net");
		}
	}
	private void readIgnoreFile(OpaeumConfig config){
		try{
			File ignoreFile = getIgnoreFile(config);
			if(ignoreFile.exists()){
				BufferedReader ignoreReader = new BufferedReader(new FileReader(ignoreFile));
				String line = null;
				while((line = ignoreReader.readLine()) != null){
					ignores.add(line);
				}
				ignoreReader.close();
				
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	private File getIgnoreFile(OpaeumConfig config){
		File ignoreFile = new File(config.getOutputRoot(), "." + config.getScmTool() + "ignore");
		return ignoreFile;
	}
	@Override
	public void execute(TransformationContext context){
		if(config.generateMavenPoms()){
			for(PomGenerationStep step:features){
				if(step.isIntegrationStep() == context.isIntegrationPhase() && !context.getLog().isCanceled()){
					step.initialize(config, workspace, migrationWorkspace);
					if(step.getExampleTargetDir().isOneProjectPerWorkspace()){
						String prefix = workspace.getIdentifier();
						DocumentRoot root = getRoot(step, prefix);
						updatePom(step, root);
					}else{
						Collection<Package> models = workspace.getGeneratingModelsOrProfiles();
						for(Package model:models){
							step.setModel(model);
							DocumentRoot root = getRoot(step, EmfPackageUtil.getIdentifier(model));
							updatePom(step, root);
						}
					}
				}
			}
			for(DocumentRoot documentRoot:this.rootMap.values()){
				if(documentRoot.getProject().getArtifactId().equals(this.parentPom.getProject().getArtifactId())){
					this.parentPom.getProject().setArtifactId(this.parentPom.getProject().getArtifactId() + "-parent");
					break;
				}
			}
			for(DocumentRoot documentRoot:this.rootMap.values()){
				documentRoot.getProject().getParent().setArtifactId(this.parentPom.getProject().getArtifactId());
				outputToFile(documentRoot);
			}
			Set<String> list = new HashSet<String>(Arrays.asList(config.getOutputRoot().list()));
			for(String m:new ArrayList<String>(parentPom.getProject().getModules().getModule())){
				if(!list.contains(m)){
					parentPom.getProject().getModules().getModule().remove(m);
				}
			}
			outputToFile(parentPom);
			saveIgnoreFile();
		}
	}
	private void saveIgnoreFile(){
		PrintWriter fw;
		try{
			fw = new PrintWriter(getIgnoreFile(config));
			for(String s:ignores){
				fw.println(s);
			}
			fw.flush();
			fw.close();
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	private DocumentRoot getRoot(PomGenerationStep step,String prefix){
		DocumentRoot root = rootMap.get(step.getProjectName());
		if(root == null){
			File projectRoot = new File(config.getOutputRoot(), step.getProjectName());
			root = buildPomRoot(projectRoot, step.getProjectName(), step.getPackaging(), false);
			this.rootMap.put(step.getProjectName(), root);
		}
		return root;
	}
	private DocumentRoot buildPomRoot(File projectRoot,String projectName,String packaging,boolean load){
		DocumentRoot root;
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new POMResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(POMPackage.eNS_URI, POMPackage.eINSTANCE);
		Resource r = null;
		if(load){
			try{
				r = resourceSet.getResource(URI.createFileURI(projectRoot.getAbsolutePath() + "/pom.xml"), true);
			}catch(Exception e){
			}
		}
		if(r == null){
			// Remember the poms are generated before the files /directories are generated
			if(!projectRoot.exists()){
				projectRoot.mkdirs();
			}
			r = resourceSet.createResource(URI.createFileURI(projectRoot.getAbsolutePath() + "/pom.xml"));
			root = POMFactory.eINSTANCE.createDocumentRoot();
			r.getContents().add(root);
			Model model = POMFactory.eINSTANCE.createModel();
			root.setProject(model);
			model.setModelVersion("4.0.0");
			model.setGroupId(config.getMavenGroupId());
			model.setArtifactId(projectName);
			model.setVersion(getMavenVersion());
			model.setPackaging(packaging);
			model.setName(projectName);
			model.setDependencies(POMFactory.eINSTANCE.createDependenciesType());
			model.setDependencyManagement(POMFactory.eINSTANCE.createDependencyManagement());
			model.getDependencyManagement().setDependencies(POMFactory.eINSTANCE.createDependenciesType1());
			model.setProperties(POMFactory.eINSTANCE.createPropertiesType2());
		}else{
			root = (DocumentRoot) r.getContents().get(0);
		}
		return root;
	}
	private void updatePom(PomGenerationStep step,DocumentRoot root){
		TextProject tp = textWorkspace.findTextProject(step.getProjectName());
		if(tp != null){
			ignores.add(tp.getName() + "/.project");
			ignores.add(tp.getName() + "/.settings");
			ignores.add(tp.getName() + "/.classpath");
			ignores.add(tp.getName() + "/test-output");
			ignores.add(tp.getName() + "/target");
			ignores.add(tp.getName() + "/pom.xml");
			PomUtil.populatePropertiesFrom(parentPom.getProject(), step.getParentPomProperties());
			PomUtil.populatePropertiesFrom(root.getProject(), step.getProperties());
			PomUtil.createModuleIfNew(parentPom, step.getProjectName());
			Properties props = new Properties();
			props.put(workspace.getIdentifier() + ".version", getMavenVersion());
			PomUtil.populatePropertiesFrom(parentPom.getProject(), props);
			createParent(step, root);
			createDependencies(step, root);
			createPlugins(step, root);
			createProfile(step, root);
			updateSourceFolders(root, tp);
		}
	}
	private void updateSourceFolders(DocumentRoot root,TextProject tp){
		Collection<SourceFolder> sourceFolders = tp.getSourceFolders();
		for(SourceFolder sf:sourceFolders){
			if(!PomUtil.isBuiltInFolder(sf.getRelativePath())){
				if(sf.getRelativePath().contains("resources")){
					PomUtil.maybeAddResourcePath(root, sf);
				}else if(sf.getRelativePath().contains("java")){
					PomUtil.maybeAddJavaPath(root, sf);
				}else if(sf.getRelativePath().contains("webapp")){
					PomUtil.maybeAddResourcePath(root, sf);
				}
				if(sf.getRelativePath().contains("generated")){
					this.ignores.add(tp.getName() + "/" + sf.getRelativePath());
				}
			}
		}
	}
	private void createProfile(PomGenerationStep step,DocumentRoot root){
		Profile[] profiles;
		profiles = step.getProfiles();
		for(Profile newProfile:profiles){
			if(isNewProfile(root, newProfile)){
				if(root.getProject().getProfiles() == null){
					root.getProject().setProfiles(POMFactory.eINSTANCE.createProfilesType());
				}
				root.getProject().getProfiles().getProfile().add(newProfile);
			}
		}
	}
	private boolean isNewProfile(DocumentRoot root,Profile newProfile){
		if(root.getProject().getProfiles() == null){
			return true;
		}else{
			for(Profile oldProfile:root.getProject().getProfiles().getProfile()){
				if(oldProfile.getId().equals(newProfile.getId())){
					return false;
				}
			}
			return true;
		}
	}
	private void createParent(PomGenerationStep step,DocumentRoot root){
		if(root.getProject().getParent() == null){
			root.getProject().setParent(POMFactory.eINSTANCE.createParent());
		}
		if(parentPom != null && parentPom.getProject() != null){
			root.getProject().setParent(POMFactory.eINSTANCE.createParent());
			root.getProject().getParent().setGroupId(parentPom.getProject().getGroupId());
			root.getProject().getParent().setArtifactId(parentPom.getProject().getArtifactId());
			root.getProject().getParent().setVersion(parentPom.getProject().getVersion());
		}else{
			root.getProject().setParent(POMFactory.eINSTANCE.createParent());
			root.getProject().getParent().setGroupId(config.getMavenGroupId());
			root.getProject().getParent().setArtifactId(config.getApplicationIdentifier());
			root.getProject().getParent().setVersion(getMavenVersion());
		}
	}
	private String getMavenVersion(){
		return config.getVersion().toVersionString() + (isGeneratingRelease() ? "" : "-SNAPSHOT");
	}
	protected boolean isGeneratingRelease(){
		return this.isGeneratingRelease;
	}
	public void outputToFile(DocumentRoot root){
		try{
			File pomFile = new File(root.eResource().getURI().toFileString());
			if(pomFile.exists()){
				pomFile.delete();
			}
			Map<Object,Object> options = new HashMap<Object,Object>();
			options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
			options.put(XMLResource.OPTION_SAVE_ONLY_IF_CHANGED, Boolean.FALSE);
			options.put(XMLResource.SCHEMA_LOCATION, "http://maven.apache.org/xsd/maven-4.0.0.xsd");
			// TODO create text resource
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			root.eResource().save(baos, options);
			String s = new String(baos.toByteArray());
			s = s.replaceAll("pom:", "");
			s = s.replaceAll(":pom", "");
			root.eResource().getResourceSet().getURIConverter().createOutputStream(root.eResource().getURI()).write(s.getBytes());
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	private void createPlugins(PomGenerationStep step,DocumentRoot childPom){
		Plugin[] plugins = step.getPlugins();
		for(Plugin newPlugin:plugins){
			registerPluginInParentPom(newPlugin);
			if(PomUtil.isNewPlugin(childPom, newPlugin)){
				PomUtil.addPlugin(childPom, newPlugin);
			}
		}
	}
	private void registerPluginInParentPom(Plugin newPlugin){
		Plugin parentPlugin = POMFactory.eINSTANCE.createPlugin();
		parentPlugin.setGroupId(newPlugin.getGroupId());
		parentPlugin.setArtifactId(newPlugin.getArtifactId());
		parentPlugin.setVersion(newPlugin.getVersion());
		newPlugin.setVersion(null);
		addPluginManagementPluginIfNew(parentPlugin);
	}
	private void addPluginManagementPluginIfNew(Plugin parentPlugin){
		if(PomUtil.isNewPluginManagementPlugin(parentPlugin, parentPom)){
			PomUtil.addPluginManagementPlugin(parentPlugin, parentPom);
		}
	}
	private void createDependencies(PomGenerationStep step,DocumentRoot root){
		Dependency[] dependencies;
		dependencies = step.getDependencies();
		for(Dependency newDep:dependencies){
			Dependency childDependency = null;
			childDependency = PomUtil.getNewDependency(root, newDep);
			if(childDependency == null){
				childDependency = POMFactory.eINSTANCE.createDependency();
				childDependency.setArtifactId(newDep.getArtifactId());
				childDependency.setGroupId(newDep.getGroupId());
				if("pom".equals(newDep.getType())){
					childDependency.setType("pom");
					childDependency.setVersion(newDep.getVersion());
				}
				childDependency.setClassifier(newDep.getClassifier());
				root.getProject().getDependencies().getDependency().add(childDependency);
			}
			Dependency oldParentDependency = PomUtil.getExisitingDependencyInDepedencyManagement(parentPom, newDep);
			if(oldParentDependency == null){
				addNewDependencyToParentPom(newDep);
			}else{
				mergeNewDependency(newDep, childDependency, oldParentDependency);
			}
		}
	}
	private void mergeNewDependency(Dependency newDep,Dependency childDependency,Dependency oldParentDependency){
		if(oldParentDependency.getScope() == null){
			childDependency.setScope(newDep.getScope());
		}else if(!oldParentDependency.getScope().equals(newDep.getScope())){
			// Override scope in the child pom
			childDependency.setScope(newDep.getScope());
		}
		oldParentDependency.setVersion(newDep.getVersion());
		if(newDep.getExclusions() != null){
			PomUtil.addNewExclusionsOnly(oldParentDependency, newDep.getExclusions().getExclusion());
		}
	}
	private void addNewDependencyToParentPom(Dependency newDep){
		if(parentPom.getProject().getDependencyManagement() == null){
			parentPom.getProject().setDependencyManagement(POMFactory.eINSTANCE.createDependencyManagement());
		}
		if(parentPom.getProject().getDependencyManagement().getDependencies() == null){
			parentPom.getProject().getDependencyManagement().setDependencies(POMFactory.eINSTANCE.createDependenciesType1());
		}
		parentPom.getProject().getDependencyManagement().getDependencies().getDependency().add(newDep);
	}
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<Element> elements){
		return elements;
	}
	@Override
	public Collection<PomGenerationStep> getSteps(){
		return features;
	}
	public DocumentRoot getParentPom(){
		return parentPom;
	}
	public void initializeSteps(){
	}
	public void generateVersionedPoms(TransformationProgressLog log){
		this.isGeneratingRelease = true;
		appendVersionSuffix(true);
		Set<Class<? extends ITransformationStep>> emptySet = Collections.emptySet();
		initialize(config, (List<PomGenerationStep>) features);
		execute(new TransformationContext(emptySet, false, log,new StrategyCalculator(emptySet)));
		execute(new TransformationContext(emptySet, true, log,new StrategyCalculator(emptySet)));
		appendVersionSuffix(false);
		this.isGeneratingRelease = false;
	}
	private void appendVersionSuffix(boolean b){
		for(PomGenerationStep f:features){
			f.appendVersionSuffix(b);
		}
	}
	@Override
	public void release(){
		this.migrationWorkspace = null;
		this.textWorkspace = null;
		this.workspace = null;
		for(PomGenerationStep p:this.features){
			p.release();
		}
	}
	@Override
	protected EmfWorkspace getModelWorkspace(){
		return workspace;
	}
}
