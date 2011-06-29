package org.nakeduml.uim.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.TypedElement;
import org.nakeduml.eclipse.EmfElementFinder;
import org.nakeduml.uim.UmlReference;
import org.nakeduml.uim.action.OperationAction;
import org.nakeduml.uim.action.TransitionAction;
import org.nakeduml.uim.binding.PropertyRef;
import org.nakeduml.uim.binding.UimBinding;
import org.nakeduml.uim.folder.AbstractFolder;
import org.nakeduml.uim.folder.OperationContainingFolder;
import org.nakeduml.uim.form.ActionTaskForm;
import org.nakeduml.uim.form.ClassForm;
import org.nakeduml.uim.form.OperationInvocationForm;
import org.nakeduml.uim.form.OperationTaskForm;
import org.nakeduml.uim.form.StateForm;

public class UmlUimLinks extends EContentAdapter{
	@Override
	public void notifyChanged(final Notification not){
		if(not.getEventType() == Notification.ADD){
		}
	}
	private boolean umlLoaded;
	public boolean umlLoaded(){
		return this.umlLoaded;
	}
	private boolean uimLoaded;
	public boolean uimLoaded(){
		return this.uimLoaded;
	}
	private static Map<URI,UmlUimLinks> instances = new HashMap<URI,UmlUimLinks>();
	Map<String,UmlUimLink> links = new HashMap<String,UmlUimLink>();
	public static void init(ResourceSet rs){
		UmlUimLinks l = null;
		outer:for(Resource r:rs.getResources()){
			if(!r.getURI().toString().contains("uml2")){
				for(Entry<URI,UmlUimLinks> entry:instances.entrySet()){
					if(entry.getKey().trimFileExtension().trimSegments(1).equals(r.getURI().trimFileExtension().trimSegments(1))){
						// same directory
						l = entry.getValue();
						break outer;
					}else if(entry.getKey().fileExtension().equals("uim") && r.getURI().fileExtension().equals("uml")){
						// entry has matching uim file for uml file
						if(entry.getKey().trimFileExtension().trimSegments(2).equals(r.getURI().trimFileExtension().trimSegments(1))){
							l = entry.getValue();
							break outer;
						}
					}else if(entry.getKey().fileExtension().equals("uml") && r.getURI().fileExtension().equals("uim")){
						if(entry.getKey().trimFileExtension().trimSegments(1).equals(r.getURI().trimFileExtension().trimSegments(2))){
							l = entry.getValue();
							break outer;
						}
					}
				}
			}
		}
		if(l == null){
			l = new UmlUimLinks();
			rs.eAdapters().add(l);
		}
		for(Resource r:rs.getResources()){
			TreeIterator<EObject> allContents = r.getAllContents();
			while(allContents.hasNext()){
				EObject eObject = (EObject) allContents.next();
				if(eObject instanceof UmlReference){
					l.uimLoaded = true;
					l.link((UmlReference) eObject);
				}
				if(eObject instanceof org.eclipse.uml2.uml.Package || eObject instanceof Classifier){
					l.umlLoaded = true;
					l.link((Namespace) eObject);
				}
				if(eObject instanceof Property || eObject instanceof Operation || eObject instanceof Parameter || eObject instanceof OpaqueAction
						|| eObject instanceof Pin || eObject instanceof State || eObject instanceof Transition){
					l.uimLoaded = true;
					l.link((NamedElement) eObject);
				}
			}
			instances.put(r.getURI(), l);
		}
	}
	private class UmlUimLink{
		private UmlUimLink(Element umlElement){
			super();
			this.umlElement = umlElement;
		}
		private UmlUimLink(UmlReference uimElement){
			super();
			this.uimElements.add(uimElement);
		}
		Element umlElement;
		Collection<UmlReference> uimElements = new HashSet<UmlReference>();
	}
	private UmlUimLinks(){
	}
	public void link(NamedElement umlElement){
		UmlUimLink link = getLink(umlElement);
		link.umlElement = umlElement;
	}
	private UmlUimLink getLink(Element umlElement){
		UmlUimLink link = links.get(getId(umlElement));
		if(link == null){
			link = new UmlUimLink(umlElement);
			links.put(getId(umlElement), link);
		}
		return link;
	}
	private UmlUimLink getLink(UmlReference umlElement){
		UmlUimLink link = links.get(getId(umlElement));
		if(link == null){
			link = new UmlUimLink(umlElement);
			links.put(getId(umlElement), link);
		}
		return link;
	}
	public Collection<UmlReference> getUimElements(Element umlElement){
		UmlUimLink link = getLink(umlElement);
		return link.uimElements;
	}
	public AbstractFolder getFolderFor(Namespace umlElement){
		UmlUimLink link = getLink(umlElement);
		Collection<UmlReference> uimElements = link.uimElements;
		for(UmlReference ur:uimElements){
			if(ur instanceof AbstractFolder){
				return (AbstractFolder) ur;
			}
		}
		return null;
	}
	public static String getId(Element umlElement){
		if(umlElement instanceof EmfWorkspace){
			return ((EmfWorkspace) umlElement).getName();
		}else{
			EAnnotation ann = umlElement.getEAnnotation(StereotypeNames.NUML_ANNOTATION);
			if(ann == null){
				ann = umlElement.createEAnnotation(StereotypeNames.NUML_ANNOTATION);
			}
			String uid = ann == null ? null : ann.getDetails().get("uid");
			if(uid == null){
				char[] a = UUID.randomUUID().toString().toCharArray();
				for(int i = 0;i < a.length;i++){
					if(!Character.isJavaIdentifierPart(a[i])){
						a[i] = '_';
					}
				}
				uid = new String(a);
				if(ann == null){
					// not in editable resource,but the filename and fragment would be stable and unique
					Resource eResource = umlElement.eResource();
					URI uri = eResource.getURI();
					uid = uri.lastSegment() + eResource.getURIFragment(umlElement);
				}else{
					ann.getDetails().put("uid", uid);
				}
			}
			return uid;
		}
	}
	private String getId(UmlReference uimElement){
		return uimElement.getUmlElementUid();
	}
	public Collection<UmlReference> getBrokenLinks(){
		Collection<UmlUimLink> values = links.values();
		Collection<UmlReference> result = new ArrayList<UmlReference>();
		for(UmlUimLink umlUimLink:values){
			if(umlUimLink.umlElement == null){
				result.addAll(umlUimLink.uimElements);
			}
		}
		return result;
	}
	public void link(UmlReference sf){
		getLink(sf).uimElements.add((UmlReference) sf);
	}
	public Element getUmlElement(UmlReference uIMBinding){
		return getLink(uIMBinding).umlElement;
	}
	public TypedElement getTypedElement(UimBinding uIMBinding){
		return (TypedElement) getLink(uIMBinding).umlElement;
	}
	public Property getProperty(PropertyRef uIMBinding){
		return (Property) getLink(uIMBinding).umlElement;
	}
	public Class getRepresentedClass(OperationContainingFolder folder){
		return (Class) getLink(folder);
	}
	public State getState(StateForm sui){
		return (State) getUmlElement(sui);
	}
	public Operation getOperation(OperationAction eObject){
		return (Operation) getUmlElement(eObject);
	}
	public Transition getTransition(TransitionAction eObject){
		return (Transition) getUmlElement(eObject);
	}
	public Operation getOperation(OperationInvocationForm form){
		return (Operation) getUmlElement(form);
	}
	public static UmlUimLinks getInstance(EObject e){
		URI uri = e.eResource().getURI();
		if(!instances.containsKey(uri)){
			init(e.eResource().getResourceSet());
		}
		return instances.get(uri);
	}
	public Operation getOperation(OperationTaskForm oif){
		return (Operation) getUmlElement(oif);
	}
	public OpaqueAction getAction(ActionTaskForm oif){
		return (OpaqueAction) getUmlElement(oif);
	}
	public Class getClass(ClassForm nearestForm){
		return (Class) getUmlElement(nearestForm);
	}
	public Collection<Property> getOwnedAttributes(Classifier class1){
		return (Collection) EmfElementFinder.getPropertiesInScope(class1);
	}
}
