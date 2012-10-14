package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.eclipse.uml.propertysections.base.AbstractBooleanPropertySection;
import org.opaeum.uim.UimPackage;

public class UserInteractionElementIsUnderUserControlSection extends AbstractBooleanPropertySection{

	public String getLabelText(){
		return "Under User Control:";
	}

	protected EStructuralFeature getFeature(){
		return UimPackage.eINSTANCE.getUserInteractionElement_UnderUserControl();
	}

	@Override
	protected Boolean getDefaultValue(){
		return false;
	}
}