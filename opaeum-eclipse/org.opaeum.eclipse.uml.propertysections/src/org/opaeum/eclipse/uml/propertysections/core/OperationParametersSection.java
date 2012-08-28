package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractParametersSection;

public class OperationParametersSection extends AbstractParametersSection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getBehavioralFeature_OwnedParameter();
	}
	protected String getLabelText(){
		return null;
	}
}