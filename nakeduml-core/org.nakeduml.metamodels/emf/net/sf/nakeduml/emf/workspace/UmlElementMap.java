package net.sf.nakeduml.emf.workspace;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Transition;

public class UmlElementMap extends EContentAdapter{
	static interface Selector{
		boolean select(Object o);
	}
	
	private static Map<ResourceSet,UmlElementMap> instances=new WeakHashMap<ResourceSet,UmlElementMap>();
	private Selector selector;
	Map<String,Element> map = new HashMap<String,Element>();
	public UmlElementMap(ResourceSet rst, Selector sel){
		this.selector=sel;
		TreeIterator<Notifier> allContents = rst.getAllContents();
		while(allContents.hasNext()){
			Notifier eObject = (Notifier) allContents.next();
			maybePut(eObject);
		}
		rst.eAdapters().add(this);
		instances.put(rst, this);
		
	}
	public UmlElementMap(ResourceSet rst){
		this(rst,new Selector(){
			public  boolean select(Object eObject){
				return eObject instanceof Property || eObject instanceof Operation || eObject instanceof Parameter || eObject instanceof OpaqueAction || eObject instanceof Pin
						|| eObject instanceof State || eObject instanceof Transition || eObject instanceof org.eclipse.uml2.uml.Package || eObject instanceof Classifier;
			}
		});
	}
	public static UmlElementMap getInstance(ResourceSet rst){
		UmlElementMap map = instances.get(rst);
		if(map==null){
			return new UmlElementMap(rst);
		}
		return map;
	}
	private void maybePut(Object object){
		if(selector.select(object)){
			Element element = (Element) object;
			map.put(getId(element), element);
		}
	}
	@Override
	public void notifyChanged(Notification notification){
		super.notifyChanged(notification);
		if(notification.getEventType()==Notification.ADD ){
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
}
