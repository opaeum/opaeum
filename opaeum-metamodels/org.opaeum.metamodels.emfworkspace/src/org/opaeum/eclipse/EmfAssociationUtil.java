package org.opaeum.eclipse;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Type;

import org.eclipse.uml2.uml.AssociationClass;

public class EmfAssociationUtil{
	public static boolean isClass(Association a){
		if(a instanceof AssociationClass){
			return true; 
		}else{
			EList<Type> endTypes = a.getEndTypes();
			for(Type type:endTypes){
				if(type instanceof Interface){
					return true;
				}
			}
			return false;
		}
	}
}
