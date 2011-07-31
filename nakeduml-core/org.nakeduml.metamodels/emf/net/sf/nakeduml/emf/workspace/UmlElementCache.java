package net.sf.nakeduml.emf.workspace;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.sf.nakeduml.emf.extraction.AbstractExtractorFromEmf;
import net.sf.nakeduml.emf.extraction.EmfExtractionPhase;
import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.emf.load.EmfWorkspaceLoader;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.linkage.NakedParsedOclStringResolver;
import net.sf.nakeduml.linkage.ProcessIdentifier;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.validation.namegeneration.JavaNameRegenerator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
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
import org.eclipse.uml2.uml.resource.UMLResource;

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
	private BlockingQueue<INakedClassifier> modifiedClasses = new LinkedBlockingQueue<INakedClassifier>();
	private BlockingQueue<RenamedNamespace> renamedNamespaces = new LinkedBlockingQueue<RenamedNamespace>();
	private static ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(1);
	public static interface Selector{
		boolean select(Object o);
	}
	private static Map<ResourceSet,UmlElementCache> instances = new WeakHashMap<ResourceSet,UmlElementCache>();
	private Selector selector;
	Map<String,Element> map = new HashMap<String,Element>();
	private TransformationProcess transformationProcess;
	public TransformationProcess getTransformationProcess(){
		return transformationProcess;
	}
	private EmfWorkspace emfWorkspace;
	private ResourceSet resourceSet;
	private NakedUmlConfig cfg;
	private Set<NamedElement> changes = Collections.synchronizedSet(new HashSet<NamedElement>());
	private boolean loadingNewResource;
	private INakedModelWorkspace nakedModelWorspace;
	private UmlElementCache(ResourceSet rst,Selector sel){
		this.resourceSet = rst;
		this.selector = sel;
		instances.put(rst, this);
	}
	public UmlElementCache(EmfWorkspace emfWorkspace,Selector sel){
		this(emfWorkspace.getResourceSet(), sel);
		this.emfWorkspace = emfWorkspace;
	}
	public void startSynchronizing(File modelFile, NakedUmlConfig cfg) throws Exception,IOException{
		this.cfg=cfg;
		this.transformationProcess = new TransformationProcess();
		emfWorkspace = EmfWorkspaceLoader.loadSingleModelWorkspace(resourceSet, modelFile, "tempWorkspace");
		cfg.load(new File(modelFile.getParentFile(), "nakeduml.properties"), "tempWorkspace");
		this.transformationProcess.execute(cfg, emfWorkspace, getTransformationSteps());
		this.nakedModelWorspace = transformationProcess.findModel(INakedModelWorkspace.class);
		loadContents();
		this.resourceSet.eAdapters().add(this);
	}
	public void loadContents(){
		TreeIterator<Notifier> allContents = resourceSet.getAllContents();
		while(allContents.hasNext()){
			Notifier eObject = (Notifier) allContents.next();
			maybePut(eObject);
		}
	}
	protected HashSet<Class<? extends TransformationStep>> getTransformationSteps(){
		return new HashSet<Class<? extends TransformationStep>>(Arrays.asList(StereotypeApplicationExtractor.class, NakedParsedOclStringResolver.class,
				ProcessIdentifier.class));
	}
	public UmlElementCache(ResourceSet rst) throws Exception{
		this(rst, new Selector(){
			public boolean select(Object eObject){
				if(eObject instanceof Element){
					return true;
				}else{
					return false;
				}
			}
		});
	}
	private void maybePut(Object object){
		if(selector.select(object)){
			Element element = (Element) object;
			map.put(getId(element), element);
			map.put(AbstractExtractorFromEmf.getId(element), element);
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
				if(notification.getEventType() == Notification.ADD && notification.getNotifier() instanceof UMLResource
						&& notification.getFeatureID(UMLResource.class) == UMLResource.RESOURCE__CONTENTS
						&& notification.getNewValue() instanceof org.eclipse.uml2.uml.Package){
					org.eclipse.uml2.uml.Package pjg = (org.eclipse.uml2.uml.Package) notification.getNewValue();
					loadPackage(pjg);
				}else if(!this.loadingNewResource){
					if((notification.getNotifier() instanceof Classifier || notification.getNotifier() instanceof Package)
							&& notification.getFeatureID(Namespace.class) == UMLPackage.NAMESPACE__NAME){
						Namespace ns = (Namespace) notification.getNotifier();
						INakedNameSpace nns = (INakedNameSpace) nakedModelWorspace.getModelElement(getId(ns));
						if(nns != null && notification.getOldStringValue() != null){
							String oldName = JavaNameRegenerator.packagePathname(nns);
							nns.setName(ns.getName());
							String newName = JavaNameRegenerator.packagePathname(nns);
							renamedNamespaces.offer(new RenamedNamespace(oldName, newName));
						}
					}
					if(notification.getNotifier() instanceof Element){
						EObject o = (EObject) notification.getNotifier();
						while(!(isSynchronizable(o) || o == null)){
							o = getContainer(o);
						}
						if(o instanceof NamedElement && o.eContainer() != null){
							scheduleSynchronization(o);
						}
					}
				}
			}
		}
		if(notification.getEventType() == Notification.ADD){
			maybePut(notification.getNewValue());
		}
	}
	protected void loadPackage(final org.eclipse.uml2.uml.Package pjg){
		threadPool.schedule(new Runnable(){
			public void run(){
				loadingNewResource = true;
				EcoreUtil.resolveAll(pjg);
				getTransformationProcess().processElements(Collections.singleton(pjg), EmfExtractionPhase.class);
				loadingNewResource = false;
			}
		}, 0, TimeUnit.MILLISECONDS);
	}
	protected void scheduleSynchronization(EObject o){
		this.changes.add((NamedElement) o);
		threadPool.schedule(new Runnable(){
			public void run(){
				try{
					HashSet<INakedClassifier> classifiers = new HashSet<INakedClassifier>();
					HashSet<Object> asdf = new HashSet<Object>( changes);
					changes.clear();
					for(Object object:getTransformationProcess().processElements(asdf, EmfExtractionPhase.class)){
						if(object instanceof INakedElement){
							INakedElement ne=(INakedElement) object;
							while(!(ne instanceof INakedClassifier || ne instanceof INakedRootObject)){
								ne=(INakedElement) ne.getOwnerElement();
							}
							if(ne instanceof INakedClassifier){
								classifiers.add((INakedClassifier) ne);
							}
						}
					}
					for(INakedClassifier c:classifiers){
						modifiedClasses.offer(c);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}

			protected INakedClassifier getNakedClassifier(INakedElement object){
				return (INakedClassifier) object;
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
	public static String getId(Element umlElement){
		if(umlElement instanceof EmfWorkspace){
			return ((EmfWorkspace) umlElement).getName();
		}else{
			String uid = null;
			EAnnotation ann = umlElement.getEAnnotation(StereotypeNames.NUML_ANNOTATION);
			if(ann == null){
				ann = umlElement.createEAnnotation(StereotypeNames.NUML_ANNOTATION);
			}
			if(ann == null){
				// not in editable resource,but the filename and fragment would be stable and unique
				Resource eResource = umlElement.eResource();
				URI uri = eResource.getURI();
				uid = uri.lastSegment() + eResource.getURIFragment(umlElement);
			}else{
				uid = ann.getDetails().get("uid");
				if(uid == null){
					char[] a = UUID.randomUUID().toString().toCharArray();
					for(int i = 0;i < a.length;i++){
						if(!Character.isJavaIdentifierPart(a[i])){
							a[i] = '_';
						}
					}
					uid = new String(a);
					ann.getDetails().put("uid", uid);
				}
			}
			return uid;
		}
	}
	public Element getElement(String umlElementUid){
		return map.get(umlElementUid);
	}
	public EmfWorkspace getEmfWorkspace(){
		return emfWorkspace;
	}
	public static void sheduleTask(Runnable r,long l){
		threadPool.schedule(r, l, TimeUnit.MILLISECONDS);
	}
	public BlockingQueue<INakedClassifier> getModifiedClasses(){
		return modifiedClasses;
	}
	public BlockingQueue<RenamedNamespace> getRenamedNamespaces(){
		return renamedNamespaces;
	}
	public INakedModelWorkspace getNakedWorkspace(){
		return nakedModelWorspace;
	}
	public NakedUmlConfig getConfig(){
		return this.cfg;
	}
}
