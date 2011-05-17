package org.nakeduml.eclipse;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.ValuePin;
import org.eclipse.uml2.uml.Variable;

public class TypedElementFinder{
	public static List<TypedElement> getTypedElementsInScope(Classifier c){
		List<TypedElement> result = getPropertiesInScope(c);
		if(c instanceof Behavior){
			Behavior b = (Behavior) c;
			result.addAll(b.getOwnedParameters());
			if(b.getContext()!=null){
				result.addAll(getPropertiesInScope(b.getContext()));
			}
		}
		return result;
	}
	public static List<TypedElement> getTypedElementsInScope(ValuePin pin){
		List<TypedElement> result = new ArrayList<TypedElement>();
		Element a= pin.getOwner();
		while(!(a instanceof Activity)){
			if(a instanceof StructuredActivityNode){
				result.addAll(((StructuredActivityNode) a).getVariables());
			}
			a=a.getOwner();
		}
		Activity activity = (Activity)a;
		result.addAll(activity.getVariables());
		result.addAll(getTypedElementsInScope(activity));
//		Variable self = UMLFactory.eINSTANCE.createVariable();
//		self.setName("selfs");
//		self.setType(activity);
//		result.add(self);
//		self.toString();

		return result;
	}

	public static List<TypedElement> getPropertiesInScope(Classifier c){
		List<TypedElement> result=new ArrayList<TypedElement>(c.getAllAttributes());
		EList<Resource> rs = c.eResource().getResourceSet().getResources();
		for(Resource r:rs){
			if(!r.getURI().toString().contains("UML.metamodel.uml") && r.getURI().fileExtension().equals("uml")){
				TreeIterator<EObject> allContents = r.getAllContents();
				while(allContents.hasNext()){
					EObject eObject = (EObject) allContents.next();
					if(eObject instanceof Property){
						Property p = (Property) eObject;
						if(p.isNavigable() && p.getOtherEnd()!=null && p.getOtherEnd().getType()!=null && c.conformsTo(p.getOtherEnd().getType())){
							result.add(p);
						}
					}
				}
			}
		}
		return result;
	}
}
