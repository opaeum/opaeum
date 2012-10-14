package org.opaeum.eclipse.uml.propertysections.core;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;

public class UmlMetaTypeRemover{
	public static Collection<EObject> removeAssocations(Collection<EObject> types){
		Collection<EObject> result = new ArrayList<EObject>();
		for(EObject eObject:types){
			if(isNonAssocation(eObject) ){
				result.add(eObject);
			}
		}
		return result;
	}
	protected static boolean isNonAssocation(EObject eObject){
		boolean valid =!(eObject instanceof Association && !(eObject instanceof AssociationClass));
		return valid;
	}
}
