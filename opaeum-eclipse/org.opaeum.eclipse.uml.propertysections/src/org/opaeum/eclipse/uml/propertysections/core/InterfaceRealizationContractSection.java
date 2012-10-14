package org.opaeum.eclipse.uml.propertysections.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.uml.propertysections.base.AbstractChooserPropertySection;



public class InterfaceRealizationContractSection extends AbstractChooserPropertySection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getInterfaceRealization_Contract();
	}
	public String getLabelText(){
		return "Classifier specialized:";
	}
	protected Object[] getComboFeatureValues(){
		
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		Collection<EObject> types = OpaeumEclipseContext.getReachableObjectsOfType(getEObject(), UMLPackage.eINSTANCE.getInterface());
		choices.addAll(types);
		return choices.toArray();
	}
	protected Object getFeatureValue(){
		return ((InterfaceRealization)getEObject()).getContract();
	}
}