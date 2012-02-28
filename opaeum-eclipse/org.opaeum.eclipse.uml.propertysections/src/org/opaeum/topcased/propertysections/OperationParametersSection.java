package org.opaeum.topcased.propertysections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.topcased.propertysections.base.AbstractParametersSection;

public class OperationParametersSection extends AbstractParametersSection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getBehavioralFeature_OwnedParameter();
	}
	protected String getLabelText(){
		return null;
	}
}