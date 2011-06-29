package org.nakeduml.topcased.propertysections;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;

public class UmlMetaTypeRemover{

	public static Collection<? extends Object> removeAll(Collection<EObject> types){
		Collection<EObject> result =new ArrayList<EObject>();
		for(EObject eObject:types){
			boolean isAssociation = eObject instanceof Association && !(eObject instanceof AssociationClass);
			if(!(eObject.eResource().getURI().toString().contains("UML.metamodel.uml")||isAssociation)){
				result.add(eObject);
			}
		}
		return result;
	}
}
