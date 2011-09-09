package org.nakeduml.topcased.uml.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.emf.load.EmfWorkspaceLoader;
import net.sf.nakeduml.emf.workspace.EmfResourceHelper;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.metamodel.actions.INakedOclAction;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.oclengine.internal.OclContextImpl;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLPackage;
import org.nakeduml.eclipse.ProgressMonitorTransformationLog;
import org.nakeduml.topcased.commands.SetOclBodyCommand;
import org.nakeduml.topcased.uml.NakedUmlPlugin;

public class NakedUmlEclipseContext{
	private class EditingContext{
		EmfWorkspace emfWorkspace;
		EditingDomain editingDomain;
		Package model;
		private IFile file;
		public EditingContext(EmfWorkspace emfWorkspace,EditingDomain editingDomain,Package model,IFile f){
			super();
			this.emfWorkspace = emfWorkspace;
			this.editingDomain = editingDomain;
			this.model = model;
			this.file = f;
		}
	}
	private Set<ResourceSet> resourceSetsStartedButNotLoaded=new HashSet<ResourceSet>();
	private Map<ResourceSet,EditingContext> emfWorkspaces = new HashMap<ResourceSet,EditingContext>();
	private EclipseUmlElementCache umlElementCache;
	private boolean isOpen = false;
	private List<NakedUmlContextListener> listeners = new ArrayList<NakedUmlContextListener>();
	private IContainer umlDirectory;
	private ResourceSet currentResourceSet;
	private EmfWorkspace directoryEmfWorkspace;
	protected EmfResourceHelper resourceHelper = new EclipseEmfResourceHelper();
	private NakedUmlErrorMarker errorMarker;
	private List<Runnable> synchronizationListeners = new ArrayList<Runnable>();
	private boolean autoSync;
	private boolean isLoadingDirectory;
	public NakedUmlEclipseContext(NakedUmlConfig cfg,IContainer umlDirectory){
		super();
		isOpen = true;
		umlElementCache = new EclipseUmlElementCache(cfg, new EclipseUmlCacheListener());
		this.umlDirectory = umlDirectory;
		this.errorMarker = new NakedUmlErrorMarker(this);
	}
	public void reinitialize(NakedUmlConfig cfg){
		directoryEmfWorkspace = null;
		umlElementCache.reinitializeProcess(cfg);
		ArrayList<EditingContext> arrayList = new ArrayList<EditingContext>(emfWorkspaces.values());
		this.emfWorkspaces.clear();
		for(EditingContext editingContext:arrayList){
			editingContext.editingDomain.getResourceSet().eAdapters().remove(getUmlElementCache());
		}
		for(EditingContext editingContext:arrayList){
			startSynch(editingContext.editingDomain, editingContext.file);
		}
		errorMarker.maybeSchedule();
	}
	public String getId(Element umlElement){
		return resourceHelper.getId(umlElement);
	}
	public Collection<EmfWorkspace> getEmfWorkspaces(){
		Collection<EmfWorkspace> result = new HashSet<EmfWorkspace>();
		for(EditingContext editingContext:this.emfWorkspaces.values()){
			result.add(editingContext.emfWorkspace);
		}
		if(directoryEmfWorkspace!=null){
			result.add(directoryEmfWorkspace);
		}
		return result;
	}
	public EclipseUmlElementCache getUmlElementCache(){
		return umlElementCache;
	}
	public boolean startSynch(final EditingDomain domain,final IFile file){
		resourceSetsStartedButNotLoaded.add(domain.getResourceSet());
		if(directoryEmfWorkspace == null){
			new Job("Loading Opium Metadata"){
				@Override
				protected IStatus run(IProgressMonitor monitor){
					try{
						final Package model = findRootObjectInFile(file, domain.getResourceSet());
						loadDirectory(monitor,domain.getResourceSet());
						if(model != null && model.eResource() != null){// Might have been closed in the meantime
							currentResourceSet = domain.getResourceSet();
							NakedUmlConfig cfg = getUmlElementCache().getConfig();
							EmfWorkspace emfWorkspace = new EmfWorkspace(model, cfg.getWorkspaceMappingInfo(), cfg.getWorkspaceIdentifier());
							emfWorkspace.setResourceHelper(new EclipseEmfResourceHelper());
							emfWorkspace.setName(cfg.getWorkspaceName());
							getUmlElementCache().setCurrentEmfWorkspace(emfWorkspace);
							newDomainLoaded(domain, file, model, emfWorkspace);
						}
						return new Status(IStatus.OK, NakedUmlPlugin.getId(), "Opium Metadata loaded");
					}catch(Exception e){
						return new Status(IStatus.ERROR, NakedUmlPlugin.getId(), "Opium Metadata not loaded", e);
					}finally{
						monitor.done();
					}
				}

			}.schedule();
		}else{
			new Job("Loading Opium Metadata"){
				@Override
				protected IStatus run(final IProgressMonitor monitor){
					monitor.beginTask("Loading Opium  Metadata", 1000);
					try{
						final Package model = findRootObjectInFile(file, domain.getResourceSet());
						if(model != null){
							currentResourceSet = domain.getResourceSet();
							// Will be null if the editingDomain is inactive
							EmfWorkspace emfWorkspace = umlElementCache.buildWorkspaces(model, new ProgressMonitorTransformationLog(monitor, 1000));
							newDomainLoaded(domain, file, model, emfWorkspace);
						}
						return new Status(IStatus.OK, NakedUmlPlugin.getId(), "Opium Metadata loaded");
					}catch(Exception e){
						return new Status(IStatus.ERROR, NakedUmlPlugin.getId(), "Opium Metadata not loaded", e);
					}finally{
						monitor.done();
					}
				}
			}.schedule();
		}
		return true;
	}
	protected void newDomainLoaded(final EditingDomain domain,final IFile file,final Package model,EmfWorkspace emfWorkspace){
		emfWorkspaces.put(domain.getResourceSet(), new EditingContext(emfWorkspace, domain, model, file));
		resourceSetsStartedButNotLoaded.add(domain.getResourceSet());
		domain.getResourceSet().eAdapters().add(umlElementCache);
		errorMarker.maybeSchedule();
	}
	public boolean isOpen(){
		return isOpen;
	}
	public void setCurrentEditContext(EditingDomain rs,IFile file){
		this.currentResourceSet = rs.getResourceSet();
		EditingContext editingContext = emfWorkspaces.get(rs.getResourceSet());
		if(editingContext!=null){
			getUmlElementCache().setCurrentEmfWorkspace(editingContext.emfWorkspace);
			//could still be loading
		}
	}
	public void onSave(IProgressMonitor monitor,ResourceSet resourceSet){
		try{
			monitor.beginTask("Saving UML Models", listeners.size() * 100);
			getUmlElementCache().getConfig().getWorkspaceMappingInfo().store();
			
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
		if(isLoadingDirectory){
			isLoadingDirectory=false;
			getUmlElementCache().reinitializeProcess(getUmlElementCache().getConfig());
		}
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
	}
	public IContainer getUmlDirectory(){
		return umlDirectory;
	}
	public boolean isNakedRootObjectLoaded(IFile r){
		for(EmfWorkspace emfWorkspace:getEmfWorkspaces()){
			if(hasLoadedNakedRootObjectFor(r, emfWorkspace)){
				return true;
			}
		}
		if(directoryEmfWorkspace != null){
			return hasLoadedNakedRootObjectFor(r, directoryEmfWorkspace);
		}
		return false;
	}
	private boolean hasLoadedNakedRootObjectFor(IFile r,EmfWorkspace emfWorkspace){
		boolean result = false;
		outer:for(Resource resource:emfWorkspace.getResourceSet().getResources()){
			if(resource.getURI().lastSegment().equals(r.getName())){
				for(EObject eObject:resource.getContents()){
					if(eObject instanceof Profile || eObject instanceof Model){
						result = getUmlElementCache().getNakedWorkspace().getModelElement(getId((Element) eObject)) != null;
						break outer;
					}
				}
			}
		}
		return result;
	}
	public void loadFile(IFile f){
		Resource r = currentResourceSet.getResource(URI.createPlatformResourceURI(f.getFullPath().toString(), true), true);
		// The UMLElementCache will synchronously update the INAkedModelWorkspace with all loaded elements
	}
	public Package getCurrentModelOrProfile(){
		EditingContext editingContext = this.emfWorkspaces.get(currentResourceSet);
		if(editingContext != null){
			return editingContext.model;
		}else{
			return null;
		}
	}
	public EmfWorkspace getCurrentEmfWorkspace(){
		if(currentResourceSet == null || !emfWorkspaces.containsKey(currentResourceSet) || emfWorkspaces.isEmpty()){
			return directoryEmfWorkspace;
		}else{
			return this.emfWorkspaces.get(currentResourceSet).emfWorkspace;
		}
	}
	public void loadDirectory(IProgressMonitor monitor){
		loadDirectory(monitor, new ResourceSetImpl());
		getUmlElementCache().setCurrentEmfWorkspace(directoryEmfWorkspace);
	}
	private void loadDirectory(IProgressMonitor monitor,ResourceSet rrst){
		this.isLoadingDirectory=true;
		monitor.beginTask("Loading EMF resources", 30);
		try{
			if(directoryEmfWorkspace == null){
				currentResourceSet = rrst;
				EmfWorkspace dew = EmfWorkspaceLoader.loadDirectory(currentResourceSet, umlDirectory.getLocation().toFile(), umlElementCache.getConfig(), new ProgressMonitorTransformationLog(monitor, 15));
				dew.setResourceHelper(new EclipseEmfResourceHelper());
				rrst.eAdapters().add(getUmlElementCache());
				INakedModelWorkspace nmws = getUmlElementCache().getNakedWorkspace();
				nmws.clearGeneratingModelOrProfiles();
				getUmlElementCache().getTransformationProcess().replaceModel(dew);
				getUmlElementCache().getTransformationProcess().execute(new ProgressMonitorTransformationLog(monitor, 10));
				this.directoryEmfWorkspace=dew;
			}else{
				currentResourceSet=directoryEmfWorkspace.getResourceSet();
				monitor.worked(25);
			}
			getUmlElementCache().getTransformationProcess().replaceModel(directoryEmfWorkspace);
			// Ensure ALL models are loaded TODO why would they not be?
			for(IResource r:umlDirectory.members()){
				if(r instanceof IFile && r.getFileExtension().equals("uml")){
					if(!isNakedRootObjectLoaded((IFile) r)){
						// Will automatically load NakedModel
						loadFile((IFile) r);
					}
				}
			}
			monitor.worked(5);
			this.isLoadingDirectory=false;
		}catch(CoreException e){
			throw new RuntimeException(e);
		}finally{
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
	private class EclipseUmlCacheListener implements UmlCacheListener{
		public void updateOclReferencesTo(INakedElement ne){
			for(INakedElement de:umlElementCache.getNakedWorkspace().getDependentElements(ne)){
				if(de instanceof INakedValueSpecification && ((INakedValueSpecification) de).isValidOclValue()){
					final INakedValueSpecification vs = (INakedValueSpecification) de;
					updateOclBody(de, vs.getOclValue(), UMLPackage.eINSTANCE.getOpaqueExpression_Body(), UMLPackage.eINSTANCE.getOpaqueExpression_Language());
				}else if(de instanceof INakedOpaqueBehavior && ((INakedOpaqueBehavior) ne).getBodyExpression() instanceof OclContextImpl){
					final INakedOpaqueBehavior vs = (INakedOpaqueBehavior) de;
					updateOclBody(de, vs.getBodyExpression(), UMLPackage.eINSTANCE.getOpaqueBehavior_Body(), UMLPackage.eINSTANCE.getOpaqueBehavior_Language());
				}else if(de instanceof INakedOclAction && ((INakedOclAction) ne).getBodyExpression() instanceof OclContextImpl){
					final INakedOclAction vs = (INakedOclAction) de;
					updateOclBody(de, vs.getBodyExpression(), UMLPackage.eINSTANCE.getOpaqueAction_Body(), UMLPackage.eINSTANCE.getOpaqueAction_Language());
				}
			}
		}
		private void updateOclBody(INakedElement de,final IOclContext oclValue,final EAttribute body,final EAttribute language){
			for(final EditingContext ew:emfWorkspaces.values()){
				final NamedElement oe = (NamedElement) ew.emfWorkspace.getElementMap().get(de.getId());
				// Could be artificially generated OCL
				if(oe != null && !ew.editingDomain.isReadOnly(oe.eResource())){
					Display.getDefault().syncExec(new Runnable(){
						@Override
						public void run(){
							SetOclBodyCommand cmd = new SetOclBodyCommand(ew.editingDomain, oe, body, language, oclValue.getExpressionString());
							ew.editingDomain.getCommandStack().execute(cmd);
						}
					});
					break;
				}
			}
		}
		@Override
		public void synchronizationComplete(Set<EObject> asdf,Set<INakedNameSpace> nakedUmlChanges){
			errorMarker.maybeSchedule();
			for(Runnable r:synchronizationListeners){
				System.out.println("NakedUmlEclipseContext.EclipseUmlCacheListener.synchronizationComplete()" + System.currentTimeMillis());
				Display.getDefault().asyncExec(r);
			}
			synchronizationListeners.clear();
		}
	}
	public void removeNakedModel(ResourceSet resourceSet){
		INakedModelWorkspace nws = getUmlElementCache().getNakedWorkspace();
		nws.removeOwnedElement(nws.getModelElement(getId(emfWorkspaces.get(resourceSet).model)));
	}
	public void runOnSynchronization(Runnable highlighter){
		synchronizationListeners.add(highlighter);
	}
	public boolean getAutoSync(){
		return this.autoSync;
	}
	public void setAutoSync(boolean b){
		this.autoSync = b;
	}
}
