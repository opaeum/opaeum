package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.uml.propertysections.base.OpaeumChooserPropertySection;
import org.opaeum.eclipse.uml.propertysections.common.OpaeumQualifiedNameLabelProvider;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;

public class SlotFeatureSection extends OpaeumChooserPropertySection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getSlot_DefiningFeature();
	}
	protected String getLabelText(){
		return "Feature:";
	}
	protected Object[] getComboFeatureValues(){
		if(getInstanceSpecification().getClassifiers().size() == 1){
			return EmfElementFinder.getPropertiesInScope(getInstanceSpecification().getClassifiers().get(0)).toArray();
		}else{
			return new Object[0];
		}
	}
	protected ILabelProvider getLabelProvider(){
		return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
	}
	protected ILabelProvider getAdvancedLabeProvider(){
		return new OpaeumQualifiedNameLabelProvider(new OpaeumItemProviderAdapterFactory());
	}
	protected Object getFeatureValue(){
		return getSlot().getDefiningFeature();
	}
	public InstanceSpecification getInstanceSpecification(){
		InstanceSpecification instanceSpecification = (InstanceSpecification) getSlot().getOwningInstance();
		return instanceSpecification;
	}
	public Slot getSlot(){
		return (Slot) getEObject();
	}
}