package net.sf.nakeduml.emf.load;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.metamodel.mapping.internal.WorkspaceMappingInfoImpl;

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
	public static EmfWorkspace loadDirectory(File dir,String workspaceName,String extension) throws Exception{
		System.out.println("UML2ModelLoader.loadDirectory()");
		long time = System.currentTimeMillis();
		getResourceSetSingleton().getResourceFactoryRegistry().getExtensionToFactoryMap().put(extension, UMLResource.Factory.INSTANCE);
		File[] files = dir.listFiles();
		for(File file:files){
			if(file.getName().endsWith(extension)){
				load(getResourceSetSingleton(), URI.createFileURI(file.getAbsolutePath()));
			}
		}
		EcoreUtil.resolveAll(getResourceSetSingleton());
		System.out.println("UML2ModelLoader.loadDirectory() took " + (System.currentTimeMillis() - time) + " ms");
		WorkspaceMappingInfoImpl mappingInfo = getMappingInfo(dir, workspaceName);
		EmfWorkspace emfWorkspace = new EmfWorkspace(dir, getResourceSetSingleton(), mappingInfo, workspaceName);
		emfWorkspace.guessGeneratingModelsAndProfiles(dir);
		return emfWorkspace;
	}
	public static EmfWorkspace loadSingleModelWorkspace(URI model_uri,String workspaceName) throws Exception{
		Model model = loadModel(model_uri);
		File dir = new File(model_uri.toFileString()).getParentFile();
		EmfWorkspace result = new EmfWorkspace(model, getMappingInfo(dir, workspaceName), workspaceName);
		return result;
	}
	public static Model loadModel(URI model_uri) throws Exception{
		long time = System.currentTimeMillis();
		System.out.println("UML2ModelLoader.loadModel()");
		Model model = (Model) load(getResourceSetSingleton(), model_uri);
		EcoreUtil.resolveAll(model.eResource().getResourceSet());
		System.out.println("UML2ModelLoader.loadModel() took " + (System.currentTimeMillis() - time) + "ms");
		return model;
	}
	private static WorkspaceMappingInfoImpl getMappingInfo(File dir,String workspaceName){
		return new WorkspaceMappingInfoImpl(new File(dir, workspaceName + ".mappinginfo"));
	}
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
	protected static ResourceSet setupStandAloneAppForUML2() throws Exception{
		ResourceSet resourceSet = new ResourceSetImpl();
		if(!Thread.currentThread().getContextClassLoader().getClass().getName().equals("org.eclipse.core.runtime.internal.adaptor.ContextFinder")){
			String uml2ResourceJar = findUml2ResourceJar();
			resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
			Map uriMap = resourceSet.getURIConverter().getURIMap();
			URI uri = URI.createURI(uml2ResourceJar);
			uriMap.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP), uri.appendSegment("libraries").appendSegment(""));
			uriMap.put(URI.createURI(UMLResource.METAMODELS_PATHMAP), uri.appendSegment("metamodels").appendSegment(""));
			uriMap.put(URI.createURI(UMLResource.PROFILES_PATHMAP), uri.appendSegment("profiles").appendSegment(""));
		}
		return resourceSet;
	}
	public static String findUml2ResourceJar() throws Exception{
		URLClassLoader s = (URLClassLoader) Thread.currentThread().getContextClassLoader();
		String uml2Jar = findUml2ResourceJar(s);
		if(uml2Jar == null){
			uml2Jar = findUml2ResourceJar((URLClassLoader) ClassLoader.getSystemClassLoader());
		}
		return uml2Jar;
	}
	public static String findUml2ResourceJar(URLClassLoader s){
		URL[] urls = s.getURLs();
		String UML2JAR = null;
		for(URL url:urls){
			if(url.toExternalForm().contains("nakeduml-metamodels") || url.toString().contains("org/eclipse/uml2/uml/resources")
					|| url.toString().contains("org.eclipse.uml2.uml.resources")){
				File file = new File(url.getFile());
				System.out.println(url.getFile());
				UML2JAR = "jar:file:///" + file.getAbsolutePath().replace('\\', '/') + "!/";
				break;
			}
		}
		if(UML2JAR == null && s.getParent() instanceof URLClassLoader){
			return findUml2ResourceJar((URLClassLoader) s.getParent());
		}else{
			return UML2JAR;
		}
	}
}