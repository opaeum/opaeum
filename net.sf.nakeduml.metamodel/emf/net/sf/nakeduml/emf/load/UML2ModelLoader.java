package net.sf.nakeduml.emf.load;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.metamodel.mapping.internal.WorkspaceMappingInfoImpl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

public class UML2ModelLoader {
	protected static ResourceSet RESOURCE_SET;

	protected static void registerResourceFactories() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
	}

	public static EmfWorkspace loadDirectory(File entryModel) throws Exception {
		String extension=entryModel.getName().substring(entryModel.getName().lastIndexOf("."));
		System.out.println("UML2ModelLoader.loadDirectory()");
		long time = System.currentTimeMillis();
		ResourceSet resourceSet = setupStandAloneAppForUML2();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(extension, UMLResource.Factory.INSTANCE);
		File[] files = entryModel.getParentFile().listFiles();
		Package result = null;
		for (File file : files) {
			if (file.getName().endsWith(extension)) {
				Package pkg = load(resourceSet, URI.createFileURI(file.getAbsolutePath()));
				if (file.equals(entryModel)) {
					result = pkg;
				}
			}
		}
		EcoreUtil.resolveAll(resourceSet);
		System.out.println("UML2ModelLoader.loadDirectory() took " + (System.currentTimeMillis() - time) + " ms");
		WorkspaceMappingInfoImpl mappingInfo = new WorkspaceMappingInfoImpl(getInputStream(result, "mappinginfo"));
		EmfWorkspace emfWorkspace = new EmfWorkspace(result, mappingInfo);
		emfWorkspace.guessGeneratingModelsAndProfiles();
		return emfWorkspace;
	}

	public static Model loadModel(String relativePath) throws Exception {
		URI model_uri = URI.createFileURI(new File(relativePath).getAbsolutePath());
		Model model = loadModel(model_uri);
		return model;
	}

	public static Model loadModel(URI model_uri) throws Exception {
		long time = System.currentTimeMillis();
		System.out.println("UML2ModelLoader.loadModel()");
		Model model = (Model) load(getResourceSetSingleton(), model_uri);
		EcoreUtil.resolveAll(model.eResource().getResourceSet());
		System.out.println("UML2ModelLoader.loadModel() took " + (System.currentTimeMillis() - time) + "ms");
		return model;
	}

	private static ResourceSet getResourceSetSingleton() throws Exception {
		if (RESOURCE_SET == null) {
			RESOURCE_SET = setupStandAloneAppForUML2();
		}
		return RESOURCE_SET;
	}

	protected static org.eclipse.uml2.uml.Package load(ResourceSet RESOURCE_SET, URI uri) {
		Package package_ = null;
		try {
			Resource resource = RESOURCE_SET.getResource(uri, true);
			package_ = (Package) EcoreUtil.getObjectByType(resource.getContents(), UMLPackage.Literals.PACKAGE);
		} catch (WrappedException we) {
			we.printStackTrace();
			System.exit(1);
		}
		return package_;
	}

	protected static ResourceSet setupStandAloneAppForUML2() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		String uml2ResourceJar = findUml2ResourceJar();
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
		Map uriMap = resourceSet.getURIConverter().getURIMap();
		URI uri = URI.createURI(uml2ResourceJar);
		uriMap.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP), uri.appendSegment("libraries").appendSegment(""));
		uriMap.put(URI.createURI(UMLResource.METAMODELS_PATHMAP), uri.appendSegment("metamodels").appendSegment(""));
		uriMap.put(URI.createURI(UMLResource.PROFILES_PATHMAP), uri.appendSegment("profiles").appendSegment(""));
		return resourceSet;
	}

	public static String findUml2ResourceJar() throws Exception {
		URLClassLoader s = (URLClassLoader) Thread.currentThread().getContextClassLoader();
		String uml2Jar = findUml2ResourceJar(s);
		if (uml2Jar == null) {
			uml2Jar = findUml2ResourceJar((URLClassLoader) ClassLoader.getSystemClassLoader());
		}
		return uml2Jar;
	}

	public static String findUml2ResourceJar(URLClassLoader s) {
		URL[] urls = s.getURLs();
		String UML2JAR = null;
		for (URL url : urls) {
			if (url.toExternalForm().contains("org/nakeduml/metamodel") || url.toExternalForm().contains("org.nakeduml.metamodel")
					|| url.toString().contains("org/eclipse/uml2/uml/resources")
					|| url.toString().contains("org.eclipse.uml2.uml.resources")) {
				System.out.println(url.getFile());
				File file = new File(url.getFile());
				UML2JAR = "jar:file:///" + file.getAbsolutePath().replace('\\', '/') + "!/";
				break;
			}
		}
		if (UML2JAR == null && s.getParent() instanceof URLClassLoader) {
			return findUml2ResourceJar((URLClassLoader) s.getParent());
		} else {
			return UML2JAR;
		}
	}

	public static InputStreamReader getInputStream(Package model, String extension) {
		try {
			URIConverter uriConverter = model.eResource().getResourceSet().getURIConverter();
			URI uri = model.eResource().getURI().trimFileExtension().appendFileExtension(extension);
			if (uriConverter.exists(uri, null)) {
				InputStream inStream = uriConverter.createInputStream(uri);
				return new InputStreamReader(inStream);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
}