package net.sf.nakeduml.pomgeneration;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;

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

@PhaseDependency(after = { JavaTransformationPhase.class }, before = { FileGenerationPhase.class })
public class PomGenerationPhase implements TransformationPhase<PomGenerationStep> {

	private NakedUmlConfig config;
	private Map<String, DocumentRoot> rootMap = new HashMap<String, DocumentRoot>();

	@Override
	public void initialize(NakedUmlConfig config) {
		this.config = config;
	}

	@Override
	public Object[] execute(List<PomGenerationStep> features) {
		for (PomGenerationStep step : features) {
			step.initialize(config);
			DocumentRoot root = rootMap.get(step.getTargetDir());
			if (root == null) {
				root = createRoot(step);
			}
			createProperties(step, root);
			createModules(step, root);
			createParent(step, root);
			createDependencyManagement(step, root);
			createDependencies(step, root);
			createPluginManagement(step, root);
			createPlugins(step, root);
			addFinalName(step, root);
			createProfile(step, root);
			outputToFile(root);
		}
		return new Object[0];

	}

	private void createProfile(PomGenerationStep step, DocumentRoot root) {
		Profile[] profiles;
		profiles = step.getProfiles();
		for (Profile newProfile : profiles) {
			if (isNewProfile(root, newProfile)) {
				if (root.getProject().getProfiles() == null) {
					root.getProject().setProfiles(POMFactory.eINSTANCE.createProfilesType());

				}
				root.getProject().getProfiles().getProfile().add(newProfile);
			}
		}
	}

	private boolean isNewProfile(DocumentRoot root, Profile newProfile) {
		if (root.getProject().getProfiles() == null) {
			return true;
		} else {
			for (Profile oldProfile : root.getProject().getProfiles().getProfile()) {
				if (oldProfile.getId().equals(newProfile.getId())) {
					return false;
				}
			}
			return true;
		}
	}

	private void createProperties(PomGenerationStep step, DocumentRoot root) {
		Properties props = step.getProperties();
	    @SuppressWarnings("rawtypes")
		Enumeration e = props.propertyNames();
	    while (e.hasMoreElements()) {
	      String key = (String) e.nextElement();
	      PomGenerationStep.addAnyElement(root.getProject().getProperties().getAny(), key, props.getProperty(key));
	    }
	}

	private void addFinalName(PomGenerationStep step, DocumentRoot root) {
		if (step.hasFinalName()) {
			root.getProject().getBuild().setFinalName(step.getFinalName());
		}
	}

	private void createParent(PomGenerationStep step, DocumentRoot root) {
		if (step.hasParent() && root.getProject().getParent()==null) {
			root.getProject().setParent(POMFactory.eINSTANCE.createParent());
			root.getProject().getParent().setGroupId(step.getParentGroupId());
			root.getProject().getParent().setArtifactId(step.getParentArtifactId());
			root.getProject().getParent().setVersion(step.getParentVersion());
		}
	}

	private void outputToFile(DocumentRoot root) {
		try {
			// TODO create text resource
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			root.eResource().save(baos, null);
			String s = new String(baos.toByteArray());
			s = s.replaceAll("pom:", "");
			s = s.replaceAll(":pom", "");
			root.eResource().getResourceSet().getURIConverter().createOutputStream(root.eResource().getURI()).write(s.getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void createPlugins(PomGenerationStep step, DocumentRoot root) {
		Plugin[] plugins;
		plugins = step.getPlugins();
		for (Plugin newPlugin : plugins) {
			if (isNewPlugin(root, newPlugin)) {
				if (root.getProject().getBuild() == null) {
					root.getProject().setBuild(POMFactory.eINSTANCE.createBuild());
				}
				if (root.getProject().getBuild().getPlugins() == null) {
					root.getProject().getBuild().setPlugins(POMFactory.eINSTANCE.createPluginsType2());

				}
				root.getProject().getBuild().getPlugins().getPlugin().add(newPlugin);
			}
		}
	}

	private void createPluginManagement(PomGenerationStep step, DocumentRoot root) {
		Plugin[] plugins = step.getPluginManagements();
		for (Plugin newPluginMan : plugins) {
			if (isNewPluginManagement(root, newPluginMan)) {
				if (root.getProject().getBuild() == null) {
					root.getProject().setBuild(POMFactory.eINSTANCE.createBuild());
				}
				if (root.getProject().getBuild().getPluginManagement() == null) {
					root.getProject().getBuild().setPluginManagement(POMFactory.eINSTANCE.createPluginManagement());
				}
				if (root.getProject().getBuild().getPluginManagement().getPlugins() == null) {
					root.getProject().getBuild().getPluginManagement().setPlugins(POMFactory.eINSTANCE.createPluginsType3());
				}
				root.getProject().getBuild().getPluginManagement().getPlugins().getPlugin().add(newPluginMan);
			}
		}
	}

	private void createDependencies(PomGenerationStep step, DocumentRoot root) {
		Dependency[] dependencies;
		dependencies = step.getDependencies();
		for (Dependency newDep : dependencies) {
			if (isNewDepedency(root, newDep)) {
				root.getProject().getDependencies().getDependency().add(newDep);
			}
		}
	}

	private void createDependencyManagement(PomGenerationStep step, DocumentRoot root) {
		Dependency[] dependencies = step.getDependencyManagementDependencies();
		for (Dependency newDepMan : dependencies) {
			if (isNewDepedencyManagement(root, newDepMan)) {
				root.getProject().getDependencyManagement().getDependencies().getDependency().add(newDepMan);
			}
		}
	}

	private void createModules(PomGenerationStep step, DocumentRoot root) {
		String[] modules = step.getModules();
		for (String module : modules) {
			if (isNewModule(root, module)) {
				if (root.getProject().getModules()==null) {
					root.getProject().setModules(POMFactory.eINSTANCE.createModulesType());
				}
				root.getProject().getModules().getModule().add(module);
			}
		}
	}

	private boolean isNewModule(DocumentRoot root, String module) {
		if (root.getProject().getModules() == null || root.getProject().getModules().getModule() == null) {
			return true;
		} else {
			for (String oldModule : root.getProject().getModules().getModule()) {
				if (oldModule.equals(module)) {
					return false;
				}
			}
			return true;
		}
	}

	private boolean isNewPlugin(DocumentRoot root, Plugin newPlugin) {
		if (root.getProject().getBuild() == null || root.getProject().getBuild().getPlugins() == null) {
			return true;
		} else {
			for (Plugin oldPlugin : root.getProject().getBuild().getPlugins().getPlugin()) {
				if (oldPlugin.getGroupId().equals(newPlugin.getGroupId()) && oldPlugin.getArtifactId().equals(newPlugin.getArtifactId())) {
					return false;
				}
			}
			return true;
		}
	}

	private boolean isNewPluginManagement(DocumentRoot root, Plugin newPlugin) {
		if (root.getProject().getBuild() == null || root.getProject().getBuild().getPluginManagement()==null || root.getProject().getBuild().getPluginManagement().getPlugins()==null) {
			return true;
		} else {
			for (Plugin oldPlugin : root.getProject().getBuild().getPluginManagement().getPlugins().getPlugin()) {
				if (oldPlugin.getGroupId().equals(newPlugin.getGroupId()) && oldPlugin.getArtifactId().equals(newPlugin.getArtifactId())) {
					return false;
				}
			}
			return true;
		}
	}

	public DocumentRoot createRoot(PomGenerationStep step) {
		DocumentRoot root;
		File jpaRoot = config.getMappedDestination(step.getTargetDir());
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new POMResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(POMPackage.eNS_URI, POMPackage.eINSTANCE);
		Resource r = null;
		try {
			r = resourceSet.getResource(URI.createFileURI(jpaRoot.getAbsolutePath() + "/pom.xml"), true);
		} catch (Exception e) {
		}
		if (r == null) {
			r = resourceSet.createResource(URI.createFileURI(jpaRoot.getAbsolutePath() + "/pom.xml"));
			root = POMFactory.eINSTANCE.createDocumentRoot();
			r.getContents().add(root);
			Model model = POMFactory.eINSTANCE.createModel();
			root.setProject(model);
			model.setModelVersion("4.0.0");
			model.setGroupId(step.getGroupId());
			model.setArtifactId(step.getName());
			model.setVersion("0.0.1");
			model.setPackaging(step.getPackaging());
			model.setName(step.getName());
			model.setDependencies(POMFactory.eINSTANCE.createDependenciesType());
			model.setDependencyManagement(POMFactory.eINSTANCE.createDependencyManagement());
			model.getDependencyManagement().setDependencies(POMFactory.eINSTANCE.createDependenciesType1());
			model.setProperties(POMFactory.eINSTANCE.createPropertiesType2());
		} else {
			root = (DocumentRoot) r.getContents().get(0);

		}
		this.rootMap.put(step.getTargetDir(), root);
		return root;
	}

	private boolean isNewDepedency(DocumentRoot root, Dependency newDep) {
		for (Dependency oldDep : root.getProject().getDependencies().getDependency()) {
			if (oldDep.getGroupId().equals(newDep.getGroupId()) && oldDep.getArtifactId().equals(newDep.getArtifactId())) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isNewDepedencyManagement(DocumentRoot root, Dependency newDepMan) {
		for (Dependency oldDep : root.getProject().getDependencyManagement().getDependencies().getDependency()) {
			if (oldDep.getGroupId().equals(newDepMan.getGroupId()) && oldDep.getArtifactId().equals(newDepMan.getArtifactId())) {
				return false;
			}
		}
		return true;
	}
	
}
