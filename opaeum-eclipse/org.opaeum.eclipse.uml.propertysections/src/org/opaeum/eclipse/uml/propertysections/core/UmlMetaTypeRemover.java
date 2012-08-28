package org.opaeum.eclipse.uml.propertysections.core;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;

public class UmlMetaTypeRemover{
	public static Collection<EObject> removeAll(Collection<EObject> types){
		Collection<EObject> result = new ArrayList<EObject>();
		for(EObject eObject:types){
			boolean valid = true;
			if(eObject instanceof NamedElement){
				Model model = ((NamedElement) eObject).getModel();
				if(model != null){
					// filter out metamodels
					valid = model.getAppliedProfile("Ecore") == null;
				}
			}
			valid = valid && !(eObject instanceof Association && !(eObject instanceof AssociationClass));
			if(valid && !(eObject instanceof Stereotype)){
				result.add(eObject);
			}
		}
		return result;
	}
}
