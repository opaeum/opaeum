package net.sf.nakeduml.emf.workspace;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.sf.nakeduml.emf.extraction.EmfExtractionPhase;
import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.Steps;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.feature.TransformationProcess.TransformationProgressLog;
import net.sf.nakeduml.linkage.LinkagePhase;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.metamodel.workspace.internal.NakedModelWorkspaceImpl;
import net.sf.nakeduml.validation.ValidationPhase;
import net.sf.nakeduml.validation.namegeneration.JavaNameRegenerator;
import net.sf.nakeduml.validation.namegeneration.PersistentNameGenerator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.nakeduml.eclipse.EmfElementFinder;

//TODO refactor out of existence
public class UmlElementCache extends EContentAdapter{
	public static class NamespaceRenameRequest{
		public String oldName;
		public String newName;
		public NamespaceRenameRequest(String oldName,String newName){
			super();
			this.oldName = oldName;
			this.newName = newName;
		}
		public String getOldName(){
			return oldName;
		}
		public String getNewName(){
			return newName;
		}
	}
	protected Set<INakedNameSpace> nakedUmlChanges = Collections.synchronizedSet(new HashSet<INakedNameSpace>());
	protected Map<String,NamespaceRenameRequest> renamedRequestsByNewName = Collections.synchronizedMap(new HashMap<String,NamespaceRenameRequest>());
	private static ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(1);
	protected TransformationProcess transformationProcess;
	protected NakedUmlConfig cfg;
	protected Set<EObject> emfChanges = Collections.synchronizedSet(new HashSet<EObject>());
	protected INakedModelWorkspace nakedModelWorspace;
	protected EmfResourceHelper resourceHelper;
	protected EmfWorkspace currentEmfWorkspace;
	long lastChange = System.currentTimeMillis();
	private Set<UMLResource> resourcesBeingLoaded = new HashSet<UMLResource>();
	private Set<UMLResource> resourcesLoaded = new HashSet<UMLResource>();
	// private Set<EObject> resourceChanges = new HashSet<EObject>();
	public UmlElementCache(EmfResourceHelper helper,NakedUmlConfig cfg){
		this.resourceHelper = helper;
		reinitializeProcess(cfg);
	}
	public void reinitializeProcess(NakedUmlConfig cfg){
		this.cfg = cfg;
		this.transformationProcess = new TransformationProcess();
		this.transformationProcess.initialize(cfg, getTransformationSteps());
		this.nakedModelWorspace = new NakedModelWorkspaceImpl();
		this.transformationProcess.replaceModel(nakedModelWorspace);
		this.nakedUmlChanges.clear();
	}
	public EmfWorkspace buildWorkspaces(Package model,TransformationProgressLog log) throws Exception,IOException{
		EcoreUtil.resolveAll(model);
		EmfWorkspace emfWorkspace = new EmfWorkspace(model, this.cfg.getWorkspaceMappingInfo(), cfg.getWorkspaceIdentifier());
		emfWorkspace.setResourceHelper(this.resourceHelper);
		emfWorkspaceLoaded(emfWorkspace);
		this.currentEmfWorkspace = emfWorkspace;
		this.transformationProcess.replaceModel(emfWorkspace);
		this.transformationProcess.execute(log);
		return emfWorkspace;
	}
	protected HashSet<Class<? extends ITransformationStep>> getTransformationSteps(){
		HashSet<Class<? extends ITransformationStep>> result = new HashSet<Class<? extends ITransformationStep>>(Arrays.asList(StereotypeApplicationExtractor.class,
				JavaNameRegenerator.class, PersistentNameGenerator.class));
		result.addAll(LinkagePhase.getAllSteps());
		result.addAll(ValidationPhase.getAllValidationSteps());
		result.add(JavaNameRegenerator.class);
		result.add(PersistentNameGenerator.class);
		Set<Class<? extends ITransformationStep>> additionalTransformationSteps = cfg.getAdditionalTransformationSteps();
		Steps steps = new Steps();
		steps.initializeFromClasses(additionalTransformationSteps);
		for(Class<? extends ITransformationStep> stepClass:steps.getExecutionUnitClasses()){
			if(stepClass.isAnnotationPresent(StepDependency.class)){
				Class<? extends TransformationPhase<? extends ITransformationStep,?>> phase = stepClass.getAnnotation(StepDependency.class).phase();
				if(phase == LinkagePhase.class || phase == ValidationPhase.class || phase == EmfExtractionPhase.class){
					result.add(stepClass);
				}
			}
		}
		return result;
	}
	public void emfWorkspaceLoaded(EmfWorkspace w){
	}
	protected void maybePut(EObject object){
		if(object instanceof Element){
			Element element = (Element) object;
			currentEmfWorkspace.putElement(element);
		}
	}
	@Override
	public void notifyChanged(final Notification notification){
		super.notifyChanged(notification);

		if(notification.getEventType() == Notification.ADD || notification.getEventType() == Notification.SET){
			if(notification.getFeatureID(EAnnotation.class) != EcorePackage.EMODEL_ELEMENT__EANNOTATIONS){
				if(notification.getNotifier() instanceof UMLResource){
					manageResourceEvent(notification);
				}else if(!resourcesBeingLoaded.isEmpty() && notification.getNewValue() instanceof EObject){
					EObject newValue = (EObject) notification.getNewValue();
					if(newValue.eIsProxy()){
						EcoreUtil.resolve(newValue, currentEmfWorkspace.getResourceSet());
						// broken references
					}
				}else if(notification.getNotifier() instanceof DynamicEObjectImpl){
					DynamicEObjectImpl sa = (DynamicEObjectImpl) notification.getNotifier();
					for(EReference eReference:sa.eClass().getEReferences()){
						if(eReference.getEType().eContainer() instanceof UMLPackage){
							// Reference to UML element - check if it is a stereotype for this one
							Element e = (Element) sa.eGet(eReference);
							if(e != null && e.getStereotypeApplications().contains(sa)){
								scheduleSynchronization(e);
							}
						}
					}
				}else{
					if((notification.getNotifier() instanceof Classifier || notification.getNotifier() instanceof Package)
							&& notification.getFeatureID(Namespace.class) == UMLPackage.NAMESPACE__NAME && notification.getOldStringValue() != null){
						registerNameSpaceNameChange(notification);
					}
					if(notification.getNotifier() instanceof Element){
						EObject o = null;
						if(notification.getEventType() == Notification.ADD && notification.getNewValue() instanceof EObject){
							o = (EObject) notification.getNewValue();
						}else if(notification.getNotifier() instanceof EObject){
							o = (EObject) notification.getNotifier();
						}
						if(o != null && o instanceof Element && o.eContainer() != null){
							Element syncronizableElement = getSyncronizableElement((Element) o);
							scheduleSynchronization(syncronizableElement);
						}
					}
				}
			}
		}else if(notification.getEventType() == Notification.REMOVE){
			if(notification.getOldValue() instanceof NamedElement){
				NamedElement ne = (NamedElement) notification.getOldValue();
				// if(this.emfChanges.contains(ne)){
				// ;
				// }
				if(!isSynchronizableElement(ne)){
					EObject e = (EObject) notification.getNotifier();
					ne = (NamedElement) getSyncronizableElement(e);
				}
				if(ne != null){
					scheduleSynchronization(ne);
				}
			}
		}
		if(resourcesBeingLoaded.isEmpty() && notification.getEventType() == Notification.ADD && notification.getNewValue() instanceof EObject){
			maybePut((EObject) notification.getNewValue());
		}
	}
	private void registerNameSpaceNameChange(final Notification notification){
	}
	public void manageResourceEvent(final Notification notification){
		if(notification.getNewValue() instanceof Package && notification.getFeatureID(UMLResource.class) == UMLResource.RESOURCE__CONTENTS){
			this.resourcesBeingLoaded.add((UMLResource) notification.getNotifier());
		}else if(notification.getFeatureID(UMLResource.class) == UMLResource.RESOURCE__IS_LOADED && notification.getNewBooleanValue() == true){
			// Do this synchronously
			this.resourcesLoaded.add((UMLResource) notification.getNotifier());
			if(resourcesLoaded.containsAll(resourcesBeingLoaded)){
				Set<Package> newObjects = new HashSet<Package>();
				for(UMLResource umlResource:resourcesLoaded){
					for(EObject eObject:umlResource.getContents()){
						if(eObject instanceof Model || eObject instanceof Profile){
							newObjects.add((Package) eObject);
						}
					}
				}
				synchronizationNow(newObjects);
				this.resourcesBeingLoaded.clear();
				this.resourcesLoaded.clear();
			}
		}
	}
	protected void scheduleSynchronization(){
	}
	protected void synchronizationNow(Set<Package> packages){
	}
	private void scheduleSynchronization(Element o){
		synchronized(nakedModelWorspace){
			lastChange = System.currentTimeMillis();
			this.emfChanges.add(o);
			if(o instanceof Association){
				this.emfChanges.addAll(((Association) o).getMemberEnds());
			}
			threadPool.schedule(new Runnable(){
				@Override
				public void run(){
					if(System.currentTimeMillis() - lastChange >= 200){
						scheduleSynchronization();
					}
				}
			}, 200, TimeUnit.MILLISECONDS);
		}
	}
	private Element getSyncronizableElement(EObject e2){
		EObject e = e2;
		while(e != null){
			if(isSynchronizableElement(e)){
				return (Element) e;
			}else{
				e = EmfElementFinder.getContainer(e);
			}
		}
		return (Element) e;
	}
	private boolean isSynchronizableElement(EObject e){
		return e instanceof Action || e instanceof ControlNode || e instanceof State || e instanceof Pseudostate || e instanceof StructuredActivityNode
				|| e instanceof Region || e instanceof Operation || e instanceof Property || e instanceof Classifier || e instanceof Transition
				|| e instanceof ActivityEdge || e instanceof Package || e instanceof Association || e instanceof Generalization || e instanceof InterfaceRealization;
	}
	public static void sheduleTask(Runnable r,long l){
		threadPool.schedule(r, l, TimeUnit.MILLISECONDS);
	}
	public Set<INakedNameSpace> getModifiedClasses(){
		return nakedUmlChanges;
	}
	public void clearModifiedClass(){
		nakedUmlChanges.clear();
	}
	public Set<NamespaceRenameRequest> getRenamedNamespaces(){
		return new HashSet<NamespaceRenameRequest>(this.renamedRequestsByNewName.values());
	}
	public void afterSave(){
		nakedUmlChanges.clear();
		renamedRequestsByNewName.clear();
	}
	public INakedModelWorkspace getNakedWorkspace(){
		return nakedModelWorspace;
	}
	public NakedUmlConfig getConfig(){
		return this.cfg;
	}
	public static void schedule(Runnable task,long delay){
		threadPool.schedule(task, delay, TimeUnit.MILLISECONDS);
	}
	public void clearRenamedNamespaces(){
		this.renamedRequestsByNewName.clear();
	}
	public TransformationProcess getTransformationProcess(){
		return transformationProcess;
	}
}
