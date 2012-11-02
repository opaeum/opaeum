package org.opaeum.eclipse.context;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.EclipseUriToFileConverter;
import org.opaeum.eclipse.OclUpdater;
import org.opaeum.eclipse.OpaeumConfigDialog;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.OpaeumErrorMarker;
import org.opaeum.eclipse.newchild.IOpaeumResourceSet;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.emf.workspace.UriToFileConverter;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.runtime.domain.IntrospectionUtil;

public class OpaeumEclipseContext{
	private static Map<IContainer,OpaeumEclipseContext> contexts = new WeakHashMap<IContainer,OpaeumEclipseContext>();
	private Map<IFile,OpenUmlFile> openUmlFiles = new HashMap<IFile,OpenUmlFile>();
	private boolean isOpen = false;
	private IContainer umlDirectory;
	protected UriToFileConverter resourceHelper = new EclipseUriToFileConverter();
	private OpaeumErrorMarker errorMarker;
	private boolean isLoading;
	private boolean newlyCreated;
	private EmfWorkspace dew;
	private OpaeumConfig config;
	public OpaeumEclipseContext(OpaeumConfig cfg,IContainer umlDirectory,boolean newlyCreated){
		super();
		this.config = cfg;
		isOpen = true;
		this.newlyCreated = newlyCreated;
		this.umlDirectory = umlDirectory;
		if(cfg.getErrorMarker() == null){
			this.errorMarker = new OpaeumErrorMarker();
		}else{
			this.errorMarker = (OpaeumErrorMarker) IntrospectionUtil.newInstance(cfg.getErrorMarker());
		}
		reinitialize();
	}
	public boolean isNewlyCreated(){
		return newlyCreated;
	}
	public void reinitialize(){
		if(config.hasOutputProjectOverride()){
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IProject project = root.getProject(config.getProjectNameOverride());
			if(project.exists()){
				config.setOutputRoot(project.getLocation().toFile().getParentFile());
			}else{
				config.calculateOutputRoot(umlDirectory.getProject().getLocation().toFile());
			}
		}else{
			config.calculateOutputRoot(umlDirectory.getProject().getLocation().toFile());
		}
		config.getSourceFolderStrategy().defineSourceFolders(config);
		this.dew = null;
		ArrayList<OpenUmlFile> arrayList = new ArrayList<OpenUmlFile>(openUmlFiles.values());
		this.openUmlFiles.clear();
		for(OpenUmlFile editingContext:arrayList){
			editingContext.dispose();
			startSynch(editingContext.getEditingDomain(), editingContext.getFile(), editingContext.geteObjectSelectorUI());
		}
	}
	public String getId(Element umlElement){
		return EmfWorkspace.getId(umlElement);
	}
	public Collection<EmfWorkspace> getEmfWorkspaces(){
		Collection<EmfWorkspace> result = new HashSet<EmfWorkspace>();
		for(OpenUmlFile editingContext:this.openUmlFiles.values()){
			result.add(editingContext.getEmfWorkspace());
		}
		return result;
	}
	public boolean startSynch(final EditingDomain domain,final IFile file,EObjectSelectorUI selector){
		OpenUmlFile openUmlFile = new OpenUmlFile(domain, file, config);
		openUmlFile.seteObjectSelectorUI(selector);
		openUmlFile.addSynchronizationListener(new OclUpdater());
		openUmlFile.addSynchronizationListener(errorMarker);
		openUmlFiles.put(file, openUmlFile);
		errorMarker.maybeSchedule(openUmlFile);
		return true;
	}
	public boolean isOpen(){
		return isOpen;
	}
	public void onSave(IProgressMonitor monitor,IFile f){
		try{
			OpenUmlFile openUmlFile = this.openUmlFiles.get(f);
			monitor.beginTask("Saving UML Models", 100);
			if(openUmlFile != null){
				openUmlFile.setDirty(false);
				openUmlFile.onSave(monitor);
				dew = null;
			}
			getUmlDirectory().refreshLocal(1, null);
		}catch(CoreException e){
			OpaeumEclipsePlugin.logError("OnSave Failed", e);
		}finally{
			monitor.done();
		}
	}
	public void onClose(IFile umlFile){
		OpenUmlFile openUmlFile = this.openUmlFiles.get(umlFile);
		this.openUmlFiles.remove(umlFile);
		if(openUmlFile != null){
			openUmlFile.release();
		}
	}
	public IContainer getUmlDirectory(){
		return umlDirectory;
	}
	public EmfWorkspace loadDirectory(IProgressMonitor monitor){
		this.isLoading = true;
		try{
			monitor.beginTask("Loading EMF resources", 300);
			ResourceSet rst;
			rst = new ResourceSetImpl();
			URI uri = URI.createPlatformResourceURI(getUmlDirectory().getFullPath().toString(), true);
//			if(dew == null){
				dew = new EmfWorkspace(uri, rst, getConfig().getVersion(), getConfig().getWorkspaceIdentifier(), getConfig().getMavenGroupId());
				dew.setUriToFileConverter(new EclipseUriToFileConverter());
				dew.setName(getConfig().getWorkspaceName());
				for(IResource r:umlDirectory.members()){
					monitor.subTask("Loading " + r.getName());
					if(r instanceof IFile && r.getFileExtension().equals("uml")){
						final Resource resource = dew.getResourceSet().getResource(
								URI.createPlatformResourceURI(((IFile) r).getFullPath().toString(), true), true);
						EcoreUtil.resolveAll(resource);
					}
					monitor.worked(100 / umlDirectory.members().length);
				}
//			}
			dew.guessGeneratingModelsAndProfiles(URI.createPlatformResourceURI(umlDirectory.getFullPath().toString(), true));
			return dew;
		}catch(CoreException e){
			throw new RuntimeException(e);
		}finally{
			this.isLoading = false;
			monitor.done();
		}
	}
	public boolean isOpen(IFile f){
		for(OpenUmlFile openUmlFile:openUmlFiles.values()){
			for(Resource resource:openUmlFile.getEmfWorkspace().getResourceSet().getResources()){
				if(resource.getURI() != null && resource.getURI().lastSegment().equals(f.getLocation().lastSegment())){
					return true;
				}
			}
		}
		return false;
	}
	public boolean isSyncronizingWith(IFile file){
		return openUmlFiles.containsKey(file);
	}
	public boolean getAutoSync(){
		return this.getConfig().synchronizeAutomatically();
	}
	public boolean isLoading(){
		return this.isLoading;
	}
	public OpaeumConfig getConfig(){
		return this.config;
	}
	public static OpaeumEclipseContext findOrCreateContextFor(IContainer umlDir){
		OpaeumEclipseContext result = getContextFor(umlDir);
		if(result == null){
			OpaeumConfig cfg = null;
			final IFile propsFile = (IFile) umlDir.findMember("opaeum.properties");
			boolean newContext = propsFile == null;
			if(newContext){
				cfg = new OpaeumConfig(new File(umlDir.getLocation().toFile(), "opaeum.properties"));
				OpaeumConfigDialog dlg = new OpaeumConfigDialog(Display.getDefault().getActiveShell(), cfg, umlDir);
				final int dlgResult = dlg.open();
				if(dlgResult != Window.OK){
					return null;
				}
				try{
					umlDir.refreshLocal(IResource.DEPTH_INFINITE, null);
				}catch(CoreException e){
					e.printStackTrace();
				}
			}else{
				// Load classes
				OpaeumEclipsePlugin.getDefault();
				cfg = new OpaeumConfig(propsFile.getLocation().toFile());
			}
			final OpaeumEclipseContext newOne = new OpaeumEclipseContext(cfg, umlDir, newContext);
			contexts.put(umlDir, newOne);
			result = newOne;
		}
		return result;
	}
	public OpenUmlFile getOpenUmlFileFor(IFile f){
		return this.openUmlFiles.get(f);
	}
	public static OpaeumEclipseContext getContextFor(IContainer umlDir){
		OpaeumEclipseContext result = contexts.get(umlDir);
		return result;
	}
	public static OpaeumEclipseContext getContextFor(EObject element){
		Resource eResource = element.eResource();
		if(eResource == null){
			return null;
		}else if(eResource.getResourceSet() instanceof IOpaeumResourceSet){
			IOpaeumResourceSet rst = (IOpaeumResourceSet) eResource.getResourceSet();
			return findOrCreateContextFor(rst.getModelDirectory());
		}else{
			EObject rootContainer = EcoreUtil.getRootContainer(element);
			String platformString = rootContainer.eResource().getURI().toPlatformString(true);
			if(platformString == null){
				ResourceSet resourceSet = rootContainer.eResource().getResourceSet();
				EList<Resource> resources = resourceSet.getResources();
				for(Resource resource:resources){
					platformString = resource.getURI().toPlatformString(true);
					if(platformString != null){
						break;
					}
				}
			}
			return getContextFor(ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformString)).getParent());
		}
	}
	public OpaeumErrorMarker getErrorMarker(){
		return errorMarker;
	}
	public OpenUmlFile getEditingContextFor(EObject eObject){
		if(eObject.eResource() != null){
			if(eObject.eResource().getResourceSet() instanceof IOpaeumResourceSet){
				IOpaeumResourceSet rst = (IOpaeumResourceSet) eObject.eResource().getResourceSet();
				return getOpenUmlFileFor(rst.getPrimaryFile());
			}
			Collection<OpenUmlFile> values = this.openUmlFiles.values();
			for(OpenUmlFile openUmlFile:values){
				if(openUmlFile.getEmfWorkspace().getResourceSet() == eObject.eResource().getResourceSet()){
					return openUmlFile;
				}
			}
		}
		return null;
	}
	public Collection<OpenUmlFile> getOpenUmlFiles(){
		return this.openUmlFiles.values();
	}
	public static OpenUmlFile findOpenUmlFileFor(EObject element){
		OpaeumEclipseContext ctx = getContextFor(element);
		if(ctx != null){
			return ctx.getEditingContextFor(element);
		}else{
			return null;
		}
	}
	public static OpenUmlFile findOpenUmlFileFor(IFile iFile){
		OpaeumEclipseContext ctx = getContextFor(iFile.getParent());
		if(ctx == null){
			return null;
		}
		return ctx.getOpenUmlFileFor(iFile);
	}
	public static Collection<EObject> getReachableObjectsOfType(EObject element,EClass type){
		return findOpenUmlFileFor(element).getTypeCacheAdapter().getReachableObjectsOfType(element, type);
	}
	public static Collection<Element> getReachableObjectsOfStereotype(EObject element,String profile,String type){
		return findOpenUmlFileFor(element).getTypeCacheAdapter().getReachableObjectsOfStereotype(element, profile, type);
	}
	public static Collection<OpenUmlFile> getAllOpenUmlFiles(){
		Set<OpenUmlFile> result = new HashSet<OpenUmlFile>();
		for(OpaeumEclipseContext c:contexts.values()){
			result.addAll(c.getOpenUmlFiles());
		}
		return result;
	}
}
