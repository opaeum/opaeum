package org.nakeduml.topcased.uml.editor;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.emf.extraction.EmfExtractionPhase;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.emf.workspace.UmlElementCache;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.nakeduml.eclipse.ApplyProfileAction;
import org.nakeduml.eclipse.ProgressMonitorTransformationLog;
import org.nakeduml.topcased.uml.NakedUmlPlugin;

public final class EclipseUmlElementCache extends UmlElementCache{
	public final class EmfToNakedUmlSynchronizer extends Job{
		private Set<EObject> emfChanges;
		private INakedModelWorkspace nakedModelWorspace;
		private Set<INakedNameSpace> nakedUmlChanges;
		private Map<String,NamespaceRenameRequest> renamedRequestsByNewName;
		private UmlCacheListener updater;
		public EmfToNakedUmlSynchronizer(Set<EObject> emfChanges,INakedModelWorkspace nakedModelWorspace,Set<INakedNameSpace> nakedUmlChanges2,
				Map<String,NamespaceRenameRequest> renamedRequestsByNewName,UmlCacheListener umlModelUpdator){
			super("Synchronizing Opium Metadata with UML");
			this.emfChanges = emfChanges;
			this.nakedModelWorspace = nakedModelWorspace;
			this.nakedUmlChanges = nakedUmlChanges2;
			this.renamedRequestsByNewName = renamedRequestsByNewName;
			this.updater = umlModelUpdator;
		}
		public IStatus run(IProgressMonitor monitor){
			try{
				monitor.beginTask("Synchronizing Opium Metadata", 50);
				synchronized(emfChanges){
					if(emfChanges.size() > 0){
						Set<INakedNameSpace> nakedClassifiers = new HashSet<INakedNameSpace>();
						Set<Classifier> emfClassifiers = findClassifiers(emfChanges);
						Set<EObject> asdf = new HashSet<EObject>(emfChanges);
						emfChanges.clear();
						long start = System.currentTimeMillis();
						for(Object object:getTransformationProcess().processElements(asdf, EmfExtractionPhase.class, new ProgressMonitorTransformationLog(monitor, 50))){
							if(object instanceof INakedElement){
								INakedElement ne = (INakedElement) object;
								if(isLocalJavaRename(ne) && couldBeReferencedFromOcl(ne)){
									updater.updateOclReferencesTo(ne);
								}
								while(!(ne instanceof INakedClassifier || ne instanceof INakedRootObject || ne == null)){
									ne = (INakedElement) ne.getOwnerElement();
								}
								if(ne instanceof INakedNameSpace){
									nakedClassifiers.add((INakedNameSpace) ne);
									if(ne instanceof INakedBehavior && ((INakedBehavior) ne).getContext() != null){
										nakedClassifiers.add(((INakedBehavior) ne).getContext());
									}
								}
								if(ne instanceof INakedClassifier || ne instanceof INakedPackage){
									maybeAddRenameRequest((INakedNameSpace) ne);
								}
							}
						}
						for(Classifier classifier:emfClassifiers){
							INakedElement peer = nakedModelWorspace.getModelElement(resourceHelper.getId(classifier));
							if(peer instanceof INakedClassifier){
								nakedClassifiers.add((INakedClassifier) peer);
							}
						}
						for(INakedNameSpace iNakedNameSpace:nakedClassifiers){
							if(nakedModelWorspace.isPrimaryModel(iNakedNameSpace.getRootObject())){
								nakedUmlChanges.add(iNakedNameSpace);
							}
						}
						updater.synchronizationComplete(asdf, nakedUmlChanges);
						System.out.println("Synchronization took " + (System.currentTimeMillis() - start));
					}
				}
				return new Status(IStatus.OK, NakedUmlPlugin.getId(), "Opium Metadata Synchronized Successfully");
			}catch(Exception e){
				e.printStackTrace();
				return new Status(IStatus.ERROR, NakedUmlPlugin.getId(), "Synchronization Failed", e);
			}finally{
				monitor.done();
			}
		}
		private boolean couldBeReferencedFromOcl(INakedElement ne){
			return ne instanceof INakedPin || ne instanceof INakedOperation || ne instanceof INakedClassifier || ne instanceof INakedParameter
					|| ne instanceof INakedProperty;
		}
		private boolean isLocalJavaRename(INakedElement ne){
			return ne.getMappingInfo().getJavaName() != null && !ne.getMappingInfo().getJavaName().equals(ne.getMappingInfo().getOldJavaName());
		}
		private void maybeAddRenameRequest(INakedNameSpace ne){
			// NB!!! this has to be done here in case multiple renames occurred before synchronization with java source
			if(ne.getMappingInfo().requiresJavaRename()){
				// Optimize namespace renames to minimize need for other models to be recompiled
				if(ne instanceof INakedClassifier){
					addRenameRequest(ne, ne.getMappingInfo().getOldQualifiedJavaName().toLowerCase(), ne.getMappingInfo().getQualifiedJavaName().toLowerCase());
				}
				addRenameRequest(ne, ne.getMappingInfo().getOldQualifiedJavaName(), ne.getMappingInfo().getQualifiedJavaName());
			}
		}
		private void addRenameRequest(INakedNameSpace ne,String oldName,String newName){
			NamespaceRenameRequest prev = renamedRequestsByNewName.get(oldName);
			if(prev != null){
				// just update the previous rename request
				prev.newName = ne.getMappingInfo().getQualifiedJavaName();
				renamedRequestsByNewName.remove(ne.getMappingInfo().getOldQualifiedJavaName());
			}else{
				prev = new NamespaceRenameRequest(ne.getMappingInfo().getOldQualifiedJavaName(), ne.getMappingInfo().getQualifiedJavaName());
			}
			renamedRequestsByNewName.put(newName, prev);
		}
		private Set<Classifier> findClassifiers(Set<EObject> changes){
			Set<Classifier> result = new HashSet<Classifier>();
			for(EObject eObject:changes){
				if(eObject instanceof Classifier){
					result.add((Classifier) eObject);
				}
			}
			return result;
		}
	}
	private UmlCacheListener umlModelUpdator;
	NakedUmlElementLinker linker = new NakedUmlElementLinker();
	EclipseUmlElementCache(NakedUmlConfig cfg,UmlCacheListener umlModelUpdator){
		super(new EclipseEmfResourceHelper(), cfg);
		this.umlModelUpdator = umlModelUpdator;
	}
	protected void scheduleSynchronization(){
		EmfToNakedUmlSynchronizer synchronizer = new EmfToNakedUmlSynchronizer(emfChanges, nakedModelWorspace, nakedUmlChanges, renamedRequestsByNewName, umlModelUpdator);
		synchronizer.schedule();
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
		linker.notifyChanged(notification);
		super.notifyChanged(notification);
	}
	@Override
	public void reinitializeProcess(NakedUmlConfig cfg){
		super.reinitializeProcess(cfg);
	}
	@Override
	public void emfWorkspaceLoaded(EmfWorkspace w){
		for(Package pkg:w.getPrimaryModels()){
			if(pkg instanceof Model){
				Model model = (Model) pkg;
				Profile pf = ApplyProfileAction.applyNakedUmlProfile(model);
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
}