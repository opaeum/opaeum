package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.eclipse.uml.propertysections.base.AbstractIntegerPropertySection;
import org.opaeum.uim.component.ComponentPackage;

public class UimFieldLabelWidthSection extends AbstractIntegerPropertySection{
	public String getLabelText(){
		return "Label Width";
	}
	protected EStructuralFeature getFeature(){
		return ComponentPackage.eINSTANCE.getUimField_MinimumLabelWidth();
	}
	protected Integer getFeatureInteger(){
		Object Int = getEObject().eGet(getFeature());
		if(Int == null){
			return new Integer(0);
		}
		return (Integer) Int;
	}
}