package org.nakeduml.topcased.activitydiagram.propertysections;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;
import org.nakeduml.topcased.propertysections.constraints.AbstractConstraintsPropertySection;

public class ActionLocalPreconditionsSection extends AbstractConstraintsPropertySection{
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getAction_LocalPrecondition();
	}
}
