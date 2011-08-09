package org.nakeduml.topcased.uml.editor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.emf.extraction.EmfExtractionPhase;
import net.sf.nakeduml.emf.workspace.EmfResourceHelper;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.emf.workspace.UmlElementCache;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationPhase;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Element;
import org.nakeduml.topcased.uml.NakedUmlPlugin;

public class NakedUmlEclipseContext{
	private Map<ResourceSet,EmfWorkspace> emfWorkspaces = new HashMap<ResourceSet,EmfWorkspace>();
	private EclipseUmlElementCache umlElementCache;
	private boolean isOpen = false;
	private List<NakedUmlContextListener> listeners = new ArrayList<NakedUmlContextListener>();
	private IContainer umlDirectory;
	private ResourceSet currentResourceSet;
	protected EmfResourceHelper resourceHelper = new EclipseEmfResourceHelper();
	public NakedUmlEclipseContext(NakedUmlConfig cfg){
		super();
		isOpen = true;
		umlElementCache = new EclipseUmlElementCache(cfg);
	}
	public String getId(Element umlElement){
		return resourceHelper.getId(umlElement);
	}
	public Collection<EmfWorkspace> getEmfWorkspaces(){
		return this.emfWorkspaces.values();
	}
	public EclipseUmlElementCache getUmlElementCache(){
		return umlElementCache;
	}
	public boolean startSynch(final ResourceSet resourceSet,final IFile file) {
		this.umlDirectory = file.getParent();
		new Job("Loading NakedUML Model"){
			@Override
			protected IStatus run(final IProgressMonitor monitor){
				monitor.beginTask("Loading NakedUML Metadata", 30);
				long start = System.currentTimeMillis();
				try{
					monitor.subTask("Loading EMF Resources");
					umlElementCache.setMonitor(monitor);
					umlElementCache.buildWorkspaces(resourceSet, file.getLocation().toFile());
				}catch(Exception e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					monitor.done();
				}
				System.out.println("NakedUmlEclipseContext.startSynch(...).new Job() {...}.run() took " + (System.currentTimeMillis() - start));
				return new Status(IStatus.OK, NakedUmlPlugin.getId(), "NakedUML Model loaded");
			}
		}.schedule();
		return true;
	}
	public boolean isOpen(){
		return isOpen;
	}
	public void setCurrentResourceSet(ResourceSet rs,IFile file){
		this.currentResourceSet = rs;
		getUmlElementCache().setCurrentEmfWorkspace(emfWorkspaces.get(rs));
	}
	public void onSave(IProgressMonitor monitor){
		for(NakedUmlContextListener l:listeners){
			l.onSave(monitor);
		}
	}
	public void onClose(boolean save){
		for(NakedUmlContextListener l:listeners){
			l.onClose(save);
		}
		this.isOpen = false;
		if(umlElementCache != null){
			this.currentResourceSet.eAdapters().remove(umlElementCache);
		}
	}
	public void addContextListener(NakedUmlContextListener l){
		this.listeners.add(l);
	}
	public IContainer getUmlDirectory(){
		return umlDirectory;
	}
	public boolean isSyncronizingWith(IFile r){
		for(EmfWorkspace emfWorkspace:this.emfWorkspaces.values()){
			for(Resource resource:emfWorkspace.getResourceSet().getResources()){
				if(resource.getURI().lastSegment().equals(r.getName())){
					return true;
				}
			}
		}
		return false;
	}
	public void loadFile(IFile f){
		Resource r = currentResourceSet.getResource(URI.createPlatformResourceURI(f.getFullPath().toString(),true), true);
	}
	public EmfWorkspace getCurrentEmfWorkspace(){
		return this.emfWorkspaces.get(currentResourceSet);
	}
}
