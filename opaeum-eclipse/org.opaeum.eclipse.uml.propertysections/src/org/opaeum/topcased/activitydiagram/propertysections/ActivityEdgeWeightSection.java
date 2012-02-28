package org.opaeum.topcased.activitydiagram.propertysections;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.topcased.propertysections.base.AbstractAutoCreatingOclExpressionSection;

public class ActivityEdgeWeightSection extends AbstractAutoCreatingOclExpressionSection{
	private ActivityEdge getEdge(){
		return (ActivityEdge) getEObject();
	}
	@Override
	protected EReference getValueSpecificationFeature(){
		return UMLPackage.eINSTANCE.getActivityEdge_Weight();
	}
	protected NamedElement getOclContext(){
		return getEdge();
	}
	@Override
	protected String getLabelText(){
		return "Weight";
	}
	@Override
	protected NamedElement getValueSpecificationOwner(){
		return getEdge();
	}
	public int getOclCompositeHeight(){
		return 30;
	}

}
