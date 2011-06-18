package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.ValueSpecification;

public class OpaqueExpressionBodySection extends AbstractOpaqueExpressionSection{
	@Override
	protected OpaqueExpression getExpression(EObject e){
		return (OpaqueExpression) e;
	}
	@Override
	protected NamedElement getOwner(){
		EObject eContainer = getEObject().eContainer();
		while(!(eContainer instanceof NamedElement)){
			eContainer=eContainer.eContainer();
		}
		return (NamedElement) eContainer;
	}
	@Override
	protected ValueSpecification getValueSpecification(){
		return (ValueSpecification) getEObject();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
	@Override
	protected String getLabelText(){
		return "Body";
	}
}
