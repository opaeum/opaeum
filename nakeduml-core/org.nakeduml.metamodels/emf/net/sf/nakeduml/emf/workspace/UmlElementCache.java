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
import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.emf.load.EmfWorkspaceLoader;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.linkage.ClassDependencyCalculator;
import net.sf.nakeduml.linkage.CompositionEmulator;
import net.sf.nakeduml.linkage.LinkagePhase;
import net.sf.nakeduml.linkage.MappedTypeLinker;
import net.sf.nakeduml.linkage.NakedParsedOclStringResolver;
import net.sf.nakeduml.linkage.PinLinker;
import net.sf.nakeduml.linkage.ProcessIdentifier;
import net.sf.nakeduml.linkage.RootEntityLinker;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.metamodel.workspace.internal.NakedModelWorkspaceImpl;
import net.sf.nakeduml.validation.ValidationPhase;
import net.sf.nakeduml.validation.namegeneration.JavaNameRegenerator;
import net.sf.nakeduml.validation.namegeneration.PersistentNameGenerator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLPackage;

public class UmlElementCache extends EContentAdapter{
	public static class RenamedNamespace{
		private String oldName;
		private String newName;
		public RenamedNamespace(String oldName,String newName){
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
	private Set<INakedClassifier> modifiedClasses = new HashSet<INakedClassifier>();
	private Map<String,RenamedNamespace> renamedNamespacesByNewName = new HashMap<String,RenamedNamespace>();
	private static ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(1);
	protected TransformationProcess transformationProcess;
	protected NakedUmlConfig cfg;
	private Set<NamedElement> changes = Collections.synchronizedSet(new HashSet<NamedElement>());
	private boolean loadingNewResource;
	private INakedModelWorkspace nakedModelWorspace;
	protected EmfResourceHelper resourceHelper;
	protected EmfWorkspace currentEmfWorkspace;
	public UmlElementCache(EmfResourceHelper helper,NakedUmlConfig cfg){
		this.cfg = cfg;
		this.resourceHelper = helper;
		this.transformationProcess = new TransformationProcess();
		this.transformationProcess.initialize(cfg, getTransformationSteps());
		this.nakedModelWorspace=new NakedModelWorkspaceImpl();
		this.transformationProcess.replaceModel(nakedModelWorspace);
	}
	public EmfWorkspace buildWorkspaces(ResourceSet resourceSet,File modelFile) throws Exception,IOException{
		EmfWorkspace emfWorkspace = EmfWorkspaceLoader.loadSingleModelWorkspace(resourceSet, modelFile, cfg);
		emfWorkspaceLoaded(emfWorkspace);
		emfWorkspace.setResourceHelper(this.resourceHelper);
		this.currentEmfWorkspace = emfWorkspace;
		this.transformationProcess.execute(cfg, emfWorkspace, getTransformationSteps());
		this.nakedModelWorspace = transformationProcess.findModel(INakedModelWorkspace.class);
		return emfWorkspace;
	}
	protected HashSet<Class<? extends ITransformationStep>> getTransformationSteps(){
		HashSet<Class<? extends ITransformationStep>> result = new HashSet<Class<? extends ITransformationStep>>(Arrays.asList(StereotypeApplicationExtractor.class,
				NakedParsedOclStringResolver.class, ProcessIdentifier.class, MappedTypeLinker.class, ClassDependencyCalculator.class, PinLinker.class,
				RootEntityLinker.class, CompositionEmulator.class, JavaNameRegenerator.class, PersistentNameGenerator.class));
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
	private boolean isSynchronizable(EObject e){
		return e instanceof Action || e instanceof Property || e instanceof Operation || e instanceof Classifier || e instanceof State || e instanceof Transition
				|| e instanceof ActivityEdge;
	}
	@Override
	public void notifyChanged(final Notification notification){
		super.notifyChanged(notification);
		if(notification.getEventType() == Notification.ADD || notification.getEventType() == Notification.SET){
			if(notification.getFeatureID(EAnnotation.class) != EcorePackage.EMODEL_ELEMENT__EANNOTATIONS){
				if(notification.getNotifier() instanceof ResourceSet && notification.getFeatureID(ResourceSet.class) == ResourceSet.RESOURCE_SET__RESOURCES){
					Resource r = (Resource) notification.getNewValue();
					if(r.getContents().size() > 0 && r.getContents().get(0) instanceof Package){
						org.eclipse.uml2.uml.Package pjg = (org.eclipse.uml2.uml.Package) r.getContents().get(0);
						loadPackage(pjg);
					}
				}else if(!this.loadingNewResource){
					if((notification.getNotifier() instanceof Classifier || notification.getNotifier() instanceof Package)
							&& notification.getFeatureID(Namespace.class) == UMLPackage.NAMESPACE__NAME){
						Namespace ns = (Namespace) notification.getNotifier();
						INakedNameSpace nns = (INakedNameSpace) nakedModelWorspace.getModelElement(resourceHelper.getId(ns));
						if(nns != null && notification.getOldStringValue() != null){
							String oldName = JavaNameRegenerator.packagePathname(nns);
							nns.setName(ns.getName());
							String newName = JavaNameRegenerator.packagePathname(nns);
							if(!oldName.equals(newName)){
								RenamedNamespace prev = renamedNamespacesByNewName.get(oldName);
								if(prev != null){
									prev.newName = newName;
									renamedNamespacesByNewName.remove(oldName);
								}else{
									prev = new RenamedNamespace(oldName, newName);
								}
								renamedNamespacesByNewName.put(newName, prev);
							}
						}
					}
					if(notification.getNotifier() instanceof Element){
						EObject o = notification.getEventType() == Notification.ADD ? (EObject) notification.getNewValue() : (EObject) notification.getNotifier();
						while(!(isSynchronizable(o) || o == null)){
							o = getContainer(o);
						}
						if(o instanceof NamedElement && o.eContainer() != null){
							scheduleSynchronization(o);
						}
					}
				}
			}
		}else if(notification.getEventType() == Notification.REMOVE){
			if(notification.getOldValue() instanceof NamedElement){
				scheduleSynchronization((NamedElement) notification.getOldValue());
			}
		}
		if(notification.getEventType() == Notification.ADD){
			maybePut((EObject) notification.getNewValue());
		}
	}
	protected void loadPackage(final org.eclipse.uml2.uml.Package pjg){
		loadingNewResource = true;
		EcoreUtil.resolveAll(pjg);
		getTransformationProcess().processElements(Collections.singleton(pjg), EmfExtractionPhase.class);
		loadingNewResource = false;
	}
	protected void scheduleSynchronization(EObject o){
		this.changes.add((NamedElement) o);
		threadPool.schedule(new Runnable(){
			public void run(){
				try{
					HashSet<INakedClassifier> classifiers = new HashSet<INakedClassifier>();
					HashSet<Object> asdf = new HashSet<Object>(changes);
					changes.clear();
					for(Object object:getTransformationProcess().processElements(asdf, EmfExtractionPhase.class)){
						if(object instanceof INakedElement){
							INakedElement ne = (INakedElement) object;
							while(!(ne instanceof INakedClassifier || ne instanceof INakedRootObject)){
								ne = (INakedElement) ne.getOwnerElement();
							}
							if(ne instanceof INakedClassifier){
								classifiers.add((INakedClassifier) ne);
							}
						}
					}
					for(INakedClassifier c:classifiers){
						modifiedClasses.add(c);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}, 400, TimeUnit.MILLISECONDS);
	}
	protected EObject getContainer(EObject o){
		if(o instanceof Event && o.eContainer() instanceof org.eclipse.uml2.uml.Package){
			for(EObject eObject:StereotypesHelper.getNumlAnnotation((Element) o).getReferences()){
				if(eObject instanceof Trigger){
					return eObject;
				}
			}
		}
		return o.eContainer();
	}
	public static void sheduleTask(Runnable r,long l){
		threadPool.schedule(r, l, TimeUnit.MILLISECONDS);
	}
	public Set<INakedClassifier> getModifiedClasses(){
		return modifiedClasses;
	}
	public void clearModifiedClass(){
		modifiedClasses.clear();
	}
	public Set<RenamedNamespace> getRenamedNamespaces(){
		return new HashSet<RenamedNamespace>(this.renamedNamespacesByNewName.values());
	}
	public void afterSave(){
		modifiedClasses.clear();
		renamedNamespacesByNewName.clear();
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
		this.renamedNamespacesByNewName.clear();
	}
	public TransformationProcess getTransformationProcess(){
		return transformationProcess;
	}
}
