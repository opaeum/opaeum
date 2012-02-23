package org.opaeum.eclipse.context;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.EditingDomainManager;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.EclipseUriToFileConverter;
import org.opaeum.eclipse.EmfToOpaeumSynchronizer;
import org.opaeum.eclipse.OclUpdater;
import org.opaeum.eclipse.OpaeumConfigDialog;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.OpaeumErrorMarker;
import org.opaeum.eclipse.ProgressMonitorTransformationLog;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.emf.workspace.UriToFileConverter;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.models.INakedModel;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.runtime.domain.ExceptionAnalyser;

public class OpaeumEclipseContext{
	private class LoadEditingDomainJob extends Job{
		private final EditingDomain domain;
		private final IFile file;
		boolean done = false;
		public LoadEditingDomainJob nextJob;
		private Status status;
		private LoadEditingDomainJob(String name,EditingDomain domain,IFile file){
			super(name);
			this.domain = domain;
			this.file = file;
		}
		@Override
		protected IStatus run(final IProgressMonitor monitor){
			isLoading = true;
			monitor.beginTask("Loading Opaeum  Metadata", 1000);
			monitor.subTask("Resolving Emf Resources");
			final Package model = findRootObjectInFile(file, domain.getResourceSet());
			domain.getCommandStack().execute(new AbstractCommand(){
				@Override
				public boolean canExecute(){
					return true;
				}
				@Override
				public void execute(){
					try{
						EcoreUtil.resolveAll(domain.getResourceSet());
						Package model2 = findRootObjectInFile(file, domain.getResourceSet());
						if(model2 != null && model2.eResource() != null && model2.eResource().getURI() != null){
							monitor.subTask("Resolving Emf Resources");
							// Will be null if the editingDomain is inactive
							currentOpenFile = file;
							EmfWorkspace emfWorkspace = umlElementCache.buildWorkspaces(model2, new ProgressMonitorTransformationLog(monitor, 1000));
							newDomainLoaded(domain, file, model2, emfWorkspace);
						}
						status = new Status(IStatus.OK, OpaeumEclipsePlugin.getId(), "Opaeum Metadata loaded");
					}catch(NullPointerException e){
						ExceptionAnalyser ea = new ExceptionAnalyser(e);
						if(model == null || model.eResource() == null || model.eResource().getURI() == null
								|| model.eResource().getResourceSet() == null
								|| ea.stringOccurs("org.eclipse.emf.ecore.util.ECrossReferenceAdapter$InverseCrossReferencer$2.add")){
							// Model closed while loading. NakedModel in unknown state. Remove
							reinitialize();
							status = new Status(IStatus.INFO, OpaeumEclipsePlugin.getId(), "Opaeum Metadata not loaded", e);
						}else{
							status = new Status(IStatus.ERROR, OpaeumEclipsePlugin.getId(), "Opaeum Metadata not loaded", e);
						}
					}catch(Exception e){
						status = new Status(IStatus.ERROR, OpaeumEclipsePlugin.getId(), "Opaeum Metadata not loaded", e);
					}finally{
						isLoading = false;
						monitor.done();
						done = true;
						if(nextJob != null){
							nextJob.schedule();
						}
					}
				}
				@Override
				public void redo(){
				}
			});
			return status;
		}
	}
	private static Map<IContainer,OpaeumEclipseContext> contexts = new WeakHashMap<IContainer,OpaeumEclipseContext>();
	private static OpaeumEclipseContext currentContext;
	private Set<IFile> resourceSetsStartedButNotLoaded = new HashSet<IFile>();
	private Map<IFile,OpenUmlFile> openUmlFiles = new HashMap<IFile,OpenUmlFile>();
	private EmfToOpaeumSynchronizer umlElementCache;
	private boolean isOpen = false;
	private List<OpaeumEclipseContextListener> listeners = new ArrayList<OpaeumEclipseContextListener>();
	private IContainer umlDirectory;
	private IFile currentOpenFile;
	protected UriToFileConverter resourceHelper = new EclipseUriToFileConverter();
	private OpaeumErrorMarker errorMarker;
	private boolean isLoading;
	private boolean newlyCreated;
	private LoadEditingDomainJob currentJob;
	private EObjectSelectorUI eObjectSelectorUI;
	private TransactionalEditingDomain directoryEditingDomain;
	public OpaeumEclipseContext(OpaeumConfig cfg,IContainer umlDirectory,boolean newlyCreated){
		super();
		isOpen = true;
		this.newlyCreated = newlyCreated;
		umlElementCache = new EmfToOpaeumSynchronizer(cfg);
		umlElementCache.addSynchronizationListener(new OclUpdater(this.openUmlFiles));
		this.umlDirectory = umlDirectory;
		this.errorMarker = new OpaeumErrorMarker(this);
		umlElementCache.addSynchronizationListener(errorMarker);
	}
	public boolean isNewlyCreated(){
		return newlyCreated;
	}
	public void reinitialize(){
		resourceSetsStartedButNotLoaded.clear();
		umlElementCache.reinitializeProcess();
		ArrayList<OpenUmlFile> arrayList = new ArrayList<OpenUmlFile>(openUmlFiles.values());
		this.openUmlFiles.clear();
		for(OpenUmlFile editingContext:arrayList){
			getEmfToOpaeumSynchronizer().deregister(editingContext.getEditingDomain().getResourceSet());
			getEmfToOpaeumSynchronizer().removeSynchronizationListener(editingContext);
		}
		for(OpenUmlFile editingContext:arrayList){
			startSynch(editingContext.getEditingDomain(), editingContext.getFile());
		}
		errorMarker.maybeSchedule();
	}
	public String getId(Element umlElement){
		return getEmfToOpaeumSynchronizer().getCurrentEmfWorkspace().getId(umlElement);
	}
	public Collection<EmfWorkspace> getEmfWorkspaces(){
		Collection<EmfWorkspace> result = new HashSet<EmfWorkspace>();
		for(OpenUmlFile editingContext:this.openUmlFiles.values()){
			result.add(editingContext.getEmfWorkspace());
		}
		return result;
	}
	// TODO declare private and delegate from context
	public EmfToOpaeumSynchronizer getEmfToOpaeumSynchronizer(){
		return umlElementCache;
	}
	public boolean startSynch(final EditingDomain domain,final IFile file){
		resourceSetsStartedButNotLoaded.add(file);
		if(currentJob != null && !currentJob.done){
			this.currentJob.nextJob = new LoadEditingDomainJob("Loading Opaeum Metadata", domain, file);
		}else{
			this.currentJob = new LoadEditingDomainJob("Loading Opaeum Metadata", domain, file);
			currentJob.schedule();
		}
		return true;
	}
	private void newDomainLoaded(final EditingDomain domain,final IFile file,final Package model,EmfWorkspace emfWorkspace){
		OpenUmlFile openUmlFile = new OpenUmlFile(emfWorkspace, domain, model, file);
		openUmlFiles.put(file, openUmlFile);
		resourceSetsStartedButNotLoaded.remove(file);
		umlElementCache.registerTo(domain.getResourceSet());
		umlElementCache.addSynchronizationListener(openUmlFile);
		errorMarker.maybeSchedule();
	}
	public boolean isOpen(){
		return isOpen;
	}
	public void setCurrentEditContext(EditingDomain rs,IFile file,EObjectSelectorUI eObjectSelectorUI){
		seteObjectSelectorUI(eObjectSelectorUI);
		this.currentOpenFile = file;
		OpenUmlFile editingContext = openUmlFiles.get(rs.getResourceSet());
		if(editingContext != null){
			getEmfToOpaeumSynchronizer().setCurrentEmfWorkspace(editingContext.getEmfWorkspace());
			// could still be loading
		}
	}
	public void onSave(IProgressMonitor monitor,ResourceSet resourceSet){
		try{
			OpenUmlFile openUmlFile = this.openUmlFiles.get(resourceSet);
			if(openUmlFile != null){
				openUmlFile.setDirty(false);
			}
			monitor.beginTask("Saving UML Models", listeners.size() * 100);
			getEmfToOpaeumSynchronizer().getConfig().getWorkspaceMappingInfo().store();
			for(OpaeumEclipseContextListener l:listeners){
				l.onSave(new SubProgressMonitor(monitor, 100));
			}
			getUmlDirectory().refreshLocal(1, null);
		}catch(CoreException e){
			e.printStackTrace();
		}finally{
			monitor.done();
		}
	}
	public void onClose(IFile umlFile){
		OpenUmlFile openUmlFile = this.openUmlFiles.get(umlFile);
		for(OpaeumEclipseContextListener l:listeners){
			l.onClose(openUmlFile == null || openUmlFile.isDirty());
		}
		this.openUmlFiles.remove(umlFile);
		if(openUmlFile != null){
			// May not have loaded successfully
			if(openUmlFile.isDirty()){
				reinitialize();
			}else if(umlElementCache != null && openUmlFile != null){
				umlElementCache.deregister(openUmlFile.getEmfWorkspace().getResourceSet());
				getEmfToOpaeumSynchronizer().removeSynchronizationListener(openUmlFile);
				currentOpenFile = null;
			}
		}
	}
	public void addContextListener(OpaeumEclipseContextListener l){
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
		if(currentOpenFile == null || !openUmlFiles.containsKey(currentOpenFile) || openUmlFiles.isEmpty()){
			return null;
		}else{
			return this.openUmlFiles.get(currentOpenFile).getEmfWorkspace();
		}
	}
	public void loadDirectory(IProgressMonitor monitor){
		this.isLoading = true;
		try{
			getEmfToOpaeumSynchronizer().suspend();
			monitor.beginTask("Loading EMF resources", 300);
			EmfWorkspace dew = getCurrentEmfWorkspace();
			if(dew == null){
				if(openUmlFiles.size() > 0){
					// Poach a workspace from one of the open editors
					OpenUmlFile next = openUmlFiles.values().iterator().next();
					dew = next.getEmfWorkspace();
				}else{
					// No open editors - create a temp EmfWorkspace
					ResourceSet rst;
					rst = new ResourceSetImpl();
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
						final Resource resource = dew.getResourceSet().getResource(
								URI.createPlatformResourceURI(((IFile) r).getFullPath().toString(), true), true);
						EcoreUtil.resolveAll(resource);
					}
				}
				monitor.worked(100 / umlDirectory.members().length);
			}
			dew.guessGeneratingModelsAndProfiles(URI.createPlatformResourceURI(umlDirectory.getFullPath().toString(), true));
			// Will only process elements as per their RootObjectStatus
			getEmfToOpaeumSynchronizer().getTransformationProcess().replaceModel(dew);
			getEmfToOpaeumSynchronizer().getTransformationProcess().execute(new ProgressMonitorTransformationLog(monitor, 200));
			getEmfToOpaeumSynchronizer().setCurrentEmfWorkspace(dew);
			INakedModelWorkspace nakedWorkspace = getEmfToOpaeumSynchronizer().getNakedWorkspace();
			nakedWorkspace.clearGeneratingModelOrProfiles();
			for(INakedRootObject ro:nakedWorkspace.getRootObjects()){
				if(ro instanceof INakedModel && ((INakedModel) ro).isRegeneratingLibrary()){
					// TODO check if code should be regenerated;
					nakedWorkspace.addGeneratingRootObject(ro);
				}else{
					for(IResource r:getUmlDirectory().members()){
						if(r.getLocation().lastSegment().equals(ro.getFileName())){
							nakedWorkspace.addGeneratingRootObject(ro);
							break;
						}
					}
				}
			}
			getEmfToOpaeumSynchronizer().resume();
			errorMarker.maybeSchedule();
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
	private Package findRootObjectInFile(IResource r,ResourceSet rs){
		EList<Resource> ownedElements = rs.getResources();
		for(Resource element:ownedElements){
			if(element.getURI().trimFileExtension().lastSegment().equals(r.getLocation().removeFileExtension().lastSegment())){
				for(EObject eObject:element.getContents()){
					if(eObject instanceof Model || eObject instanceof Package){
						return (Package) eObject;
					}
				}
			}
		}
		return null;
	}
	public boolean isSyncronizingWith(IFile file){
		return resourceSetsStartedButNotLoaded.contains(file) || openUmlFiles.containsKey(file);
	}
	public boolean getAutoSync(){
		return this.getConfig().synchronizeAutomatically();
	}
	public boolean isLoading(){
		return this.isLoading;
	}
	public EditingDomain getEditingDomain(){
		if(currentOpenFile != null && openUmlFiles.get(currentOpenFile) != null){
			OpenUmlFile editingContext = openUmlFiles.get(currentOpenFile);
			return editingContext.editingDomain;
		}else if(directoryEditingDomain != null){
			return directoryEditingDomain;
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
	public OpenUmlFile getEditingContextFor(IFile f){
		return this.openUmlFiles.get(f);
	}
	public static OpaeumEclipseContext getContextFor(IContainer umlDir){
		OpaeumEclipseContext result = contexts.get(umlDir);
		return result;
	}
	public static void selectContext(Element e){
		setCurrentContext(getContextFor(e));
		getCurrentContext().selectOpenFileFor(e);
	}
	private void selectOpenFileFor(Element e){
		// TODO consider using the element's resource's filename
		Set<Entry<IFile,OpenUmlFile>> entrySet = openUmlFiles.entrySet();
		for(Entry<IFile,OpenUmlFile> entry:entrySet){
			if(entry.getValue().getEmfWorkspace().getResourceSet().equals(e.eResource().getResourceSet())){
				currentOpenFile = entry.getKey();
				break;
			}
		}
	}
	public static OpaeumEclipseContext getContextFor(Element element){
		return getContextFor(ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(element.eResource().getURI().toPlatformString(true)))
				.getParent());
	}
	public EObjectSelectorUI geteObjectSelectorUI(){
		return eObjectSelectorUI;
	}
	public void seteObjectSelectorUI(EObjectSelectorUI eObjectSelectorUI){
		this.eObjectSelectorUI = eObjectSelectorUI;
	}
	public void executeAndForget(final Command command){
		performExecute(command, null);
	}
	public void executeAndWait(final Command command){
		if(Display.getCurrent() != null){
			throw new IllegalStateException("ExecuteAndWait can only be called from background threads");
		}
		if(getEditingDomain() instanceof TransactionalEditingDomain){
			try{
				Object lock = new Object();
				synchronized(lock){
					executeInOwnTransaction(command, lock);
					lock.wait(3*60*1000);
				}
			}catch(InterruptedException e){
				throw new RuntimeException(e);
			}
		}else if(getEditingDomain() == null){
			command.execute();
		}else{
			getEditingDomain().getCommandStack().execute(command);
		}
	}
	private void performExecute(final Command command,final Object lock){
		if(getEditingDomain() instanceof TransactionalEditingDomain){
			executeInOwnTransaction(command, lock);
		}else if(getEditingDomain() == null){
			command.execute();
		}else{
			getEditingDomain().getCommandStack().execute(command);
		}
	}
	private void executeInOwnTransaction(final Command command,final Object lock){
		// !!!!! Papyrus requires all this shit
		Display.getDefault().asyncExec(new Runnable(){
			public void run(){
				try{
					((TransactionalEditingDomain) getEditingDomain()).runExclusive(new Runnable(){
						public void run(){
							Display.getCurrent().asyncExec(new Runnable(){
								public void run(){
									try{
										getEditingDomain().getCommandStack().execute(command);
									}finally{
										if(lock != null){
											synchronized(lock){
												lock.notifyAll();
											}
										}
									}
								}
							});
						}
					});
				}catch(Exception e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
