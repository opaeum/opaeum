package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.common.edit.provider.IItemQualifiedTextProvider;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.StructuralFeatureAction;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VariableAction;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.topcased.tabbedproperties.sections.AbstractChooserPropertySection;

public class SendSignalActionSignalSection extends AbstractChooserPropertySection{
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
		return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
	}
	protected ILabelProvider getAdvancedLabeProvider(){
		return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory()){
			public String getText(Object object){
				IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(object, IItemQualifiedTextProvider.class);
				return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object) : super.getText(object);
			}
		};
	}
	protected Object getFeatureValue(){
		return ((SendSignalAction) getEObject()).getSignal();
	}
}
