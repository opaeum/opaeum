package org.opaeum.eclipse;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;

public class EmfAssociationUtil{
	public static Property getFirstEnd(EObject ass){
		if(ass instanceof Association && ((Association) ass).getMemberEnds().size() > 0){
			return ((Association) ass).getMemberEnds().get(0);
		}
		return null;
	}
	public static Property getSecondEnd(EObject ass){
		if(ass instanceof Association && ((Association) ass).getMemberEnds().size() > 1){
			return ((Association) ass).getMemberEnds().get(1);
		}
		return null;
	}
	public static boolean isClass(Association a){
		if(a == null){
			return false;
		}else{
			for(Property property:a.getMemberEnds()){
				if(EmfPropertyUtil.isDerived(property)){
					return false;
				}
			}
			if(a instanceof AssociationClass){
				return true;
			}else{
				if(a.getMemberEnds().size() > 2){
					return true;
				}
				for(Property property:a.getMemberEnds()){
					if(EmfPropertyUtil.isMany(property)
							&& (property.getType() instanceof Interface || property.getOtherEnd().getType() instanceof Interface)){
						return true;
					}
				}
				return false;
			}
		}
	}
}
