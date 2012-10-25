package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractChooserPropertySection;

public class SendSignalActionSignalSection extends AbstractChooserPropertySection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getSendSignalAction_Signal();
	}
	public String getLabelText(){
		return "Signal:";
	}
	protected Object[] getComboFeatureValues(){
		return getChoices(getEObject(), UMLPackage.eINSTANCE.getSignal());
	}
	protected Object getFeatureValue(){
		return ((SendSignalAction) getEObject()).getSignal();
	}
}
