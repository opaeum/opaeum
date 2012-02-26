package org.opaeum.topcased.propertysections.property;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Association;
import org.opaeum.topcased.propertysections.NamedElementNameSection;

public class SecondEndNameSection extends NamedElementNameSection{
	@Override
	protected EObject getEObject(){
		return super.getEObject()==null?null:((Association) super.getEObject()).getMemberEnds().get(1);
	}
	@Override
	protected List<EObject> getEObjectList(){
		List<EObject> result = new ArrayList<EObject>();
		for(EObject eObject:super.getEObjectList()){
			result.add(((Association) eObject).getMemberEnds().get(1));
		}
		return result;
	}
}
