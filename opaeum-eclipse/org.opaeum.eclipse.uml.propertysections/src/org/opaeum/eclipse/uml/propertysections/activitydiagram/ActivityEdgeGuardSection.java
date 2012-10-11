package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractAutoCreatingOclExpressionSection;

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
	public String getLabelText(){
		return "Guard condition";
	}
	@Override
	protected NamedElement getValueSpecificationOwner(){
		return getEdge();
	}
	public int getOclCompositeHeight(){
		return 30;
	}

}
