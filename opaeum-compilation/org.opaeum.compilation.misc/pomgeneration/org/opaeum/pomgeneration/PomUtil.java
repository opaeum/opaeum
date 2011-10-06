package org.opaeum.pomgeneration;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.maven.pom.Build;
import org.apache.maven.pom.ConfigurationType3;
import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.DocumentRoot;
import org.apache.maven.pom.Exclusion;
import org.apache.maven.pom.Model;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;
import org.apache.maven.pom.PluginExecution;
import org.apache.maven.pom.PluginsType2;
import org.apache.maven.pom.PropertiesType2;
import org.apache.maven.pom.Repository;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.impl.AnyTypeImpl;
import org.opaeum.textmetamodel.SourceFolder;

public class PomUtil{
	private static Set<String> builtInFolders = new HashSet<String>();
	static{
		builtInFolders.add("src/main/java");
		builtInFolders.add("src/main/resources");
		builtInFolders.add("src/main/webapp");
		builtInFolders.add("src/test/java");
		builtInFolders.add("src/test/resources");
	}
	public static AnyType addEmptyAnyElement(FeatureMap any,String elementName){
		EStructuralFeature sourceFeature = ExtendedMetaData.INSTANCE.demandFeature(null, elementName, true);
		AnyType node = XMLTypeFactory.eINSTANCE.createAnyType();
		any.add(sourceFeature, node);
		return node;
	}
	public static void addAnyAttribute(EObject any,String elementName,String content){
		EStructuralFeature attributeFeature = ExtendedMetaData.INSTANCE.demandFeature(null, elementName, false);
		any.eSet(attributeFeature, content);
	}
	public static EStructuralFeature addAnyElementWithContent(FeatureMap any,String elementName,String content){
		EStructuralFeature sourceFeature = ExtendedMetaData.INSTANCE.demandFeature(null, elementName, true);
		AnyType node = XMLTypeFactory.eINSTANCE.createAnyType();
		node.getMixed().add(FeatureMapUtil.createTextEntry(content));
		any.add(sourceFeature, node);
		return sourceFeature;
	}
	public static void setAnyElementContent(FeatureMap any,String key,String property){
		for(FeatureMap.Entry entry:any){
			if(entry.getEStructuralFeature().getName().equals(key)){
				AnyTypeImpl anyValue = (AnyTypeImpl) entry.getValue();
				anyValue.getMixed().set(0, FeatureMapUtil.createTextEntry(property));
			}
		}
	}
	public static boolean containsAnyElement(FeatureMap any,String elementName){
		for(FeatureMap.Entry entry:any){
			if(entry.getEStructuralFeature().getName().equals(elementName)){
				return true;
			}
		}
		return false;
	}
	public static boolean isNewPluginManagementPlugin(Plugin newPlugin,DocumentRoot projectPom){
		if(projectPom.getProject().getBuild() == null || projectPom.getProject().getBuild().getPluginManagement() == null
				|| projectPom.getProject().getBuild().getPluginManagement().getPlugins() == null){
			return true;
		}else{
			EList<Plugin> plugin = projectPom.getProject().getBuild().getPluginManagement().getPlugins().getPlugin();
			return PomUtil.isNewPlugin(newPlugin, plugin);
		}
	}
	public static boolean isNewPlugin(DocumentRoot root,Plugin newPlugin){
		if(root.getProject().getBuild() == null || root.getProject().getBuild().getPlugins() == null){
			return true;
		}else{
			return isNewPlugin(newPlugin, root.getProject().getBuild().getPlugins().getPlugin());
		}
	}
	private static boolean isNewPlugin(Plugin newPlugin,EList<Plugin> plugin){
		for(Plugin oldPlugin:plugin){
			if(oldPlugin.getGroupId().equals(newPlugin.getGroupId()) && oldPlugin.getArtifactId().equals(newPlugin.getArtifactId())){
				return false;
			}
		}
		return true;
	}
	public static void populatePropertiesFrom(Model project,Properties props){
		if(project.getProperties() == null){
			project.setProperties(POMFactory.eINSTANCE.createPropertiesType2());
		}
		PropertiesType2 mvnProperties = project.getProperties();
		@SuppressWarnings("rawtypes")
		Enumeration e = props.propertyNames();
		while(e.hasMoreElements()){
			String key = (String) e.nextElement();
			if(!containsAnyElement(mvnProperties.getAny(), key)){
				addAnyElementWithContent(mvnProperties.getAny(), key, props.getProperty(key));
			}else{
				setAnyElementContent(mvnProperties.getAny(), key, props.getProperty(key));
			}
		}
	}
	public static boolean isBuiltInFolder(String relativePath){
		return false;// Bug in m2Eclipse
		// return builtInFolders.contains(relativePath);
	}
	private static AnyType findOrCreateAnyType(FeatureMap featuresOnParent,String elementName){
		for(FeatureMap.Entry entry:featuresOnParent){
			if(entry.getEStructuralFeature().getName().equals(elementName)){
				return (AnyType) entry.getValue();
			}
		}
		return addEmptyAnyElement(featuresOnParent, elementName);
	}
	private static ConfigurationType3 findConfigurationForExecution(Plugin plugin,String phase,String goalName){
		if(plugin.getExecutions() == null){
			plugin.setExecutions(POMFactory.eINSTANCE.createExecutionsType());
		}
		EList<PluginExecution> executions = plugin.getExecutions().getExecution();
		PluginExecution executionForGoal = null;
		for(PluginExecution execution:executions){
			if(execution.getPhase().equals(phase) && execution.getGoals() != null && execution.getGoals().getGoal().size() == 1
					&& execution.getGoals().getGoal().get(0).equals(goalName)){
				executionForGoal = execution;
				break;
			}
		}
		if(executionForGoal == null){
			executionForGoal = POMFactory.eINSTANCE.createPluginExecution();
			executionForGoal.setId(goalName);
			executionForGoal.setGoals(POMFactory.eINSTANCE.createGoalsType1());
			executionForGoal.getGoals().getGoal().add(goalName);
			executionForGoal.setPhase(phase);
			plugin.getExecutions().getExecution().add(executionForGoal);
		}
		if(executionForGoal.getConfiguration() == null){
			executionForGoal.setConfiguration(POMFactory.eINSTANCE.createConfigurationType3());
		}
		return executionForGoal.getConfiguration();
	}
	private static Plugin findOrCreatePlugin(DocumentRoot pom,String artifactId,String groupId){
		Plugin result = null;
		Model project = pom.getProject();
		Build build = project.getBuild();
		if(build == null){
			build = POMFactory.eINSTANCE.createBuild();
			project.setBuild(build);
		}
		PluginsType2 plugins2 = build.getPlugins();
		if(plugins2 == null){
			plugins2 = POMFactory.eINSTANCE.createPluginsType2();
			build.setPlugins(plugins2);
		}
		EList<Plugin> plugins = plugins2.getPlugin();
		for(Plugin plugin:plugins){
			if(plugin.getArtifactId().equals(artifactId)){
				result = plugin;
				break;
			}
		}
		if(result == null){
			result = POMFactory.eINSTANCE.createPlugin();
			result.setGroupId(groupId);
			result.setArtifactId(artifactId);
			addPlugin(pom, result);
		}
		return result;
	}
	static boolean isNewModule(DocumentRoot root,String module){
		if(root.getProject().getModules() == null || root.getProject().getModules().getModule() == null){
			return true;
		}else{
			for(String oldModule:root.getProject().getModules().getModule()){
				if(oldModule.equals(module)){
					return false;
				}
			}
			return true;
		}
	}
	public static void createModuleIfNew(DocumentRoot root,String module){
		if(isNewModule(root, module)){
			if(root.getProject().getModules() == null){
				root.getProject().setModules(POMFactory.eINSTANCE.createModulesType());
			}
			root.getProject().getModules().getModule().add(module);
		}
	}
	public static void maybeAddJavaPath(DocumentRoot root,SourceFolder sf){
		Plugin buildHelper = findOrCreatePlugin(root, "build-helper-maven-plugin", "org.codehaus.mojo");
		ConfigurationType3 config;
		if(sf.getRelativePath().contains("test")){
			BuildHelperGoal goal = BuildHelperGoal.ADD_TEST_SOURCE;
			config = findConfigurationForExecution(buildHelper, goal.getPhase(), goal.getGoal());
		}else{
			BuildHelperGoal goal = BuildHelperGoal.ADD_SOURCE;
			config = findConfigurationForExecution(buildHelper, goal.getPhase(), goal.getGoal());
		}
		AnyType fm = findOrCreateAnyType(config.getAny(), "sources");
		boolean hasPathEntry = false;
		for(FeatureMap.Entry entry:fm.getAny()){
			if(entry.getEStructuralFeature().getName().equals("source")){
				AnyType any = (AnyType) entry.getValue();
				if(any.getMixed().get(0).getValue().equals(sf.getRelativePath())){
					hasPathEntry = true;
				}
			}
		}
		if(!hasPathEntry){
			addAnyElementWithContent(fm.getAny(), "source", sf.getRelativePath());
		}
	}
	public static void maybeAddResourcePath(DocumentRoot root,SourceFolder sf){
		Plugin buildHelper = findOrCreatePlugin(root, "build-helper-maven-plugin", "org.codehaus.mojo");
		ConfigurationType3 config;
		if(sf.getRelativePath().contains("test")){
			BuildHelperGoal goal = BuildHelperGoal.ADD_TEST_RESOURCE;
			config = findConfigurationForExecution(buildHelper, goal.getPhase(), goal.getGoal());
		}else{
			BuildHelperGoal goal = BuildHelperGoal.ADD_RESOURCE;
			config = findConfigurationForExecution(buildHelper, goal.getPhase(), goal.getGoal());
		}
		AnyType fm = findOrCreateAnyType(config.getAny(), "resources");
		boolean hasPathEntry = false;
		outer:for(FeatureMap.Entry entry:fm.getAny()){
			if(entry.getEStructuralFeature().getName().equals("resource")){
				AnyType resource = (AnyType) entry.getValue();
				for(FeatureMap.Entry fe:resource.getAny()){
					if(fe.getEStructuralFeature().getName().equals("directory")){
						AnyType directory = (AnyType) fe.getValue();
						if(directory.getMixed().get(0).getValue().equals(sf.getRelativePath())){
							hasPathEntry = true;
							break outer;
						}
					}
				}
			}
			if(entry.getValue() instanceof AnyType){
				((AnyType) entry.getValue()).getAny();
			}
			if(entry.getValue().equals(sf.getRelativePath())){
				hasPathEntry = true;
			}
		}
		if(!hasPathEntry){
			AnyType resource = addEmptyAnyElement(fm.getAny(), "resource");
			addAnyElementWithContent(resource.getAny(), "directory", sf.getRelativePath());
		}
	}
	public static void addPlugin(DocumentRoot childPom,Plugin newPlugin){
		if(childPom.getProject().getBuild() == null){
			childPom.getProject().setBuild(POMFactory.eINSTANCE.createBuild());
		}
		if(childPom.getProject().getBuild().getPlugins() == null){
			childPom.getProject().getBuild().setPlugins(POMFactory.eINSTANCE.createPluginsType2());
		}
		childPom.getProject().getBuild().getPlugins().getPlugin().add(newPlugin);
	}
	public static Dependency getNewDependency(DocumentRoot root,Dependency newDep){
		for(Dependency oldDep:root.getProject().getDependencies().getDependency()){
			if(oldDep.getGroupId().equals(newDep.getGroupId()) && oldDep.getArtifactId().equals(newDep.getArtifactId())){
				return oldDep;
			}
		}
		return null;
	}
	public static Dependency getExisitingDependencyInDepedencyManagement(DocumentRoot root,Dependency newDepMan){
		if(root.getProject().getDependencyManagement() == null || root.getProject().getDependencyManagement().getDependencies() == null){
			return null;
		}
		for(Dependency oldDep:root.getProject().getDependencyManagement().getDependencies().getDependency()){
			if(oldDep.getGroupId().equals(newDepMan.getGroupId()) && oldDep.getArtifactId().equals(newDepMan.getArtifactId())){
				return oldDep;
			}
		}
		return null;
	}
	static void addPluginManagementPlugin(Plugin parentPlugin,DocumentRoot localPom){
		if(localPom.getProject().getBuild() == null){
			localPom.getProject().setBuild(POMFactory.eINSTANCE.createBuild());
		}
		if(localPom.getProject().getBuild().getPluginManagement() == null){
			localPom.getProject().getBuild().setPluginManagement(POMFactory.eINSTANCE.createPluginManagement());
		}
		if(localPom.getProject().getBuild().getPluginManagement().getPlugins() == null){
			localPom.getProject().getBuild().getPluginManagement().setPlugins(POMFactory.eINSTANCE.createPluginsType3());
		}
		localPom.getProject().getBuild().getPluginManagement().getPlugins().getPlugin().add(parentPlugin);
	}
	public static void addRepositoryIfNew(DocumentRoot localPom,String repoId,String repoUrl,String repoName){
		if(localPom.getProject().getRepositories() == null){
			localPom.getProject().setRepositories(POMFactory.eINSTANCE.createRepositoriesType());
		}
		Repository repo = POMFactory.eINSTANCE.createRepository();
		repo.setId(repoId);
		repo.setName(repoName);
		repo.setUrl(repoUrl);
		EList<Repository> repos = localPom.getProject().getRepositories().getRepository();
		boolean found = false;
		for(Repository repository:repos){
			if(repository.getUrl().equals(repo.getUrl())){
				found = true;
				break;
			}
		}
		if(!found){
			repos.add(repo);
		}
	}
	public static void addNewExclusionsOnly(Dependency oldDep,EList<Exclusion> newExclusions){
		if(newExclusions.size() > 0){
			if(oldDep.getExclusions() == null){
				oldDep.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
			}
			//Clone collection because ELists remove their children from their previous container
			for(Exclusion newExclusion: new ArrayList<Exclusion>(newExclusions)){
				if(!isExclusionAlreadyPresent(oldDep, newExclusion)){
					oldDep.getExclusions().getExclusion().add(newExclusion);
				}
			}
		}
	}
	private static boolean isExclusionAlreadyPresent(Dependency oldDep,Exclusion newExclusion){
		boolean found = false;
		for(Exclusion oldExclusion:oldDep.getExclusions().getExclusion()){
			if(oldExclusion.getGroupId().equals(newExclusion.getGroupId()) && oldExclusion.getArtifactId().equals(newExclusion.getArtifactId())){
				found = true;
				break;
			}
		}
		return found;
	}
}
