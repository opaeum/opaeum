package org.opaeum.eclipse.uml.propertysections.property;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Association;
import org.opaeum.eclipse.uml.propertysections.core.NamedElementNameSection;

public class FirstEndNameSection extends NamedElementNameSection{
	@Override
	protected EObject getEObject(){
		Association association = (Association) super.getEObject();
		if(association == null || association.getMembers().size() < 1){
			return null;
		}else{
			return association.getMemberEnds().get(0);
		}
	}
	@Override
	protected List<EObject> getEObjectList(){
		List<EObject> result = new ArrayList<EObject>();
		for(EObject eObject:super.getEObjectList()){
			Association association = (Association) eObject;
			if(association.getMembers().size() >= 1){
				result.add(association.getMemberEnds().get(0));
			}
		}
		return result;
	}
}
