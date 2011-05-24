package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.topcased.modeler.uml.properties.sections.AbstractValueSpecificationSection;

public class ActivityEdgeGuardSection extends AbstractOpaqueExpressionSection{

	@Override
	protected OpaqueExpression getExpression(EObject e){
		return (OpaqueExpression) ((ActivityEdge)e).getGuard();
	}

	@Override
	protected NamedElement getOwner(){
		return getEdge();
	}

	private ActivityEdge getEdge(){
		return (ActivityEdge)getEObject();
	}

	@Override
	protected ValueSpecification getValueSpecification(){
		return getEdge().getGuard();
	}

	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getActivityEdge_Guard();
	}

	@Override
	protected String getLabelText(){
		return "Guard condition";
	}
}
