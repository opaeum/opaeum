package net.sf.nakeduml.pomgeneration;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			DocumentRoot root = rootMap.get(step.getTargetDir());
			if (root == null) {
				root = createRoot(step);
			}
			Dependency[] dependencies = step.getDependencies();
			for (Dependency newDep : dependencies) {
				if (isNewDepedency(root, newDep)) {
					root.getProject().getDependencies().getDependency().add(newDep);
				}
			}
			Plugin[] plugins = step.getPlugins();
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
		return new Object[0];

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
			// TODO parameterize
			model.setGroupId("za.co.companyname");
			model.setModelVersion("4.0.0");
			model.setVersion("0.0.1");
			model.setArtifactId(this.config.getName() + step.getArtifactSuffix());
			model.setName(this.config.getName() + step.getArtifactSuffix());
			model.setDependencies(POMFactory.eINSTANCE.createDependenciesType());
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
}
