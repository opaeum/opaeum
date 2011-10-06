package org.opaeum.topcased.propertysections.constraints;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;

public class OperationPostconditionsSection extends AbstractConstraintsPropertySection{
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getOperation_Postcondition();
	}
}
