package org.opeum.topcased.propertysections.constraints;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;

public class ClassInvariantsSection extends AbstractConstraintsPropertySection{
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getNamespace_OwnedRule();
	}
}
