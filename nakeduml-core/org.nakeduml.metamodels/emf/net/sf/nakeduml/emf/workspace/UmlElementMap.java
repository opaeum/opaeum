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
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.sf.nakeduml.emf.extraction.AbstractExtractorFromEmf;
import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.emf.load.EmfWorkspaceLoader;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.linkage.NakedParsedOclStringResolver;
import net.sf.nakeduml.linkage.ProcessIdentifier;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;

public class UmlElementMap extends EContentAdapter{
	ScheduledThreadPoolExecutor ex = new ScheduledThreadPoolExecutor(1);
	public static interface Selector{
		boolean select(Object o);
	}
	private static Map<ResourceSet,UmlElementMap> instances = new WeakHashMap<ResourceSet,UmlElementMap>();
	private Selector selector;
	Map<String,Element> map = new HashMap<String,Element>();
	private TransformationProcess transformationProcess;
	public TransformationProcess getTransformationProcess(){
		return transformationProcess;
	}
	private EmfWorkspace emfWorkspace;
	private ResourceSet resourceSet;
	private NakedUmlConfig cfg;
	private Set<Classifier> changes = Collections.synchronizedSet(new HashSet<Classifier>());
	private UmlElementMap(ResourceSet rst,Selector sel){
		this.resourceSet = rst;
		this.selector = sel;
		instances.put(rst, this);
	}
	public UmlElementMap(EmfWorkspace emfWorkspace,Selector sel){
		this(emfWorkspace.getResourceSet(), sel);
		this.emfWorkspace = emfWorkspace;
	}
	public void startSynchronizing(File modelFile) throws Exception,IOException{
		this.transformationProcess = new TransformationProcess();
		emfWorkspace = EmfWorkspaceLoader.loadSingleModelWorkspace(resourceSet, modelFile, "tempWorkspace");
		this.cfg = new NakedUmlConfig();
		cfg.load(new File(modelFile.getParentFile(), "nakeduml.properties"), "tempWorkspace");
		this.transformationProcess.execute(cfg, emfWorkspace, getTransformationSteps());
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
	public UmlElementMap(ResourceSet rst) throws Exception{
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
	@Override
	public void notifyChanged(final Notification notification){
		super.notifyChanged(notification);
		if(notification.getEventType() == Notification.ADD || notification.getEventType() == Notification.SET){
			if(notification.getFeatureID(EAnnotation.class) != EcorePackage.EMODEL_ELEMENT__EANNOTATIONS){
				if(notification.getNotifier() instanceof Element){
					EObject o = (EObject) notification.getNotifier();
					while(!(o instanceof Classifier || o == null)){
						if(o instanceof Event && o.eContainer() instanceof org.eclipse.uml2.uml.Package){
							for(EObject eObject:StereotypesHelper.getNumlAnnotation((Element) o).getReferences()){
								if(eObject instanceof Trigger){
									o = eObject;
									break;
								}
							}
						}else{
							o = o.eContainer();
						}
					}
					if(o instanceof Classifier){
						this.changes.add((Classifier) o);
						this.ex.schedule(new Runnable(){
							public void run(){
								try{
									HashSet<Classifier> cls = new HashSet<Classifier>(changes);
									changes.clear();
									for(Classifier classifier:cls){
										getTransformationProcess().processSingleElement(cfg, emfWorkspace, getTransformationSteps(), classifier);
									}
								}catch(Exception e){
									e.printStackTrace();
								}
							}
						}, 400, TimeUnit.MILLISECONDS);
					}
				}
			}
		}
		if(notification.getEventType() == Notification.ADD){
			maybePut(notification.getNewValue());
		}
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
}
