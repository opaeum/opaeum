package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;

public class OperationParametersSection extends AbstractParametersSection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getBehavioralFeature_OwnedParameter();
	}
	protected String getLabelText(){
		return null;
	}
}