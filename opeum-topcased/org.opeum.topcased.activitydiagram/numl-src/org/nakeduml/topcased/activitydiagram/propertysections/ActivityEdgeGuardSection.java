package org.opeum.topcased.activitydiagram.propertysections;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.opeum.topcased.propertysections.AbstractAutoCreatingOclExpressionSection;

public class ActivityEdgeGuardSection extends AbstractAutoCreatingOclExpressionSection{
	private ActivityEdge getEdge(){
		return (ActivityEdge) getEObject();
	}
	@Override
	protected EReference getValueSpecificationFeature(){
		return UMLPackage.eINSTANCE.getActivityEdge_Guard();
	}
	protected NamedElement getOclContext(){
		return getEdge();
	}
	@Override
	protected String getLabelText(){
		return "Guard condition";
	}
	@Override
	protected NamedElement getValueSpecificationOwner(){
		return getEdge();
	}
}
