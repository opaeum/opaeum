package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractChooserPropertySection;

public class ReceptionSignalSection extends AbstractChooserPropertySection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getReception_Signal();
	}
	public String getLabelText(){
		return "Signal:";
	}
	protected Object[] getComboFeatureValues(){
		return getChoices(getEObject(), UMLPackage.eINSTANCE.getSignal());
	}
	protected Object getFeatureValue(){
		return ((Reception) getEObject()).getSignal();
	}
}
