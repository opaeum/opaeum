package org.opeum.topcased.activitydiagram.propertysections;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opeum.topcased.propertysections.InstanceSpecificationSlotsSection;

public class NewObjectPinValueSection extends InstanceSpecificationSlotsSection{
	@Override
	protected InstanceSpecification getInstanceSpecification(){
		ValueSpecification value = ((ValuePin) getEObject()).getValue();
		if(value != null){
			return ((InstanceValue) value).getInstance();
		}else{
			return null;
		}
	}
	@Override
	protected void handleModelChanged(Notification msg){
		super.handleModelChanged(msg);
		if(msg.getFeatureID(ValuePin.class)==UMLPackage.VALUE_PIN__TYPE){
			refresh();
		}
	}
}
