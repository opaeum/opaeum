package net.sf.nakeduml.pomgeneration;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedSet;
import java.util.TreeSet;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.jbpm5.FlowGenerationPhase;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.SourceFolder;
import net.sf.nakeduml.textmetamodel.TextProject;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

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
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.nakeduml.bootstrap.DefaultConfigGenerationPhase;

@PhaseDependency(after = {JavaTransformationPhase.class,FlowGenerationPhase.class,DefaultConfigGenerationPhase.class},before = {FileGenerationPhase.class})
public class PomGenerationPhase implements TransformationPhase<PomGenerationStep>{
	@InputModel
	private INakedModelWorkspace workspace;
	@InputModel
	private TextWorkspace textWorkspace;
	private NakedUmlConfig config;
	private Map<String,DocumentRoot> rootMap = new HashMap<String,DocumentRoot>();
	DocumentRoot parentPom;
	private SortedSet<String> ignores = new TreeSet<String>();
	public static final String NUML_VERSION = "1.0.0.5-SNAPSHOT";
	@Override
	public void initialize(NakedUmlConfig config){
		this.config = config;
		if(config.generateMavenPoms()){
			parentPom = buildPomRoot(config.getOutputRoot(), workspace.getDirectoryName(), "pom", true);
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
			PomUtil.addAnyElementWithContent(cfg.getAny(), "workspace", "${basedir}");
			PomUtil.addAnyElementWithContent(cfg.getAny(), "projectNameTemplate", "[artifactId]");
			addPluginManagementPluginIfNew(eclipse);
			PomUtil.addRepositoryIfNew(parentPom, "repository.jboss.org", "https://repository.jboss.org/nexus/content/groups/public", "JBoss Repository");
			PomUtil.addRepositoryIfNew(parentPom, "java.net", "http://download.java.net/maven/2/", "java net");
		}
	}
	private void readIgnoreFile(NakedUmlConfig config){
		try{
			File ignoreFile = getIgnoreFile(config);
			if(ignoreFile.exists()){
				BufferedReader ignoreReader = new BufferedReader(new FileReader(ignoreFile));
				String line = null;
				while((line = ignoreReader.readLine()) != null){
					ignores.add(line);
				}
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	private File getIgnoreFile(NakedUmlConfig config){
		File ignoreFile = new File(config.getOutputRoot(), "." + config.getScmTool() + "ignore");
		return ignoreFile;
	}
	@Override
	public Object[] execute(List<PomGenerationStep> features,TransformationContext context){
		if(config.generateMavenPoms()){
			for(PomGenerationStep step:features){
				step.initialize(config, workspace);
				if(step.getExampleTargetDir().useWorkspaceName()){
					String prefix = workspace.getDirectoryName();
					DocumentRoot root = getRoot(step, prefix);
					updatePom(step, root);
				}else{
					List<INakedRootObject> models = workspace.getGeneratingModelsOrProfiles();
					for(INakedRootObject model:models){
						step.setModel(model);
						DocumentRoot root = getRoot(step, model.getFileName());
						updatePom(step, root);
					}
				}
			}
		}
		for(DocumentRoot documentRoot:this.rootMap.values()){
			outputToFile(documentRoot);
		}
		outputToFile(parentPom);
		saveIgnoreFile();
		return new Object[0];
	}
	private void saveIgnoreFile(){
		PrintWriter fw;
		try{
			fw = new PrintWriter(getIgnoreFile(config));
			for(String s:ignores){
				fw.println(s);
			}
			fw.flush();
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	private DocumentRoot getRoot(PomGenerationStep step,String prefix){
		String projectName = prefix + step.getExampleTargetDir().getProjectSuffix();
		DocumentRoot root = rootMap.get(projectName);
		if(root == null){
			File projectRoot = new File(config.getOutputRoot(), projectName);
			root = buildPomRoot(projectRoot, step.getProjectName(), step.getPackaging(), false);
			this.rootMap.put(projectName, root);
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
			r = resourceSet.createResource(URI.createFileURI(projectRoot.getAbsolutePath() + "/pom.xml"));
			root = POMFactory.eINSTANCE.createDocumentRoot();
			r.getContents().add(root);
			Model model = POMFactory.eINSTANCE.createModel();
			root.setProject(model);
			model.setModelVersion("4.0.0");
			model.setGroupId(config.getMavenGroupId());
			model.setArtifactId(projectName);
			model.setVersion(config.getMavenGroupVersion());
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
			props.put(workspace.getDirectoryName() + ".version", config.getMavenGroupVersion());
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
		root.getProject().setParent(POMFactory.eINSTANCE.createParent());
		root.getProject().getParent().setGroupId(config.getMavenGroupId());
		root.getProject().getParent().setArtifactId(workspace.getDirectoryName());
		root.getProject().getParent().setVersion(config.getMavenGroupVersion());
	}
	private void outputToFile(DocumentRoot root){
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
			if(childDependency == null){
				childDependency = PomUtil.getNewDepedency(root, newDep);
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
			Dependency oldDep = PomUtil.getExisitingDependencyInDepedencyManagement(parentPom, newDep);
			if(oldDep == null){
				if(parentPom.getProject().getDependencyManagement()==null){
					parentPom.getProject().setDependencyManagement(POMFactory.eINSTANCE.createDependencyManagement());
				}
				if(parentPom.getProject().getDependencyManagement().getDependencies()==null){
					parentPom.getProject().getDependencyManagement().setDependencies(POMFactory.eINSTANCE.createDependenciesType1());
				}
				parentPom.getProject().getDependencyManagement().getDependencies().getDependency().add(newDep);
			}else{
				if(oldDep.getScope() == null){
					childDependency.setScope(newDep.getScope());
				}else if(!oldDep.getScope().equals(newDep.getScope())){
					childDependency.setScope(newDep.getScope());
				}
				oldDep.setVersion(newDep.getVersion());
			}
		}
	}
}
