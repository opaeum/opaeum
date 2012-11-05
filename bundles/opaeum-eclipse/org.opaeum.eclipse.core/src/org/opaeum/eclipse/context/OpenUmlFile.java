package org.opaeum.eclipse.context;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.internal.resource.UMLResourceImpl;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.opaeum.eclipse.EclipseUriToFileConverter;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.OpaeumElementLinker;
import org.opaeum.eclipse.OpaeumScheduler;
import org.opaeum.eclipse.OpaeumSynchronizationListener;
import org.opaeum.eclipse.ProgressMonitorTransformationLog;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.emf.workspace.UriToFileConverter;
import org.opaeum.feature.DefaultTransformationLog;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.Steps;
import org.opaeum.feature.TransformationPhase;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.SourcePopulationResolver;
import org.opaeum.validation.AbstractValidator;
import org.opaeum.validation.ValidationPhase;
import org.opaeum.validation.activities.ActionValidation;

@SuppressWarnings("restriction")
public class OpenUmlFile extends EContentAdapter{
	public static Map<EObject,String> DETACHED_EOBJECT_TO_ID_MAP;
	static{
		new UMLResourceImpl(null){
			{
				OpenUmlFile.DETACHED_EOBJECT_TO_ID_MAP = UMLResourceImpl.DETACHED_EOBJECT_TO_ID_MAP;
			}
		};
	}
	private EmfWorkspace emfWorkspace;
	private EditingDomain editingDomain;
	private Package model;
	private IFile file;
	private boolean dirty;
	private TransformationProcess transformationProcess;
	private OpaeumConfig cfg;
	private Set<EObject> emfChanges = Collections.synchronizedSet(new HashSet<EObject>());
	private UriToFileConverter resourceHelper;
	private long lastChange = System.currentTimeMillis();
	private Set<UMLResource> resourcesBeingLoaded = new HashSet<UMLResource>();
	private Set<UMLResource> resourcesLoaded = new HashSet<UMLResource>();
	private boolean suspended = false;
	private Set<OpaeumSynchronizationListener> synchronizationListener = new HashSet<OpaeumSynchronizationListener>();
	private OpaeumElementLinker linker = new OpaeumElementLinker();
	private OJUtil ojUtil;
	private TypeCacheAdapter typeCacheAdapter;
	private EObjectSelectorUI eObjectSelectorUI;
	private Adapter additionalContentAdapter;
	public OpenUmlFile(EditingDomain editingDomain,IFile f,OpaeumConfig cfg){
		super();
		Package model = findRootObjectInFile(f, editingDomain.getResourceSet());
		this.setEditingDomain(editingDomain);
		this.setModel(model);
		this.setFile(f);
		this.resourceHelper = new EclipseUriToFileConverter();
		this.cfg = cfg;
		this.ojUtil = new OJUtil();
		emfWorkspace = new EmfWorkspace(model, this.cfg.getVersion(), cfg.getWorkspaceIdentifier(), cfg.getMavenGroupId());
		emfWorkspace.setUriToFileConverter(new EclipseUriToFileConverter());
		emfWorkspace.setName(cfg.getWorkspaceName());
		this.transformationProcess = new TransformationProcess();
		this.transformationProcess.initialize(cfg, getTransformationSteps(cfg));
		this.transformationProcess.replaceModel(ojUtil);
		this.transformationProcess.replaceModel(emfWorkspace);
		this.transformationProcess.execute(new DefaultTransformationLog());
		editingDomain.getResourceSet().eAdapters().add(this);
		typeCacheAdapter = new TypeCacheAdapter();
	}
	public TypeCacheAdapter getTypeCacheAdapter(){
		return typeCacheAdapter;
	}
	public void addEmfChange(URI uri){
		EObject eObject = emfWorkspace.getResourceSet().getEObject(uri, true);
		synchronized(emfChanges){
			emfChanges.add(eObject);
		}
	}
	public void addSynchronizationListener(OpaeumSynchronizationListener l){
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
			ValidationRunner synchronizer = new ValidationRunner();
			synchronizer.schedule();
		}
	}
	protected void synchronizationNow(final Set<Package> packages){
		if(!suspended){
			Display.getDefault().syncExec(new Runnable(){
				@Override
				public void run(){
					// ProgressMonitorDialog dlg = new ProgressMonitorDialog(Display.getDefault().getActiveShell());
					// IProgressMonitor pm = dlg.getProgressMonitor();
					// dlg.open();
					try{
						// pm.beginTask("Loading new Packages", 50);
						new ValidationRunner().run(new NullProgressMonitor());
						// pm.done();
					}finally{
						// dlg.close();
						// pm.done();
					}
				}
			});
		}
	}
	public UriToFileConverter getResourceHelper(){
		return resourceHelper;
	}
	@SuppressWarnings("unchecked")
	public static HashSet<Class<? extends ITransformationStep>> getTransformationSteps(OpaeumConfig cfg){
		HashSet<Class<? extends ITransformationStep>> result = new HashSet<Class<? extends ITransformationStep>>(
				Arrays.asList(ActionValidation.class));
		Set<Class<? extends AbstractValidator>> allSteps = ValidationPhase.getAllValidationSteps();
		if(cfg.shouldBeCm1Compatible()){
			// TODO ignore linkage steps as they will be included from Javatransformations
			allSteps.remove(SourcePopulationResolver.class);
		}
		result.addAll(allSteps);
		Set<Class<? extends ITransformationStep>> additionalTransformationSteps = cfg.getAdditionalTransformationSteps();
		Steps steps = new Steps();
		steps.initializeFromClasses(additionalTransformationSteps);
		for(Class<? extends ITransformationStep> stepClass:steps.getExecutionUnitClasses()){
			if(stepClass.isAnnotationPresent(StepDependency.class)){
				Class<? extends TransformationPhase<? extends ITransformationStep,?>> phase = stepClass.getAnnotation(StepDependency.class).phase();
				if(phase == ValidationPhase.class){
					result.add(stepClass);
				}
			}
		}
		return result;
	}
	@Override
	public void notifyChanged(final Notification notification){
		super.notifyChanged(notification);
		if(notification.getNotifier() instanceof ResourceSet && notification.getNewValue() instanceof UMLResource){
			resourcesBeingLoaded.add((UMLResource) notification.getNewValue());
		}else if(notification.getNotifier() instanceof UMLResource){
			manageResourceEvent(notification);
		}
		if(!suspended && resourcesBeingLoaded.isEmpty()){
			linker.notifyChanged(notification);
			if(additionalContentAdapter != null){
				additionalContentAdapter.notifyChanged(notification);
			}
			if(notification.getEventType() == Notification.ADD || notification.getEventType() == Notification.ADD_MANY
					|| notification.getEventType() == Notification.SET){
				final boolean annotationCreated = notification.getNotifier() instanceof EModelElement
						&& EcorePackage.eINSTANCE.getEModelElement_EAnnotations().equals(notification.getFeature());
				if(!(annotationCreated)){
					if(notification.getNotifier() instanceof DynamicEObjectImpl){
						scheduleSynchronization((Element) UMLUtil.getBaseElement((EObject) notification.getNotifier()));
					}else{
						if(notification.getNotifier() instanceof Element){
							EObject o = null;
							if(notification.getEventType() == Notification.ADD && notification.getFeature() instanceof EReference
									&& ((EReference) notification.getFeature()).isContainment() && notification.getNewValue() instanceof Element){
								// new object
								o = (EObject) notification.getNewValue();
								scheduleSynchronization(o);
							}else if(notification.getNotifier() instanceof EObject){
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
				}else if(notification.getNotifier() instanceof DynamicEObjectImpl){
					scheduleSynchronization((Element) UMLUtil.getBaseElement((EObject) notification.getNotifier()));
				}else if(notification.getNotifier() instanceof EObject){
					scheduleSynchronization((EObject) notification.getNotifier());
				}
			}
		}
		typeCacheAdapter.notifyChanged(notification);
	}
	private void storeTempId(final Element ne,final Resource eResource){
		final StringBuilder uriFragment = new StringBuilder();
		// STore it temporarily for EmfWorkspace.getId()
		uriFragment.append(DETACHED_EOBJECT_TO_ID_MAP.get(ne));
		final String id = EmfWorkspace.getResourceId(eResource) + "@" + uriFragment.toString();
		StereotypesHelper.findOrCreateNumlAnnotation(ne).getDetails().put("opaeumId", id);
		for(Element element:EmfElementFinder.getCorrectOwnedElements(ne)){
			storeTempId(element, eResource);
		}
	}
	private void manageResourceEvent(final Notification notification){
		int featureID = notification.getFeatureID(UMLResource.class);
		if(featureID == UMLResource.RESOURCE__IS_LOADED){
			if(notification.getNewBooleanValue() == true){
				EcoreUtil.resolveAll((UMLResource) notification.getNotifier());
				// Do this synchronously
				this.resourcesLoaded.add((UMLResource) notification.getNotifier());
				if(resourcesBeingLoaded.size() > 0 && resourcesLoaded.containsAll(resourcesBeingLoaded)){
					Set<Package> newObjects = new HashSet<Package>();
					for(UMLResource umlResource:resourcesLoaded.toArray(new UMLResource[resourcesLoaded.size()])){// avoid concurrentmod
						for(EObject eObject:umlResource.getContents()){
							if((eObject instanceof Model || eObject instanceof Profile) && EmfWorkspace.isUtilzedModel((Package) eObject)){
								newObjects.add((Package) eObject);
							}
						}
					}
					this.resourcesBeingLoaded.clear();
					this.resourcesLoaded.clear();
					synchronizationNow(newObjects);
				}
			}
		}
	}
	private void scheduleSynchronization(EObject o){
		lastChange = System.currentTimeMillis();
		this.emfChanges.add(o);
		this.dirty = true;
		OpaeumScheduler.schedule(new Runnable(){
			@Override
			public void run(){
				if(System.currentTimeMillis() - lastChange >= 100){
					scheduleSynchronization();
				}
			}
		}, 100);
	}
	public EmfWorkspace getEmfWorkspace(){
		return emfWorkspace;
	}
	void setEmfWorkspace(EmfWorkspace emfWorkspace){
		this.emfWorkspace = emfWorkspace;
	}
	public EditingDomain getEditingDomain(){
		return editingDomain;
	}
	void setEditingDomain(EditingDomain editingDomain){
		this.editingDomain = editingDomain;
	}
	public Package getModel(){
		return model;
	}
	void setModel(Package model){
		this.model = model;
	}
	public IFile getFile(){
		return file;
	}
	private void setFile(IFile file){
		this.file = file;
	}
	public boolean isDirty(){
		return dirty;
	}
	public void setDirty(boolean dirty){
		this.dirty = dirty;
	}
	public TransformationProcess getTransformationProcess(){
		return transformationProcess;
	}
	public void removeSynchronizationListener(OpaeumSynchronizationListener editingContext){
		this.synchronizationListener.remove(editingContext);
	}
	public void release(){
		for(OpaeumSynchronizationListener l:this.synchronizationListener){
			l.onClose(this);
		}
		this.synchronizationListener.clear();
		ojUtil = null;
		emfWorkspace.getResourceSet().eAdapters().remove(this);
		emfWorkspace = null;
		this.editingDomain = null;
		this.transformationProcess = null;
	}
	private Package findRootObjectInFile(IResource r,ResourceSet rs){
		EList<Resource> ownedElements = rs.getResources();
		for(Resource element:ownedElements){
			URI uri = element.getURI();
			if(uri.trimFileExtension().lastSegment().equals(r.getLocation().removeFileExtension().lastSegment())){
				for(EObject eObject:element.getContents()){
					if(eObject instanceof Model || eObject instanceof Package){
						return (Package) eObject;
					}
				}
			}
		}
		return null;
	}
	public OJUtil getOJUtil(){
		return this.ojUtil;
	}
	private class ValidationRunner extends Job{
		private Set<EObject> emfChanges;
		public ValidationRunner(){
			super("Executing Opaeum validations");
			synchronized(OpenUmlFile.this.emfChanges){
				this.emfChanges = new HashSet<EObject>(OpenUmlFile.this.emfChanges);
				OpenUmlFile.this.emfChanges.clear();
			}
		}
		public IStatus run(final IProgressMonitor monitor){
			try{
				new AbstractCommand(){
					@Override
					public boolean canExecute(){
						return true;
					}
					@Override
					public void execute(){
						monitor.beginTask("Executing Opaeum validations", 50);
						if(emfChanges.size() > 0){
							long start = System.currentTimeMillis();
							Set<Element> changedElements = new HashSet<Element>();
							for(Object object:transformationProcess.processElements(emfChanges, ValidationPhase.class,
									new ProgressMonitorTransformationLog(monitor, 50))){
								if(object instanceof Element){
									changedElements.add(EmfElementFinder.getNearestClassifier((Element) object));
								}
							}
							OpaeumSynchronizationListener[] array = synchronizationListener
									.toArray(new OpaeumSynchronizationListener[synchronizationListener.size()]);
							for(OpaeumSynchronizationListener listener:array){
								listener.synchronizationComplete(OpenUmlFile.this, changedElements);
							}
							OpaeumEclipsePlugin.logInfo("Validation took " + (System.currentTimeMillis() - start));
						}
					}
					@Override
					public void redo(){
						// TODO Auto-generated method stub
					}
				}.execute();
				return new Status(IStatus.OK, OpaeumEclipsePlugin.getId(), "Opaeum validations executed");
			}catch(Exception e){
				e.printStackTrace();
				return new Status(IStatus.ERROR, OpaeumEclipsePlugin.getId(), "Opaeum validations not executed!", e);
			}finally{
				monitor.done();
			}
		}
	}
	public void addContextListener(OpaeumSynchronizationListener javaSourceSynchronizer){
		this.synchronizationListener.add(javaSourceSynchronizer);
	}
	public void onSave(IProgressMonitor monitor){
		for(OpaeumSynchronizationListener l:this.synchronizationListener){
			if(l instanceof OpaeumEclipseContextListener){
				((OpaeumEclipseContextListener) l).onSave(monitor, this);
			}
		}
	}
	public OpaeumConfig getConfig(){
		return this.cfg;
	}
	public EObjectSelectorUI geteObjectSelectorUI(){
		return eObjectSelectorUI;
	}
	public void seteObjectSelectorUI(EObjectSelectorUI eObjectSelectorUI){
		this.eObjectSelectorUI = eObjectSelectorUI;
	}
	public Adapter getAdditionalContentAdapter(){
		return additionalContentAdapter;
	}
	public void setAdditionalContentAdapter(Adapter additionalContentAdapter){
		this.additionalContentAdapter = additionalContentAdapter;
	}
	public void executeAndForget(final Command command){
		TxUtil.performExecute(command, null, getEditingDomain());
	}
	@Deprecated
	public void executeAndWait(final Command command){
		EditingDomain editingDomain2 = getEditingDomain();
		TxUtil.executeAndWait(command, editingDomain2);
	}
	public void dispose(){
		for(OpaeumSynchronizationListener l:this.synchronizationListener){
			l.onClose(this);
		}
	}
}