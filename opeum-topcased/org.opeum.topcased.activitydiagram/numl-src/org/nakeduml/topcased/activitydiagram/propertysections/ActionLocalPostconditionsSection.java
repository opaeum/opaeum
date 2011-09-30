package org.opeum.topcased.activitydiagram.propertysections;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;
import org.opeum.topcased.propertysections.constraints.AbstractConstraintsPropertySection;

public class ActionLocalPostconditionsSection extends AbstractConstraintsPropertySection{
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getAction_LocalPostcondition();
	}
}
