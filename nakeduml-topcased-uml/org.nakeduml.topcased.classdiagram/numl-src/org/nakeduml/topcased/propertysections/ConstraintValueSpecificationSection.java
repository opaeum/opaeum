package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;

public class ConstraintValueSpecificationSection extends AbstractOpaqueExpressionSection{
	@Override
	protected OpaqueExpression getExpression(EObject e){
		return (OpaqueExpression) ((Constraint)e).getSpecification();
	}
	@Override
	protected NamedElement getOwner(){
		return (NamedElement) getEObject();
	}
	@Override
	protected ValueSpecification getValueSpecification(){
		return  ((Constraint)getEObject()).getSpecification();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getConstraint_Specification();
	}
	@Override
	protected String getLabelText(){
		return "Boolean value";
	}
}
