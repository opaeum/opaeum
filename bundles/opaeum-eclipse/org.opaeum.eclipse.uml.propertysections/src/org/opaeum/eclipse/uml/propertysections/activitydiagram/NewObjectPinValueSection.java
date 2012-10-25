package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.opaeum.eclipse.uml.propertysections.core.InstanceSpecificationSlotsSection;

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
