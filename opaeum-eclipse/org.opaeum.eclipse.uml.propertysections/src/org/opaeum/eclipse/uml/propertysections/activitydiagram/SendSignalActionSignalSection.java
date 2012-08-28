package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.common.edit.provider.IItemQualifiedTextProvider;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.opaeum.eclipse.uml.propertysections.base.OpaeumChooserPropertySection;

public class SendSignalActionSignalSection extends OpaeumChooserPropertySection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getSendSignalAction_Signal();
	}
	protected String getLabelText(){
		return "Signal:";
	}
	protected Object[] getComboFeatureValues(){
		return getChoices(getEObject(), UMLPackage.eINSTANCE.getSignal());
	}
	protected ILabelProvider getLabelProvider(){
		return super.getLabelProvider();
	}
	protected ILabelProvider getAdvancedLabeProvider(){
		return super.getAdvancedLabeProvider();
	}
	protected Object getFeatureValue(){
		return ((SendSignalAction) getEObject()).getSignal();
	}
}
