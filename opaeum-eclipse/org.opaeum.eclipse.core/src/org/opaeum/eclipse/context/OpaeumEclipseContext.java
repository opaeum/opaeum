package org.opaeum.eclipse.context;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.EclipseUriToFileConverter;
import org.opaeum.eclipse.EmfToNakedUmlSynchronizer;
import org.opaeum.eclipse.OpaeumConfigDialog;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.NakedUmlErrorMarker;
import org.opaeum.eclipse.OclUpdater;
import org.opaeum.eclipse.ProgressMonitorTransformationLog;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.emf.workspace.UriToFileConverter;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;

public class OpaeumEclipseContext{
	private static Map<IContainer,OpaeumEclipseContext> contexts = new WeakHashMap<IContainer,OpaeumEclipseContext>();
	private static OpaeumEclipseContext currentContext;
	private Set<ResourceSet> resourceSetsStartedButNotLoaded = new HashSet<ResourceSet>();
	private Map<ResourceSet,NakedUmlEditingContext> emfWorkspaces = new HashMap<ResourceSet,NakedUmlEditingContext>();
	private EmfToNakedUmlSynchronizer umlElementCache;
	private boolean isOpen = false;
	private List<NakedUmlContextListener> listeners = new ArrayList<NakedUmlContextListener>();
	private IContainer umlDirectory;
	private ResourceSet currentResourceSet;
	protected UriToFileConverter resourceHelper = new EclipseUriToFileConverter();
	private NakedUmlErrorMarker errorMarker;
	private boolean autoSync;
	private boolean isLoading;
	private boolean newlyCreated;
	public OpaeumEclipseContext(OpaeumConfig cfg,IContainer umlDirectory,boolean newlyCreated){
		super();
		isOpen = true;
		this.newlyCreated = newlyCreated;
		umlElementCache = new EmfToNakedUmlSynchronizer(cfg);
		umlElementCache.addSynchronizationListener(new OclUpdater(this.emfWorkspaces));
		this.umlDirectory = umlDirectory;
		this.errorMarker = new NakedUmlErrorMarker(this);
		umlElementCache.addSynchronizationListener(errorMarker);
	}
	public boolean isNewlyCreated(){
		return newlyCreated;
	}
	public void reinitialize(){
		umlElementCache.reinitializeProcess();
		ArrayList<NakedUmlEditingContext> arrayList = new ArrayList<NakedUmlEditingContext>(emfWorkspaces.values());
		this.emfWorkspaces.clear();
		for(NakedUmlEditingContext editingContext:arrayList){
			editingContext.getEditingDomain().getResourceSet().eAdapters().remove(getEmfToOpaeumSynchronizer());
		}
		for(NakedUmlEditingContext editingContext:arrayList){
			startSynch(editingContext.getEditingDomain(), editingContext.getFile());
		}
		errorMarker.maybeSchedule();
	}
	public String getId(Element umlElement){
		return getEmfToOpaeumSynchronizer().getCurrentEmfWorkspace().getId(umlElement);
	}
	public Collection<EmfWorkspace> getEmfWorkspaces(){
		Collection<EmfWorkspace> result = new HashSet<EmfWorkspace>();
		for(NakedUmlEditingContext editingContext:this.emfWorkspaces.values()){
			result.add(editingContext.getEmfWorkspace());
		}
		return result;
	}
	// TODO declare private and delegate from context
	public EmfToNakedUmlSynchronizer getEmfToOpaeumSynchronizer(){
		return umlElementCache;
	}
	public boolean startSynch(final EditingDomain domain,final IFile file){
		resourceSetsStartedButNotLoaded.add(domain.getResourceSet());
		new Job("Loading Opaeum Metadata"){
			@Override
			protected IStatus run(final IProgressMonitor monitor){
				isLoading = true;
				monitor.beginTask("Loading Opaeum  Metadata", 1000);
				try{
					final Package model = findRootObjectInFile(file, domain.getResourceSet());
					if(model != null){
						currentResourceSet = domain.getResourceSet();
						// Will be null if the editingDomain is inactive
						EmfWorkspace emfWorkspace = umlElementCache.buildWorkspaces(model, new ProgressMonitorTransformationLog(monitor, 1000));
						newDomainLoaded(domain, file, model, emfWorkspace);
					}
					return new Status(IStatus.OK, OpaeumEclipsePlugin.getId(), "Opaeum Metadata loaded");
				}catch(Exception e){
					return new Status(IStatus.ERROR, OpaeumEclipsePlugin.getId(), "Opaeum Metadata not loaded", e);
				}finally{
					isLoading = false;
					monitor.done();
				}
			}
		}.schedule();
		return true;
	}
	private void newDomainLoaded(final EditingDomain domain,final IFile file,final Package model,EmfWorkspace emfWorkspace){
		emfWorkspaces.put(domain.getResourceSet(), new NakedUmlEditingContext(emfWorkspace, domain, model, file));
		resourceSetsStartedButNotLoaded.remove(domain.getResourceSet());
		domain.getResourceSet().eAdapters().add(umlElementCache);
		errorMarker.maybeSchedule();
	}
	public boolean isOpen(){
		return isOpen;
	}
	public void setCurrentEditContext(EditingDomain rs,IFile file){
		this.currentResourceSet = rs.getResourceSet();
		NakedUmlEditingContext editingContext = emfWorkspaces.get(rs.getResourceSet());
		if(editingContext != null){
			getEmfToOpaeumSynchronizer().setCurrentEmfWorkspace(editingContext.getEmfWorkspace());
			// could still be loading
		}
	}
	public void onSave(IProgressMonitor monitor,ResourceSet resourceSet){
		try{
			monitor.beginTask("Saving UML Models", listeners.size() * 100);
			getEmfToOpaeumSynchronizer().getConfig().getWorkspaceMappingInfo().store();
			for(NakedUmlContextListener l:listeners){
				l.onSave(new SubProgressMonitor(monitor, 100));
			}
			getUmlDirectory().refreshLocal(1, null);
		}catch(CoreException e){
			e.printStackTrace();
		}finally{
			monitor.done();
		}
	}
	public void onClose(boolean save,ResourceSet rs){
		for(NakedUmlContextListener l:listeners){
			l.onClose(save);
		}
		if(umlElementCache != null && this.emfWorkspaces.containsKey(rs)){
			this.emfWorkspaces.remove(rs);
			rs.eAdapters().remove(umlElementCache);
			currentResourceSet = null;
		}
	}
	public void addContextListener(NakedUmlContextListener l){
		this.listeners.add(l);
		umlElementCache.addSynchronizationListener(l);
	}
	public IContainer getUmlDirectory(){
		return umlDirectory;
	}
	public boolean isNakedRootObjectLoaded(IFile r){
		for(INakedRootObject ro:getEmfToOpaeumSynchronizer().getNakedWorkspace().getRootObjects()){
			if(ro.getFileName().equals(r.getLocation().lastSegment()) && ro.getStatus().isExtracted()){
				return true;
			}
		}
		return false;
	}
	public EmfWorkspace getCurrentEmfWorkspace(){
		if(currentResourceSet == null || !emfWorkspaces.containsKey(currentResourceSet) || emfWorkspaces.isEmpty()){
			return null;
		}else{
			return this.emfWorkspaces.get(currentResourceSet).getEmfWorkspace();
		}
	}
	public void loadDirectory(IProgressMonitor monitor){
		this.isLoading = true;
		try{
			getEmfToOpaeumSynchronizer().suspend();
			monitor.beginTask("Loading EMF resources", 300);
			EmfWorkspace dew = getCurrentEmfWorkspace();
			if(dew == null){
				if(emfWorkspaces.size() > 0){
					// Poach a workspace from one of the open editors
					NakedUmlEditingContext next = emfWorkspaces.values().iterator().next();
					dew = next.getEmfWorkspace();
				}else{
					// No open editors - create a temp EmfWorkspace
					ResourceSet rst = new ResourceSetImpl();
					URI uri = URI.createPlatformResourceURI(getUmlDirectory().getFullPath().toString(), true);
					OpaeumConfig cfg = getEmfToOpaeumSynchronizer().getConfig();
					dew = new EmfWorkspace(uri, rst, cfg.getWorkspaceMappingInfo(), cfg.getWorkspaceIdentifier());
					dew.setUriToFileConverter(new EclipseUriToFileConverter());
					dew.setName(cfg.getWorkspaceName());
				}
			}
			for(IResource r:umlDirectory.members()){
				monitor.subTask("Loading " + r.getName());
				if(r instanceof IFile && r.getFileExtension().equals("uml")){
					if(!isNakedRootObjectLoaded((IFile) r)){
						// Will automatically load NakedModel
						final Resource resource = dew.getResourceSet().getResource(URI.createPlatformResourceURI(((IFile) r).getFullPath().toString(), true), true);
						EcoreUtil.resolveAll(resource);
					}
				}
				monitor.worked(100 / umlDirectory.members().length);
			}
			dew.calculatePrimaryModels();
			getEmfToOpaeumSynchronizer().setCurrentEmfWorkspace(dew);
			// Will only process elements as per their RootObjectStatus
			getEmfToOpaeumSynchronizer().getTransformationProcess().execute(new ProgressMonitorTransformationLog(monitor, 200));
			getEmfToOpaeumSynchronizer().resume();
			errorMarker.maybeSchedule();
		}catch(CoreException e){
			throw new RuntimeException(e);
		}finally{
			this.isLoading = false;
			monitor.done();
		}
	}
	private Package findRootObjectInFile(IResource r,ResourceSet rs){
		EList<Resource> ownedElements = rs.getResources();
		;
		for(Resource element:ownedElements){
			if(element.getURI().lastSegment().equals(r.getLocation().lastSegment())){
				for(EObject eObject:element.getContents()){
					if(eObject instanceof Model || eObject instanceof Package){
						return (Package) eObject;
					}
				}
			}
		}
		return null;
	}
	public boolean isSyncronizingWith(ResourceSet resourceSet){
		return resourceSetsStartedButNotLoaded.contains(resourceSet) || emfWorkspaces.containsKey(resourceSet);
	}
	public void removeNakedModel(ResourceSet resourceSet){
		INakedModelWorkspace nws = getEmfToOpaeumSynchronizer().getNakedWorkspace();
		nws.removeOwnedElement(nws.getModelElement(getId(emfWorkspaces.get(resourceSet).getModel())), true);
	}
	public boolean getAutoSync(){
		return this.autoSync;
	}
	public void setAutoSync(boolean b){
		this.autoSync = b;
	}
	public boolean isLoading(){
		return this.isLoading;
	}
	public EditingDomain getEditingDomain(){
		if(currentResourceSet != null && emfWorkspaces.get(currentResourceSet) != null){
			NakedUmlEditingContext editingContext = emfWorkspaces.get(currentResourceSet);
			return editingContext.editingDomain;
		}else{
			return null;
		}
	}
	public OpaeumConfig getConfig(){
		return getEmfToOpaeumSynchronizer().getConfig();
	}
	public INakedModelWorkspace getNakedWorkspace(){
		return getEmfToOpaeumSynchronizer().getNakedWorkspace();
	}
	public static OpaeumEclipseContext getCurrentContext(){
		return currentContext;
	}
	public static void setCurrentContext(OpaeumEclipseContext currentContext){
		OpaeumEclipseContext.currentContext = currentContext;
	}
	public static OpaeumEclipseContext findOrCreateContextFor(IContainer umlDir){
		OpaeumEclipseContext result = getContextFor(umlDir);
		if(result == null){
			OpaeumConfig cfg = null;
			final IFile propsFile = (IFile) umlDir.getFile(new Path("opaeum.properties"));
			boolean newContext = !propsFile.exists();
			if(newContext){
				cfg = new OpaeumConfig(new File(umlDir.getLocation().toFile(), "opaeum.properties"));
				OpaeumConfigDialog dlg = new OpaeumConfigDialog(Display.getDefault().getActiveShell(), cfg);
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
			cfg.calculateOutputRoot(umlDir.getProject().getLocation().toFile());
			final OpaeumEclipseContext newOne = new OpaeumEclipseContext(cfg, umlDir, newContext);
			contexts.put(umlDir, newOne);
			result = newOne;
		}
		setCurrentContext(result);
		return result;
	}
	public static OpaeumEclipseContext getContextFor(IContainer umlDir){
		OpaeumEclipseContext result = contexts.get(umlDir);
		return result;
	}

}
