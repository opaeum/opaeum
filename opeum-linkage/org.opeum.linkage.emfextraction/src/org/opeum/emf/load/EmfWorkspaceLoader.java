package org.opeum.emf.load;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
import org.opeum.emf.workspace.EmfWorkspace;
import org.opeum.feature.DefaultTransformationLog;
import org.opeum.feature.OpeumConfig;
import org.opeum.feature.TransformationProcess.TransformationProgressLog;
import org.opeum.feature.WorkspaceMappingInfo;
import org.opeum.metamodel.core.internal.StereotypeNames;

@SuppressWarnings("unchecked")
public class EmfWorkspaceLoader{
	protected static ResourceSet RESOURCE_SET;
	protected static void registerResourceFactories(){
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
	}
	public static EmfWorkspace loadDirectory(ResourceSet resourceSet,File dir,OpeumConfig cfg){
		return loadDirectory(resourceSet, dir, cfg, new DefaultTransformationLog());
	}
	public static EmfWorkspace loadDirectory(final ResourceSet resourceSet,File dir,OpeumConfig cfg,TransformationProgressLog log){
		File[] files = dir.listFiles();
		log.startTask("Loading Emf Resources", files.length + 2);
		String ext = UMLResource.FILE_EXTENSION;
		long time = System.currentTimeMillis();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(ext, UMLResource.Factory.INSTANCE);
		final URI dirUri = findDirUri(resourceSet, dir, ext);
		for(final File file:files){
			log.startStep("Loading " + file.getName());
			if(file.getName().endsWith(ext)){
				load(resourceSet, dirUri.appendSegment(file.getName()));
			}
			log.endLastStep();
		}
		log.startStep("Resolving EMF Proxies");
		ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(4);
		for(final Resource r:new ArrayList<Resource>(resourceSet.getResources())){
			if(r.getURI() != null && r.getURI().fileExtension().equals("uml")){
				// exec.schedule(new Runnable(){
				// @Override
				// public void run(){
				EcoreUtil.resolveAll(r);
				// }
				// }, 1, TimeUnit.MILLISECONDS);
			}
		}
		try{
			exec.shutdown();
			exec.awaitTermination(100, TimeUnit.SECONDS);
		}catch(InterruptedException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.endLastStep();
		System.out.println("UML2ModelLoader.loadDirectory() took " + (System.currentTimeMillis() - time) + " ms");
		log.startStep("Loading Opium Mapping Information");
		WorkspaceMappingInfo workspaceMappingInfo = cfg.getWorkspaceMappingInfo();
		log.endLastStep();
		EmfWorkspace result = new EmfWorkspace(dirUri, resourceSet, workspaceMappingInfo, cfg.getWorkspaceIdentifier());
		result.guessGeneratingModelsAndProfiles(dirUri);
		if(cfg.getWorkspaceName() != null){
			result.setName(cfg.getWorkspaceName());
		}else{
			result.setName(cfg.getWorkspaceIdentifier());
		}
		log.endLastTask();
		return result;
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
	public static EmfWorkspace loadSingleModelWorkspace(ResourceSet resourceSet,File modelFile,OpeumConfig cfg) throws Exception{
		String ext = modelFile.getName().substring(modelFile.getName().lastIndexOf(".") + 1);
		File dir = modelFile.getParentFile();
		URI dirUri = findDirUri(resourceSet, dir, ext);
		Model model = loadModel(resourceSet, dirUri.appendSegment(modelFile.getName()));
		EmfWorkspace result = new EmfWorkspace(model, cfg.getWorkspaceMappingInfo(), cfg.getWorkspaceIdentifier());
		if(cfg.getWorkspaceName() != null){
			result.setName(cfg.getWorkspaceName());
		}else{
			result.setName(cfg.getWorkspaceIdentifier());
		}
		return result;
	}
	@Deprecated
	public static Model loadModel(URI model_uri) throws Exception{
		ResourceSet resourceSetSingleton = getResourceSetSingleton();
		return loadModel(resourceSetSingleton, model_uri);
	}
	public static Model loadModel(ResourceSet resourceSetSingleton,URI model_uri){
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
		}
		return package_;
	}
	public static ResourceSet setupStandAloneAppForUML2(){
		ResourceSet resourceSet = new ResourceSetImpl();
		if(!Thread.currentThread().getContextClassLoader().getClass().getName().equals("org.eclipse.core.runtime.internal.adaptor.ContextFinder")){
			resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
			@SuppressWarnings("rawtypes")
			Map uriMap = resourceSet.getURIConverter().getURIMap();
			URLClassLoader loader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
			URI uri = URI.createURI(findLocation(loader, true, "org/eclipse/uml2/uml/resources"/*Maven jar*/, "org.eclipse.uml2.uml.resources"/*Eclipse jar*/));
			uriMap.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP), uri.appendSegment("libraries").appendSegment(""));
			uriMap.put(URI.createURI(UMLResource.METAMODELS_PATHMAP), uri.appendSegment("metamodels").appendSegment(""));
			uriMap.put(URI.createURI(UMLResource.PROFILES_PATHMAP), uri.appendSegment("profiles").appendSegment(""));
			String jar = findLocation(loader, false, "org.opeum.transformation.engine/target/classes");//find the project directory in workspace
			if(jar == null){
				jar = findLocation(loader, true, "org/opeum/org.opeum.transformation.engine");//Find maven jar next
				if(jar == null){
					// eclipse jar
					jar = findLocation(loader, true, "org.opeum.transformation.engine_");//Find eclipse jar next
					uri = URI.createURI(jar);
					uri = uri.appendSegment("models");
				}else{
					// Maven jar found
					uri = URI.createURI(jar);
				}
			}else{
				// Workspace dir found
				uri = URI.createURI(jar);
			}
			uriMap.put(URI.createURI(StereotypeNames.MODELS_PATHMAP), uri.appendSegment(""));
			resourceSet.getResource(URI.createURI("pathmap://NAKEDUML_MODELS/profiles/OpiumStandardProfile.uml"), true).getContents().get(0);
		}
		return resourceSet;
	}
	public static String findLocation(URLClassLoader s,boolean jar,String...names){
		try{
			URL[] urls = s.getURLs();
			String location = null;
			outer:for(URL url:urls){
				for(String string:names){
					String ext = url.toExternalForm();
					if(ext.contains(string)){
						File file = new File(url.getFile());
						if(ext.endsWith(".jar")){
							if(jar){
								System.out.println(ext);
								location = "jar:file:///" + file.getAbsolutePath().replace('\\', '/') + "!/";
							}
						}else{
							if(!jar){
								System.out.println(ext);
								location = "file:///" + file.getAbsolutePath().replace('\\', '/');
							}
						}
						break outer;
					}
				}
			}
			if(location == null && s.getParent() instanceof URLClassLoader && s.getParent() != s){
				location = findLocation((URLClassLoader) s.getParent(), jar, names);
			}
//			if(location == null && s != (URLClassLoader) ClassLoader.getSystemClassLoader()){
//				location = findLocation((URLClassLoader) ClassLoader.getSystemClassLoader(), jar, names);
//			}
			return location;
		}catch(Throwable t){
			System.out.println(t.toString());
			return null;
		}
	}
}