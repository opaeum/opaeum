package org.opaeum.eclipse.uml.propertysections.event;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractBooleanSection;

public class TimeEventIsRelativeSection extends AbstractBooleanSection{
	@Override
	protected Boolean getDefaultValue(){
		return false;
	}
	@Override
	protected Element getElement(EObject eObject){
		return (TimeEvent)eObject;
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getTimeEvent_IsRelative();
	}
	@Override
	public String getLabelText(){
		return "Is Relative:";
	}
}
