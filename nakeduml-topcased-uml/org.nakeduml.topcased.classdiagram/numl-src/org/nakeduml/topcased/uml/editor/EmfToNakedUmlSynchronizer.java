package org.nakeduml.topcased.uml.editor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.sf.nakeduml.emf.extraction.EmfExtractionPhase;
import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.emf.workspace.UriToFileConverter;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.Steps;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.feature.TransformationProcess.TransformationProgressLog;
import net.sf.nakeduml.linkage.AbstractModelElementLinker;
import net.sf.nakeduml.linkage.LinkagePhase;
import net.sf.nakeduml.linkage.QualifierLogicCalculator;
import net.sf.nakeduml.linkage.SourcePopulationResolver;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.metamodel.workspace.internal.NakedModelWorkspaceImpl;
import net.sf.nakeduml.validation.ValidationPhase;
import net.sf.nakeduml.validation.namegeneration.JavaNameRegenerator;
import net.sf.nakeduml.validation.namegeneration.PersistentNameGenerator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.resource.UMLResourceImpl;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.nakeduml.eclipse.ProfileApplier;
import org.topcased.modeler.utils.ResourceUtils;

public final class EmfToNakedUmlSynchronizer extends EContentAdapter{
	private static ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(1);
	protected TransformationProcess transformationProcess;
	protected NakedUmlConfig cfg;
	protected Set<EObject> emfChanges = Collections.synchronizedSet(new HashSet<EObject>());
	protected INakedModelWorkspace nakedModelWorspace;
	protected UriToFileConverter resourceHelper;
	protected EmfWorkspace currentEmfWorkspace;
	long lastChange = System.currentTimeMillis();
	protected Set<UMLResource> resourcesBeingLoaded = new HashSet<UMLResource>();
	private Set<UMLResource> resourcesLoaded = new HashSet<UMLResource>();
	boolean suspended = false;
	private Set<NakedUmlSynchronizationListener> synchronizationListener = new HashSet<NakedUmlSynchronizationListener>();
	NakedUmlElementLinker linker = new NakedUmlElementLinker();
	public EmfToNakedUmlSynchronizer(NakedUmlConfig cfg){
		this.resourceHelper = new EclipseUriToFileConverter();
		this.cfg = cfg;
		reinitializeProcess();
	}
	public void addSynchronizationListener(NakedUmlSynchronizationListener l){
		this.synchronizationListener.add(l);
	}
	public void suspend(){
		suspended = true;
	}
	public void resumeAndCatchUp(){
		suspended = false;
		scheduleSynchronization();
	}
	public void resume(){
		suspended = false;
		emfChanges.clear();
	}
	protected void scheduleSynchronization(){
		if(!suspended){
			SynchronizationProcessRunner synchronizer = new SynchronizationProcessRunner(getTransformationProcess(), synchronizationListener, emfChanges);
			synchronizer.schedule();
		}
	}
	protected void synchronizationNow(final Set<Package> packages){
		if(!suspended){
			Display.getDefault().syncExec(new Runnable(){
				@Override
				public void run(){
					ProgressMonitorDialog dlg = new ProgressMonitorDialog(Display.getDefault().getActiveShell());
					IProgressMonitor pm = dlg.getProgressMonitor();
					dlg.open();
					try{
						pm.beginTask("Loading new Packages", 50);
						new SynchronizationProcessRunner(getTransformationProcess(), synchronizationListener, packages).run(pm);
						pm.done();
					}finally{
						dlg.close();
						pm.done();
					}
				}
			});
		}
	}
	public void emfWorkspaceLoaded(EmfWorkspace w){
		for(Package model:w.getPrimaryModels()){
			if(model instanceof Model || model instanceof Profile){
				if(!ResourceUtils.isReadOnly(model.eResource())){
					EMap<String,String> d = StereotypesHelper.getNumlAnnotation(model).getDetails();
					if(d.get("uuid") == null || d.get("uuid").trim().length() == 0){
						d.put("uuid", Math.round(Math.random() * 1000000) + "");
					}
				}
				if(model instanceof Model){
					Profile pf = ProfileApplier.applyNakedUmlProfile((Model) model);
					Stereotype modelStereotype = pf.getOwnedStereotype(StereotypeNames.MODEL);
					if(StereotypesHelper.hasStereotype(model, StereotypeNames.MODEL_LIBRARY)){
						modelStereotype = pf.getOwnedStereotype(StereotypeNames.MODEL_LIBRARY);
					}
					if(!model.isStereotypeApplied(modelStereotype)){
						model.applyStereotype(modelStereotype);
					}
					if(model.getValue(modelStereotype, "mappedImplementationPackage") == null){
						model.setValue(modelStereotype, "mappedImplementationPackage", cfg.getMavenGroupId() + "." + model.getName().toLowerCase());
					}
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
			nws.addPrimaryModel((INakedRootObject) nws.getModelElement(e.getId(package1)));
		}
		for(Package g:e.getGeneratingModelsOrProfiles()){
			nws.addGeneratingRootObject((INakedRootObject) nws.getModelElement(e.getId(g)));
		}
		this.currentEmfWorkspace = e;
	}
	public EmfWorkspace getCurrentEmfWorkspace(){
		return currentEmfWorkspace;
	}
	public UriToFileConverter getResourceHelper(){
		return resourceHelper;
	}
	public void reinitializeProcess(){
		this.transformationProcess = new TransformationProcess();
		cfg.reset();
		this.transformationProcess.initialize(cfg, getTransformationSteps());
		this.nakedModelWorspace = new NakedModelWorkspaceImpl();
		this.transformationProcess.replaceModel(nakedModelWorspace);
	}
	public EmfWorkspace buildWorkspaces(Package model,TransformationProgressLog log) throws Exception,IOException{
		EcoreUtil.resolveAll(model.eResource().getResourceSet());
		EmfWorkspace emfWorkspace = new EmfWorkspace(model, this.cfg.getWorkspaceMappingInfo(), cfg.getWorkspaceIdentifier());
		emfWorkspace.setUriToFileConverter(new EclipseUriToFileConverter());
		emfWorkspace.setName(cfg.getWorkspaceName());
		emfWorkspaceLoaded(emfWorkspace);
		this.currentEmfWorkspace = emfWorkspace;
		this.transformationProcess.replaceModel(emfWorkspace);
		this.transformationProcess.execute(log);
		return emfWorkspace;
	}
	@SuppressWarnings("unchecked")
	protected HashSet<Class<? extends ITransformationStep>> getTransformationSteps(){
		HashSet<Class<? extends ITransformationStep>> result = new HashSet<Class<? extends ITransformationStep>>(Arrays.asList(StereotypeApplicationExtractor.class,
				JavaNameRegenerator.class, PersistentNameGenerator.class));
		Set<Class<? extends AbstractModelElementLinker>> allSteps = LinkagePhase.getAllSteps();
		// TODO ignore linkage steps as they will be included from Javatransformations
		allSteps.remove(SourcePopulationResolver.class);
		allSteps.remove(QualifierLogicCalculator.class);
		result.addAll(allSteps);
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
	@Override
	public void notifyChanged(final Notification notification){
		if(!suspended && resourcesBeingLoaded.isEmpty()){
			linker.notifyChanged(notification);
		}
		if(notification.getEventType() == Notification.ADD || notification.getEventType() == Notification.ADD_MANY || notification.getEventType() == Notification.SET){
			final boolean annotationCreated = notification.getNotifier() instanceof EModelElement
					&& EcorePackage.eINSTANCE.getEModelElement_EAnnotations().equals(notification.getFeature());
			if(!annotationCreated){
				if(notification.getNotifier() instanceof UMLResource){
					manageResourceEvent(notification);
				}else if(!resourcesBeingLoaded.isEmpty()){
					if(notification.getNewValue() instanceof EObject){
						EObject newValue = (EObject) notification.getNewValue();
						if(newValue.eIsProxy()){
							EcoreUtil.resolve(newValue, currentEmfWorkspace.getResourceSet());
							// broken references
						}
					}
				}else if(notification.getNotifier() instanceof DynamicEObjectImpl){
					DynamicEObjectImpl sa = (DynamicEObjectImpl) notification.getNotifier();
					for(EReference eReference:sa.eClass().getEReferences()){
						if(eReference.getEType().eContainer() instanceof UMLPackage){
							// Reference to UML element - check if it is a stereotype for this one
							Object e = sa.eGet(eReference);
							if(e instanceof Element && ((Element) e).getStereotypeApplications().contains(sa)){
								scheduleSynchronization((Element) e);
							}
						}
					}
				}else{
					if(notification.getNotifier() instanceof Element){
						EObject o = null;
						if(notification.getEventType() == Notification.ADD && notification.getFeature() instanceof EReference
								&& ((EReference) notification.getFeature()).isContainment() && notification.getNewValue() instanceof Element){
							// new object
							o = (EObject) notification.getNewValue();
							scheduleSynchronization(o);
						}
						if(notification.getNotifier() instanceof EObject){
							o = (EObject) notification.getNotifier();
							scheduleSynchronization(o);
						}
					}
				}
			}
		}else if(notification.getEventType() == Notification.REMOVE || notification.getEventType() == Notification.REMOVE_MANY){
			if(notification.getFeature() instanceof EReference && ((EReference) notification.getFeature()).isContainment()
					&& notification.getOldValue() instanceof Element){
				// Deletion
				final Element oldValue = (Element) notification.getOldValue();
				final Resource eResource = ((EObject) notification.getNotifier()).eResource();
				storeTempId(oldValue, eResource);
				scheduleSynchronization(oldValue);
			}else if(notification.getNotifier() instanceof EObject){
				scheduleSynchronization((EObject) notification.getNotifier());
			}
		}
		super.notifyChanged(notification);
	}
	private void storeTempId(final Element ne,final Resource eResource){
		final StringBuilder uriFragment = new StringBuilder();
		// STore it temporarily for EmfWorkspace.getId()
		new UMLResourceImpl(null){
			{
				uriFragment.append(DETACHED_EOBJECT_TO_ID_MAP.get(ne));
			}
		};
		final String id = currentEmfWorkspace.getResourceId(eResource) + "@" + uriFragment.toString();
		StereotypesHelper.getNumlAnnotation(ne).getDetails().put("opiumId", id);
		for(Element element:ne.getOwnedElements()){
			storeTempId(element, eResource);
		}
	}
	public void manageResourceEvent(final Notification notification){
		if(notification.getNewValue() instanceof Package && notification.getFeatureID(UMLResource.class) == UMLResource.RESOURCE__CONTENTS){
			this.resourcesBeingLoaded.add((UMLResource) notification.getNotifier());
			EcoreUtil.resolveAll((UMLResource) notification.getNotifier());
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
	private void scheduleSynchronization(EObject o){
		lastChange = System.currentTimeMillis();
		this.emfChanges.add(o);
		threadPool.schedule(new Runnable(){
			@Override
			public void run(){
				if(System.currentTimeMillis() - lastChange >= 200){
					scheduleSynchronization();
				}
			}
		}, 200, TimeUnit.MILLISECONDS);
	}
	public static void sheduleTask(Runnable r,long l){
		threadPool.schedule(r, l, TimeUnit.MILLISECONDS);
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
	public TransformationProcess getTransformationProcess(){
		return transformationProcess;
	}
}