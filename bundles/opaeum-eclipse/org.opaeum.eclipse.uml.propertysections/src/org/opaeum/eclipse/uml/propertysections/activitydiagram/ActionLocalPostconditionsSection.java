package org.opaeum.eclipse.uml.propertysections.activitydiagram;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.constraints.AbstractConstraintsPropertySection;

public class ActionLocalPostconditionsSection extends AbstractConstraintsPropertySection{
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getAction_LocalPostcondition();
	}
}
