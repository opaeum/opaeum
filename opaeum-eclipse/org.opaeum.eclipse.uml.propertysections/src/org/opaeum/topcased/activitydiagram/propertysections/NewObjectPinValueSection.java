package org.opaeum.topcased.activitydiagram.propertysections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.topcased.propertysections.InstanceSpecificationSlotsSection;

public class NewObjectPinValueSection extends InstanceSpecificationSlotsSection{
	@Override
	protected List<InstanceSpecification> getInstanceSpecifications(){
		List<InstanceSpecification> result = new ArrayList<InstanceSpecification>();
		for(EObject eObject:getEObjectList()){
			result.add((InstanceSpecification) ((ValuePin) eObject).getValue());
		}
		return result;
	}
	@Override
	protected void handleModelChanged(Notification msg){
		super.handleModelChanged(msg);
		if(msg.getFeatureID(ValuePin.class)==UMLPackage.VALUE_PIN__TYPE){
			refresh();
		}
	}
}
