package org.nakeduml.uim.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.nakeduml.emf.workspace.UmlElementCache;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.TypedElement;
import org.nakeduml.eclipse.EmfElementFinder;
import org.nakeduml.uim.UmlReference;
import org.nakeduml.uim.UserInteractionElement;
import org.nakeduml.uim.action.OperationAction;
import org.nakeduml.uim.action.TransitionAction;
import org.nakeduml.uim.binding.PropertyRef;
import org.nakeduml.uim.binding.UimBinding;
import org.nakeduml.uim.folder.OperationContainingFolder;
import org.nakeduml.uim.form.ActionTaskForm;
import org.nakeduml.uim.form.ClassForm;
import org.nakeduml.uim.form.FormPanel;
import org.nakeduml.uim.form.OperationInvocationForm;
import org.nakeduml.uim.form.OperationTaskForm;
import org.nakeduml.uim.form.StateForm;

public class UmlUimLinks{
	private static Map<ResourceSet,UmlUimLinks> instances = new HashMap<ResourceSet,UmlUimLinks>();
	Collection<UmlElementCache> maps = new ArrayList<UmlElementCache>();
	public UmlUimLinks(UmlElementCache map){
		maps.add(map);
	}
	public Element getUmlElement(UmlReference uIMBinding){
		return getLink(uIMBinding);
	}
	public TypedElement getTypedElement(UimBinding uIMBinding){
		return (TypedElement) getLink(uIMBinding);
	}
	public Property getProperty(PropertyRef uIMBinding){
		return (Property) getLink(uIMBinding);
	}
	private Element getLink(UmlReference uIMBinding){
		for(UmlElementCache map:maps){
			Element element = map.getElement(uIMBinding.getUmlElementUid());
			if(element != null){
				return element;
			}
		}
		return null;
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
	public static String getId(Element e){
		return UmlElementCache.getId(e);
	}
	public static void associate(ResourceSet uimRst,UmlElementCache umlElementMap){
		UmlUimLinks col = instances.get(uimRst);
		if(col == null){
			col = new UmlUimLinks(umlElementMap);
			instances.put(uimRst,col);
		}else{
			col.maps.add(umlElementMap);
		}
	}
	public static UmlUimLinks getInstance(UserInteractionElement eObject){
		return instances.get(eObject.eResource().getResourceSet());
	}
	public static UmlUimLinks getInstance(FormPanel eObject){
		return instances.get(eObject.eResource().getResourceSet());
	}
	public static UmlUimLinks getInstance(UmlReference eObject){
		return instances.get(eObject.eResource().getResourceSet());
	}
}
