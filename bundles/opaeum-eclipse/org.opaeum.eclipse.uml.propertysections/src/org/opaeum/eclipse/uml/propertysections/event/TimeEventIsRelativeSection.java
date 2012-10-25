package org.opaeum.eclipse.uml.propertysections.event;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractBooleanPropertySection;

public class TimeEventIsRelativeSection extends AbstractBooleanPropertySection{
	@Override
	protected Boolean getDefaultValue(){
		return false;
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
