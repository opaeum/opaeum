package org.nakeduml.topcased.activitydiagram.propertysections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.eclipse.uml2.uml.ValueSpecification;
import org.nakeduml.topcased.propertysections.AbstractOpaqueExpressionSection;

public class ValuePinValueSection extends AbstractOpaqueExpressionSection{
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getValuePin_Value();
	}
	@Override
	protected String getLabelText(){
		return "Value";
	}
	public ValuePin getValuePin(){
		return (ValuePin) getEObject();
	}
	protected OpaqueExpression getExpression(EObject e){
		return (OpaqueExpression) ((ValuePin)e).getValue();
	}
	@Override
	protected NamedElement getOwner(){
		return getValuePin();
	}
	@Override
	protected ValueSpecification getValueSpecification(){
		return getValuePin().getValue();
	}
}
