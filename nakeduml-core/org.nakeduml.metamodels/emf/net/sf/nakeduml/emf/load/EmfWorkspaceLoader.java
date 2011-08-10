package net.sf.nakeduml.emf.load;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.common.util.EList;
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

public class EmfWorkspaceLoader{
	protected static ResourceSet RESOURCE_SET;
	protected static void registerResourceFactories(){
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
	}
	public static EmfWorkspace loadDirectory(ResourceSet resourceSet,File dir,NakedUmlConfig cfg){
		System.out.println("UML2ModelLoader.loadDirectory()");
		String ext = UMLResource.FILE_EXTENSION;
		long time = System.currentTimeMillis();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(ext, UMLResource.Factory.INSTANCE);
		File[] files = dir.listFiles();
		URI dirUri = findDirUri(resourceSet, dir, ext);
		for(File file:files){
			if(file.getName().endsWith(ext)){
				load(resourceSet, dirUri.appendSegment(file.getName()));
			}
		}
		EcoreUtil.resolveAll(resourceSet);
		System.out.println("UML2ModelLoader.loadDirectory() took " + (System.currentTimeMillis() - time) + " ms");
		EmfWorkspace emfWorkspace = new EmfWorkspace(dirUri, resourceSet, cfg.getWorkspaceMappingInfo(), cfg.getWorkspaceIdentifier());
		emfWorkspace.guessGeneratingModelsAndProfiles(dirUri);
		return emfWorkspace;
	}
	private static URI findDirUri(ResourceSet resourceSet,File dir,String extension){
		URI dirUri = null;
		EList<Resource> resources = resourceSet.getResources();
		for(Resource r:resources){
			URI ruir = r.getURI();
			URI potentialDirUri = ruir.trimFileExtension().trimSegments(1);
			String lastSegment = potentialDirUri.lastSegment();
			if(ruir.fileExtension().equals(extension) && lastSegment != null && lastSegment.equals(dir.getName())){
				dirUri = potentialDirUri;
			}
		}
		if(dirUri == null){
			dirUri = URI.createFileURI(dir.getAbsolutePath());
		}
		return dirUri;
	}
	public static EmfWorkspace loadSingleModelWorkspace(ResourceSet resourceSet,File modelFile,NakedUmlConfig cfg) throws Exception{
		String ext = modelFile.getName().substring(modelFile.getName().lastIndexOf(".") + 1);
		File dir = modelFile.getParentFile();
		URI dirUri = findDirUri(resourceSet, dir, ext);
		Model model = loadModel(resourceSet, dirUri.appendSegment(modelFile.getName()));
		EmfWorkspace result = new EmfWorkspace(model, cfg.getWorkspaceMappingInfo(), cfg.getWorkspaceIdentifier());
		return result;
	}
	@Deprecated
	public static Model loadModel(URI model_uri) throws Exception{
		ResourceSet resourceSetSingleton = getResourceSetSingleton();
		return loadModel(resourceSetSingleton, model_uri);
	}
	private static Model loadModel(ResourceSet resourceSetSingleton,URI model_uri){
		long time = System.currentTimeMillis();
		System.out.println("UML2ModelLoader.loadModel()");
		Model model = (Model) load(resourceSetSingleton, model_uri);
		EcoreUtil.resolveAll(model.eResource().getResourceSet());
		System.out.println("UML2ModelLoader.loadModel() took " + (System.currentTimeMillis() - time) + "ms");
		return model;
	}
	@Deprecated
	private static ResourceSet getResourceSetSingleton() throws Exception{
		if(RESOURCE_SET == null){
			RESOURCE_SET = setupStandAloneAppForUML2();
		}
		return RESOURCE_SET;
	}
	protected static org.eclipse.uml2.uml.Package load(ResourceSet RESOURCE_SET,URI uri){
		Package package_ = null;
		try{
			Resource resource = RESOURCE_SET.getResource(uri, true);
			package_ = (Package) EcoreUtil.getObjectByType(resource.getContents(), UMLPackage.Literals.PACKAGE);
		}catch(WrappedException we){
			we.printStackTrace();
			System.exit(1);
		}
		return package_;
	}
	public static ResourceSet setupStandAloneAppForUML2(){
		ResourceSet resourceSet = new ResourceSetImpl();
		if(!Thread.currentThread().getContextClassLoader().getClass().getName().equals("org.eclipse.core.runtime.internal.adaptor.ContextFinder")){
			resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
			Map uriMap = resourceSet.getURIConverter().getURIMap();
			URLClassLoader loader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
			URI uri = URI.createURI(findJar(loader, "nakeduml-metamodels", "org/eclipse/uml2/uml/resources", "org.eclipse.uml2.uml.resources"));
			uriMap.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP), uri.appendSegment("libraries").appendSegment(""));
			uriMap.put(URI.createURI(UMLResource.METAMODELS_PATHMAP), uri.appendSegment("metamodels").appendSegment(""));
			uriMap.put(URI.createURI(UMLResource.PROFILES_PATHMAP), uri.appendSegment("profiles").appendSegment(""));
			String jar = findJar(loader, "nakeduml-metamodels");
			if(jar != null){
				// Maven jar found
				uri = URI.createURI(jar);
			}else{
				// eclipse jar
				jar = findJar(loader, "org.nakeduml.metamodels_");
				uri = URI.createURI(jar);
				uri=uri.appendSegment("models");
			}
			uriMap.put(URI.createURI(StereotypeNames.MODELS_PATHMAP), uri.appendSegment(""));
//			URI umlProfile = uri.appendSegment("profiles").appendSegment("NakedUMLProfile.uml");
		}
		return resourceSet;
	}
	@Deprecated
	public static String findUml2ResourceJar(URLClassLoader s){
		return findJar(s, "nakeduml-metamodels", "org/eclipse/uml2/uml/resources", "org.eclipse.uml2.uml.resources");
	}
	public static String findJar(URLClassLoader s,String...names){
		URL[] urls = s.getURLs();
		String UML2JAR = null;
		outer:for(URL url:urls){
			for(String string:names){
				String ext = url.toExternalForm();
				if(ext.contains(string) && ext.endsWith(".jar")){
					File file = new File(url.getFile());
					System.out.println(url.getFile());
					UML2JAR = "jar:file:///" + file.getAbsolutePath().replace('\\', '/') + "!/";
					break outer;
				}
			}
		}
		if(UML2JAR == null && s.getParent() instanceof URLClassLoader){
			UML2JAR = findJar((URLClassLoader) s.getParent(), names);
		}
		if(UML2JAR == null){
			UML2JAR = findUml2ResourceJar((URLClassLoader) ClassLoader.getSystemClassLoader());
		}
		return UML2JAR;
	}
}