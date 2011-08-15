package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;

public class ConstraintValueSpecificationSection extends AbstractOpaqueExpressionSection{
	@Override
	protected EReference getValueSpecificationFeature(){
		return UMLPackage.eINSTANCE.getConstraint_Specification();
	}
	@Override
	protected String getLabelText(){
		return "Boolean value";
	}
	@Override
	protected NamedElement getValueSpecificationOwner(){
		return (Constraint)getEObject();
	}
}
