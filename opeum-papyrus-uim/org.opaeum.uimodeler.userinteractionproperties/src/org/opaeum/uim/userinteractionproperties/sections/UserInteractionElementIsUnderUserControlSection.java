package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.uim.UimPackage;
import org.topcased.tabbedproperties.sections.AbstractBooleanPropertySection;

public class UserInteractionElementIsUnderUserControlSection extends AbstractBooleanPropertySection{

	protected String getLabelText(){
		return "Under User Control:";
	}

	protected EStructuralFeature getFeature(){
		return UimPackage.eINSTANCE.getUserInteractionElement_UnderUserControl();
	}
}