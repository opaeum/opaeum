package org.opaeum.eclipse;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;

public class EmfAssociationUtil{
	public static boolean isClass(Association a){
		if(a instanceof AssociationClass){
			return true; 
		}else{
			if(a.getMemberEnds().size()>2){
				return true;
			}
			for(Property property:a.getMemberEnds()){
				if(EmfPropertyUtil.isMany(property) && (property.getType() instanceof Interface || property.getOtherEnd().getType() instanceof Interface)){
					return true;
				}
			}
			return false;
		}
	}
}
