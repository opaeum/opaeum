package org.nakeduml.topcased.uml.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.emf.load.EmfWorkspaceLoader;
import net.sf.nakeduml.emf.workspace.EmfResourceHelper;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.metamodel.actions.INakedOclAction;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
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
	private Map<ResourceSet,EditingContext> emfWorkspaces = new HashMap<ResourceSet,EditingContext>();
	private EclipseUmlElementCache umlElementCache;
	private boolean isOpen = false;
	private List<NakedUmlContextListener> listeners = new ArrayList<NakedUmlContextListener>();
	private IContainer umlDirectory;
	private ResourceSet currentResourceSet;
	private EmfWorkspace directoryEmfWorkspace;
	protected EmfResourceHelper resourceHelper = new EclipseEmfResourceHelper();
	public NakedUmlEclipseContext(NakedUmlConfig cfg,IContainer umlDirectory){
		super();
		isOpen = true;
		umlElementCache = new EclipseUmlElementCache(cfg, new UmlModelUpdator());
		this.umlDirectory = umlDirectory;
	}
	public void reinitialize(NakedUmlConfig cfg){
		directoryEmfWorkspace=null;
		umlElementCache.reinitializeProcess(cfg);
		ArrayList<EditingContext> arrayList = new ArrayList<EditingContext>(emfWorkspaces.values());
		this.emfWorkspaces.clear();
		for(EditingContext editingContext:arrayList){
			editingContext.editingDomain.getResourceSet().eAdapters().remove(getUmlElementCache());
		}
		for(EditingContext editingContext:arrayList){
			startSynch(editingContext.editingDomain, editingContext.file);
		}
	}
	public String getId(Element umlElement){
		return resourceHelper.getId(umlElement);
	}
	public Collection<EmfWorkspace> getEmfWorkspaces(){
		Collection<EmfWorkspace> result = new HashSet<EmfWorkspace>();
		for(EditingContext editingContext:this.emfWorkspaces.values()){
			result.add(editingContext.emfWorkspace);
		}
		return result;
	}
	public EclipseUmlElementCache getUmlElementCache(){
		return umlElementCache;
	}
	public boolean startSynch(final EditingDomain domain,final IFile file){
		currentResourceSet = domain.getResourceSet();
		new Job("Loading NakedUML Model"){
			@Override
			protected IStatus run(final IProgressMonitor monitor){
				monitor.beginTask("Loading NakedUML Metadata", 30);
				try{
					monitor.subTask("Loading EMF Resources");
					umlElementCache.setMonitor(monitor);
					EmfWorkspace emfWorkspace = umlElementCache.buildWorkspaces(domain.getResourceSet(), file.getLocation().toFile());
					Package model = findRootObjectInFile(file, emfWorkspace);
					emfWorkspaces.put(currentResourceSet, new EditingContext(emfWorkspace, domain, model, file));
					domain.getResourceSet().eAdapters().add(umlElementCache);
				}catch(Exception e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					monitor.done();
				}
				return new Status(IStatus.OK, NakedUmlPlugin.getId(), "NakedUML Model loaded");
			}
		}.schedule();
		return true;
	}
	public boolean isOpen(){
		return isOpen;
	}
	public void setCurrentEditContext(EditingDomain rs,IFile file){
		this.currentResourceSet = rs.getResourceSet();
		getUmlElementCache().setCurrentEmfWorkspace(emfWorkspaces.get(rs).emfWorkspace);
	}
	public void onSave(IProgressMonitor monitor,ResourceSet resourceSet){
		for(NakedUmlContextListener l:listeners){
			l.onSave(monitor);
		}
	}
	public void onClose(boolean save,ResourceSet rs){
		for(NakedUmlContextListener l:listeners){
			l.onClose(save);
		}
		if(umlElementCache != null){
			this.emfWorkspaces.remove(rs);
			rs.eAdapters().remove(umlElementCache);
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
	public EmfWorkspace getCurrentEmfWorkspace(){
		if(currentResourceSet == null || !emfWorkspaces.containsKey(currentResourceSet) || emfWorkspaces.isEmpty()){
			return directoryEmfWorkspace;
		}else{
			return this.emfWorkspaces.get(currentResourceSet).emfWorkspace;
		}
	}
	public void loadDirectory(IProgressMonitor monitor){
		ResourceSet resourceSet = new ResourceSetImpl();
		monitor.beginTask("Loading EMF resources", 2);
		directoryEmfWorkspace = EmfWorkspaceLoader.loadDirectory(resourceSet, umlDirectory.getLocation().toFile(), umlElementCache.getConfig());
		directoryEmfWorkspace.setResourceHelper(new EclipseEmfResourceHelper());
		// NB!!! we don't put this resourceset - it is transient
		// this.emfWorkspaces.put(currentResourceSet, w);
		monitor.worked(1);
		getUmlElementCache().getTransformationProcess().replaceModel(directoryEmfWorkspace);
		getUmlElementCache().getTransformationProcess().execute();
		monitor.worked(2);
		monitor.done();
	}
	public void prepareDirectoryTransformation() throws CoreException{
		INakedModelWorkspace nmws = getUmlElementCache().getNakedWorkspace();
		nmws.clearGeneratingModelOrProfiles();
		for(IResource r:umlDirectory.members()){
			if(r instanceof IFile && r.getFileExtension().equals("uml")){
				if(!isNakedRootObjectLoaded((IFile) r)){
					loadFile((IFile) r);
				}
				INakedRootObject rootObjectFor = getRootObjectFor(r);
				nmws.addGeneratingRootObject(rootObjectFor);
			}
		}
	}
	private INakedRootObject getRootObjectFor(IResource r){
		INakedRootObject result = null;
		if(directoryEmfWorkspace != null){
			result = findRootObject(r, directoryEmfWorkspace);
		}
		if(result == null){
			for(EmfWorkspace emfWorkspace:getEmfWorkspaces()){
				if((result = findRootObject(r, emfWorkspace)) != null){
					break;
				}
			}
		}
		return result;
	}
	protected INakedRootObject findRootObject(IResource r,EmfWorkspace emfWorkspace){
		Element result = findRootObjectInFile(r, emfWorkspace);
		if(result != null){
			return (INakedRootObject) getUmlElementCache().getNakedWorkspace().getModelElement(getId(result));
		}
		return null;
	}
	private Package findRootObjectInFile(IResource r,EmfWorkspace emfWorkspace){
		EList<Element> ownedElements = emfWorkspace.getOwnedElements();
		Element result = null;
		for(Element element:ownedElements){
			if(element.eResource().getURI().lastSegment().equals(r.getLocation().lastSegment())){
				result = element;
				break;
			}
		}
		return (Package) result;
	}
	public boolean isSyncronizingWith(ResourceSet resourceSet){
		return emfWorkspaces.containsKey(resourceSet);
	}
	private class UmlModelUpdator implements IUmlModelUpdator{
		public void updateOclBody(INakedElement de,final IOclContext oclValue,final EAttribute body,final EAttribute language){
			for(final EditingContext ew:emfWorkspaces.values()){
				final NamedElement oe = (NamedElement) ew.emfWorkspace.getElementMap().get(de.getId());
				if(!ew.editingDomain.isReadOnly(oe.eResource())){
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
	}
	public void removeNakedModel(ResourceSet resourceSet){
		INakedModelWorkspace nws = getUmlElementCache().getNakedWorkspace();
		nws.removeOwnedElement(nws.getModelElement(getId(emfWorkspaces.get(resourceSet).model)));
	}
}
