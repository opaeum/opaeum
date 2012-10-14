package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.eclipse.uml.propertysections.base.AbstractStringPropertySection;
import org.opaeum.uim.UimPackage;

public class UserInteractionElementNameSection extends AbstractStringPropertySection{

	public String getLabelText(){
		return "Name:";
	}

	protected EStructuralFeature getFeature(){
		return UimPackage.eINSTANCE.getUserInteractionElement_Name();
	}
}