package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.uim.UimPackage;
import org.topcased.tabbedproperties.sections.AbstractStringPropertySection;

public class UserInteractionElementNameSection extends AbstractStringPropertySection{

	protected String getLabelText(){
		return "Name:";
	}

	protected EStructuralFeature getFeature(){
		return UimPackage.eINSTANCE.getUserInteractionElement_Name();
	}
}