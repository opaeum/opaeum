package org.nakeduml.topcased.uml.editor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import net.sf.nakeduml.emf.extraction.EmfExtractionPhase;
import net.sf.nakeduml.emf.workspace.EmfResourceHelper;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.emf.workspace.UmlElementCache;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.nakeduml.eclipse.ProfileApplier;
import org.nakeduml.eclipse.ProgressMonitorTransformationLog;

public final class EclipseUmlElementCache extends UmlElementCache{
	boolean suspended = false;
	private UmlCacheListener umlModelUpdator;
	NakedUmlElementLinker linker = new NakedUmlElementLinker();
	EclipseUmlElementCache(NakedUmlConfig cfg,UmlCacheListener umlModelUpdator){
		super(new EclipseEmfResourceHelper(), cfg);
		this.umlModelUpdator = umlModelUpdator;
	}
	public void suspend(){
		suspended = true;
	}
	public void resume(){
		suspended = false;
		scheduleSynchronization();
	}
	protected void scheduleSynchronization(){
		if(!suspended){
			EmfToNakedUmlSynchronizer synchronizer = new EmfToNakedUmlSynchronizer(this, emfChanges, nakedModelWorspace, nakedUmlChanges, renamedRequestsByNewName,
					umlModelUpdator);
			synchronizer.schedule();
		}
	}
	protected void synchronizationNow(final Set<Package> packages){
		Display.getDefault().syncExec(new Runnable(){
			@Override
			public void run(){
				ProgressMonitorDialog dlg = new ProgressMonitorDialog(Display.getDefault().getActiveShell());
				IProgressMonitor pm = dlg.getProgressMonitor();
				dlg.open();
				try{
					pm.beginTask("Loading new Packages", 50);
					getTransformationProcess().processElements(packages, EmfExtractionPhase.class, new ProgressMonitorTransformationLog(pm, 50));
					pm.done();
				}finally{
					dlg.close();
					pm.done();
				}
			}
		});
	}
	@Override
	public void notifyChanged(Notification notification){
		if(!suspended){
			linker.notifyChanged(notification);
		}
		super.notifyChanged(notification);
	}
	@Override
	public void reinitializeProcess(){
		super.reinitializeProcess();
	}
	@Override
	public void emfWorkspaceLoaded(EmfWorkspace w){
		for(Package pkg:w.getPrimaryModels()){
			if(pkg instanceof Model){
				Model model = (Model) pkg;
				Profile pf = ProfileApplier.applyNakedUmlProfile(model);
				Stereotype st = pf.getOwnedStereotype(StereotypeNames.MODEL);
				if(!model.isStereotypeApplied(st)){
					model.applyStereotype(st);
				}
				if(model.getValue(st, "mappedImplementationPackage") == null){
					model.setValue(st, "mappedImplementationPackage", cfg.getMavenGroupId() + "." + model.getName().toLowerCase());
				}
				try{
					model.eResource().save(new HashMap<Object,Object>());
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	public void setCurrentEmfWorkspace(EmfWorkspace e){
		transformationProcess.replaceModel(e);
		INakedModelWorkspace nws = getNakedWorkspace();
		nws.clearGeneratingModelOrProfiles();
		for(Package package1:e.getPrimaryModels()){
			nws.addPrimaryModel((INakedRootObject) nws.getModelElement(resourceHelper.getId(package1)));
		}
		for(Package g:e.getGeneratingModelsOrProfiles()){
			nws.addGeneratingRootObject((INakedRootObject) nws.getModelElement(resourceHelper.getId(g)));
		}
		this.currentEmfWorkspace = e;
	}
	public EmfWorkspace getCurrentEmfWorkspace(){
		return currentEmfWorkspace;
	}
	public EmfResourceHelper getResourceHelper(){
		return resourceHelper;
	}
}