package org.nakeduml.topcased.uml.editor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.emf.extraction.EmfExtractionPhase;
import net.sf.nakeduml.emf.workspace.UmlElementCache.NamespaceRenameRequest;
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
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Classifier;
import org.nakeduml.eclipse.ProgressMonitorTransformationLog;
import org.nakeduml.topcased.uml.NakedUmlPlugin;

public final class EmfToNakedUmlSynchronizer extends Job{
	/**
	 * 
	 */
	private final EclipseUmlElementCache eclipseUmlElementCache;
	private Set<EObject> emfChanges;
	private INakedModelWorkspace nakedModelWorspace;
	private Set<INakedNameSpace> nakedUmlChanges;
	private Map<String,NamespaceRenameRequest> renamedRequestsByNewName;
	private UmlCacheListener updater;
	public EmfToNakedUmlSynchronizer(EclipseUmlElementCache eclipseUmlElementCache, Set<EObject> emfChanges,INakedModelWorkspace nakedModelWorspace,Set<INakedNameSpace> nakedUmlChanges2,
			Map<String,NamespaceRenameRequest> renamedRequestsByNewName,UmlCacheListener umlModelUpdator){
		super("Synchronizing Opium Metadata with UML");
		this.eclipseUmlElementCache = eclipseUmlElementCache;
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
					for(Object object:this.eclipseUmlElementCache.getTransformationProcess().processElements(asdf, EmfExtractionPhase.class, new ProgressMonitorTransformationLog(monitor, 50))){
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
						INakedElement peer = nakedModelWorspace.getModelElement(this.eclipseUmlElementCache.getResourceHelper().getId(classifier));
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