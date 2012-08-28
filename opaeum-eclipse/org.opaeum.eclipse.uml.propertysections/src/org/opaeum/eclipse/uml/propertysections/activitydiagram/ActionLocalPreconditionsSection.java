package org.opaeum.eclipse.uml.propertysections.activitydiagram;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.constraints.AbstractConstraintsPropertySection;

public class ActionLocalPreconditionsSection extends AbstractConstraintsPropertySection{
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getAction_LocalPrecondition();
	}
}
