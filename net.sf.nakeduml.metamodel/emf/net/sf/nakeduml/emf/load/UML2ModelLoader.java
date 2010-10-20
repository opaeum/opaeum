package net.sf.nakeduml.emf.load;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

public class UML2ModelLoader {
	protected static final ResourceSet RESOURCE_SET = new ResourceSetImpl();

	protected static void registerResourceFactories() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
	}

	public static Model loadModel(String relativePath) throws Exception {
		URI model_uri = URI.createFileURI(new File(relativePath).getAbsolutePath());
		Model model = loadModel(model_uri);
		return model;
	}

	public static Model loadModel(URI model_uri) throws Exception {
		setupStandAloneAppForUML2();
		long time = System.currentTimeMillis();
		System.out.println("UML2ModelLoader.loadModel()");
		Model model = (Model) load(model_uri);
		EcoreUtil.resolveAll(model.eResource().getResourceSet());
		System.out.println("UML2ModelLoader.loadModel() took " + (System.currentTimeMillis() - time) + "seconds");
		return model;
	}

	protected static org.eclipse.uml2.uml.Package load(URI uri) {
		org.eclipse.uml2.uml.Package package_ = null;
		try {
			Resource resource = RESOURCE_SET.getResource(uri, true);
			package_ = (org.eclipse.uml2.uml.Package) EcoreUtil.getObjectByType(resource.getContents(), UMLPackage.Literals.PACKAGE);
		} catch (WrappedException we) {
			we.printStackTrace();
			System.exit(1);
		}
		return package_;
	}

	protected static void setupStandAloneAppForUML2() throws Exception {
		String uml2ResourceJar = findUml2ResourceJar();
		RESOURCE_SET.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		RESOURCE_SET.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
		Map uriMap = RESOURCE_SET.getURIConverter().getURIMap();
		URI uri = URI.createURI(uml2ResourceJar);
		uriMap.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP), uri.appendSegment("libraries").appendSegment(""));
		uriMap.put(URI.createURI(UMLResource.METAMODELS_PATHMAP), uri.appendSegment("metamodels").appendSegment(""));
		uriMap.put(URI.createURI(UMLResource.PROFILES_PATHMAP), uri.appendSegment("profiles").appendSegment(""));
	}

	public static String findUml2ResourceJar() throws Exception {
		URLClassLoader s = (URLClassLoader) Thread.currentThread().getContextClassLoader();
		String uml2Jar = findUml2ResourceJar(s);
		if(uml2Jar==null){
			uml2Jar=findUml2ResourceJar((URLClassLoader) ClassLoader.getSystemClassLoader());
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
}