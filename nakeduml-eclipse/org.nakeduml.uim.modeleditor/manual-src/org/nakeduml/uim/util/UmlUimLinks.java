package org.nakeduml.uim.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
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
import org.nakeduml.eclipse.TypedElementFinder;
import org.nakeduml.uim.AbstractFolder;
import org.nakeduml.uim.ActionTaskForm;
import org.nakeduml.uim.ClassForm;
import org.nakeduml.uim.OperationAction;
import org.nakeduml.uim.OperationContainingFolder;
import org.nakeduml.uim.OperationInvocationForm;
import org.nakeduml.uim.OperationTaskForm;
import org.nakeduml.uim.PropertyRef;
import org.nakeduml.uim.StateForm;
import org.nakeduml.uim.TransitionAction;
import org.nakeduml.uim.UimBinding;
import org.nakeduml.uim.UmlReference;

public class UmlUimLinks{
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
		EAnnotation ann = umlElement.getEAnnotation("http://www.nakeduml.org");
		if(ann == null){
			ann = umlElement.createEAnnotation("http://www.nakeduml.org");
			char[] a = UUID.randomUUID().toString().toCharArray();
			for(int i = 0;i < a.length;i++){
				if(!Character.isJavaIdentifierPart(a[i])){
					a[i] = '_';
				}
			}
			if(ann != null){
				ann.getDetails().put("uid", new String(a));
			}else{
				return new String(a);
			}
		}
		return ann.getDetails().get("uid");
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
		return (Collection)TypedElementFinder.getPropertiesInScope(class1);
	}
}
