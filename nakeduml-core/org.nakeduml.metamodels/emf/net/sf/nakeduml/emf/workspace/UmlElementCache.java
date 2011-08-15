package net.sf.nakeduml.emf.workspace;

import java.io.File;
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
import net.sf.nakeduml.emf.load.EmfWorkspaceLoader;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.linkage.CompositionEmulator;
import net.sf.nakeduml.linkage.DependencyCalculator;
import net.sf.nakeduml.linkage.EnumerationValuesAttributeAdder;
import net.sf.nakeduml.linkage.InverseCalculator;
import net.sf.nakeduml.linkage.LinkagePhase;
import net.sf.nakeduml.linkage.MappedTypeLinker;
import net.sf.nakeduml.linkage.NakedParsedOclStringResolver;
import net.sf.nakeduml.linkage.ObjectFlowLinker;
import net.sf.nakeduml.linkage.ParameterLinker;
import net.sf.nakeduml.linkage.PinLinker;
import net.sf.nakeduml.linkage.ProcessIdentifier;
import net.sf.nakeduml.linkage.QualifierLogicCalculator;
import net.sf.nakeduml.linkage.ReferenceResolver;
import net.sf.nakeduml.linkage.RootEntityLinker;
import net.sf.nakeduml.linkage.SourcePopulationResolver;
import net.sf.nakeduml.linkage.TypeResolver;
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
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.metamodel.workspace.internal.NakedModelWorkspaceImpl;
import net.sf.nakeduml.validation.ValidationPhase;
import net.sf.nakeduml.validation.namegeneration.JavaNameRegenerator;
import net.sf.nakeduml.validation.namegeneration.PersistentNameGenerator;
import nl.klasse.octopus.oclengine.internal.OclContextImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

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
	private UMLResource newResourceBeingLoaded;
	private Set<EObject> resourceChanges = new HashSet<EObject>();
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
	public EmfWorkspace buildWorkspaces(ResourceSet resourceSet,File modelFile) throws Exception,IOException{
		EmfWorkspace emfWorkspace = EmfWorkspaceLoader.loadSingleModelWorkspace(resourceSet, modelFile, cfg);
		emfWorkspaceLoaded(emfWorkspace);
		emfWorkspace.setResourceHelper(this.resourceHelper);
		this.currentEmfWorkspace = emfWorkspace;
		this.transformationProcess.replaceModel(emfWorkspace);
		this.transformationProcess.execute();
		return emfWorkspace;
	}
	protected HashSet<Class<? extends ITransformationStep>> getTransformationSteps(){
		HashSet<Class<? extends ITransformationStep>> result = new HashSet<Class<? extends ITransformationStep>>(Arrays.asList(StereotypeApplicationExtractor.class,
				NakedParsedOclStringResolver.class, ProcessIdentifier.class, MappedTypeLinker.class, DependencyCalculator.class, PinLinker.class, RootEntityLinker.class,
				CompositionEmulator.class, JavaNameRegenerator.class, PersistentNameGenerator.class, TypeResolver.class, SourcePopulationResolver.class,
				ReferenceResolver.class, QualifierLogicCalculator.class, ParameterLinker.class, ObjectFlowLinker.class, InverseCalculator.class,
				EnumerationValuesAttributeAdder.class));
		for(Class<? extends ITransformationStep> stepClass:cfg.getAdditionalTransformationSteps()){
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
				}else if(newResourceBeingLoaded != null && notification.getNewValue() instanceof EObject){
					EObject newValue = (EObject) notification.getNewValue();
					if(newValue.eIsProxy()){
						EcoreUtil.resolve(newValue, newResourceBeingLoaded);
						// broken references
					}else{
						this.resourceChanges.add(newValue);
					}
				}else if(notification.getNotifier() instanceof DynamicEObjectImpl){
					DynamicEObjectImpl sa = (DynamicEObjectImpl) notification.getNotifier();
					for(EReference eReference:sa.eClass().getEReferences()){
						if(eReference.getEType().eContainer() instanceof UMLPackage){
							// Reference to UML element - check if it is a stereotype for this one
							Element e = (Element) sa.eGet(eReference);
							if(e.getStereotypeApplications().contains(sa)){
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
							scheduleSynchronization((Element) o);
						}
					}
				}
			}
		}else if(notification.getEventType() == Notification.REMOVE){
			if(notification.getOldValue() instanceof NamedElement){
				NamedElement ne = (NamedElement)notification.getOldValue();
//				if(this.emfChanges.contains(ne)){
//					;
//				}
				scheduleSynchronization(ne);
			}
		}
		if(notification.getEventType() == Notification.ADD && notification.getNewValue() instanceof EObject){
			maybePut((EObject) notification.getNewValue());
		}
	}
	private void registerNameSpaceNameChange(final Notification notification){
	}
	public void manageResourceEvent(final Notification notification){
		if(notification.getNewValue() instanceof Package && notification.getFeatureID(UMLResource.class) == UMLResource.RESOURCE__CONTENTS){
			this.newResourceBeingLoaded = (UMLResource) notification.getNotifier();
			resourceChanges.add((EObject) notification.getNewValue());
		}else if(notification.getFeatureID(UMLResource.class) == UMLResource.RESOURCE__IS_LOADED && notification.getNewBooleanValue() == true){
			// Do this synchronously
			Set<EObject> asdf = new HashSet<EObject>(resourceChanges);
			resourceChanges.clear();
			getTransformationProcess().processElements(asdf, EmfExtractionPhase.class);
			this.newResourceBeingLoaded = null;
		}
	}
	protected void scheduleSynchronization(Element o){
		this.emfChanges.add((Element) o);
		threadPool.schedule(newNakedUmlSynchronizer(), 400, TimeUnit.MILLISECONDS);
	}
	protected Runnable newNakedUmlSynchronizer(){
		return new Runnable(){
			@Override
			public void run(){
			}
		};
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
