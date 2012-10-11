package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.uml.propertysections.base.OpaeumChooserPropertySection;

public class SlotFeatureSection extends OpaeumChooserPropertySection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getSlot_DefiningFeature();
	}
	public String getLabelText(){
		return "Feature:";
	}
	protected Object[] getComboFeatureValues(){
		if(getInstanceSpecification().getClassifiers().size() == 1){
			return EmfPropertyUtil.getEffectiveProperties(getInstanceSpecification().getClassifiers().get(0)).toArray();
		}else{
			return new Object[0];
		}
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