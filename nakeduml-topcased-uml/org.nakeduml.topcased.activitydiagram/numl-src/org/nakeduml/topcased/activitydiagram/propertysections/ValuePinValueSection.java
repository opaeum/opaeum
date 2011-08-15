package org.nakeduml.topcased.activitydiagram.propertysections;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.nakeduml.topcased.propertysections.AbstractOpaqueExpressionSection;

public class ValuePinValueSection extends AbstractOpaqueExpressionSection{
	@Override
	protected String getLabelText(){
		return "Value";
	}
	public ValuePin getValuePin(){
		return (ValuePin) getEObject();
	}
	@Override
	protected NamedElement getValueSpecificationOwner(){
		return getValuePin();
	}
	@Override
	protected EReference getValueSpecificationFeature(){
		return UMLPackage.eINSTANCE.getValuePin_Value();
	}
}
