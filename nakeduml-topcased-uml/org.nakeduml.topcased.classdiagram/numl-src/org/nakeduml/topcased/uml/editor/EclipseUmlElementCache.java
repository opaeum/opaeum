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
import net.sf.nakeduml.metamodel.actions.INakedOclAction;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.oclengine.internal.OclContextImpl;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.nakeduml.eclipse.ApplyProfileAction;
import org.nakeduml.eclipse.ProgressMonitorTransformationLog;

public final class EclipseUmlElementCache extends UmlElementCache{
	public final class EmfToNakedUmlSynchronizer implements Runnable{
		private Set<EObject> emfChanges;
		private INakedModelWorkspace nakedModelWorspace;
		private Set<INakedNameSpace> nakedUmlChanges;
		private Map<String,NamespaceRenameRequest> renamedRequestsByNewName;
		private UmlCacheListener updater;
		
		public EmfToNakedUmlSynchronizer(Set<EObject> emfChanges,INakedModelWorkspace nakedModelWorspace,Set<INakedNameSpace> nakedUmlChanges2,
				Map<String,NamespaceRenameRequest> renamedRequestsByNewName,UmlCacheListener umlModelUpdator){
			super();
			this.emfChanges = emfChanges;
			this.nakedModelWorspace = nakedModelWorspace;
			this.nakedUmlChanges = nakedUmlChanges2;
			this.renamedRequestsByNewName = renamedRequestsByNewName;
			this.updater = umlModelUpdator;
		}
		public void run(){
			try{
				if(emfChanges.size() > 0){
					Set<INakedNameSpace> nakedClassifiers = new HashSet<INakedNameSpace>();
					Set<Classifier> emfClassifiers = findClassifiers(emfChanges);
					Set<EObject> asdf = new HashSet<EObject>(emfChanges);
					emfChanges.clear();
					for(Object object:getTransformationProcess().processElements(asdf, EmfExtractionPhase.class)){
						if(object instanceof INakedElement){
							INakedElement ne = (INakedElement) object;
							if(isLocalJavaRename(ne) && couldBeReferencedFromOcl(ne)){
								updater.updateOclReferencesTo(ne);
							}
							while(!(ne instanceof INakedClassifier || ne instanceof INakedRootObject || ne ==null)){
								ne = (INakedElement) ne.getOwnerElement();
							}
							if(ne instanceof INakedNameSpace){
								nakedClassifiers.add((INakedNameSpace) ne);
							}
							if(ne instanceof INakedClassifier || ne instanceof INakedPackage){
								maybeAddRenameRequest((INakedNameSpace) ne);
							}
						}
					}
					for(Classifier classifier:emfClassifiers){
						nakedClassifiers.add((INakedClassifier) nakedModelWorspace.getModelElement(resourceHelper.getId(classifier)));
					}
					for(INakedNameSpace iNakedNameSpace:nakedClassifiers){
						if(nakedModelWorspace.isPrimaryModel(iNakedNameSpace.getRootObject())){
							nakedUmlChanges.add(iNakedNameSpace);
						}
					}
					updater.synchronizationComplete(asdf, nakedUmlChanges);
				}
			}catch(Exception e){
				e.printStackTrace();
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
	private IProgressMonitor monitor;
	private UmlCacheListener umlModelUpdator;
	NakedUmlElementLinker linker=new NakedUmlElementLinker();
	EclipseUmlElementCache(NakedUmlConfig cfg,UmlCacheListener umlModelUpdator){
		super(new EclipseEmfResourceHelper(), cfg);
		this.umlModelUpdator = umlModelUpdator;
	}
	@Override
	public void notifyChanged(Notification notification){
		linker.notifyChanged(notification);
		super.notifyChanged(notification);
	}
	public void setMonitor(IProgressMonitor monitor){
		getTransformationProcess().setLog(new ProgressMonitorTransformationLog(monitor));
		this.monitor = monitor;
	}
	protected Runnable newNakedUmlSynchronizer(){
		return new EmfToNakedUmlSynchronizer(emfChanges, nakedModelWorspace, nakedUmlChanges, renamedRequestsByNewName, umlModelUpdator);
	}
	@Override
	public void emfWorkspaceLoaded(EmfWorkspace w){
		monitor.worked(15);
		for(Package pkg:w.getPrimaryModels()){
			if(pkg instanceof Model){
				Model model = (Model) pkg;
				Profile pf = ApplyProfileAction.applyNakedUmlProfile(model);
				Stereotype st = pf.getOwnedStereotype(StereotypeNames.PACKAGE);
				if(!model.isStereotypeApplied(st)){
					model.applyStereotype(st);
				}
				if(model.getValue(st, "mappedImplementationPackage") == null){
					model.setValue(st, "mappedImplementationPackage", cfg.getMavenGroupId() + "." + model.getName().toLowerCase());
				}
				try{
					model.eResource().save(new HashMap());
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
		Package generatingModel = e.getGeneratingModelsOrProfiles().iterator().next();// Should be exactly one
		nws.addGeneratingRootObject((INakedRootObject) nws.getModelElement(resourceHelper.getId(generatingModel)));
		this.currentEmfWorkspace = e;
	}
	public EmfWorkspace getCurrentEmfWorkspace(){
		return currentEmfWorkspace;
	}
}