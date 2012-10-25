package org.opaeum.eclipse.uml.propertysections.activitydiagram;


import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractTriggerSection;

public class AcceptEventActionTriggerSection extends AbstractTriggerSection{
	@Override
	protected List<Trigger> getTriggers(){
		return getAction().getTriggers();
	}
	@Override
	protected NamedElement getOwner(){
		return getAction();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
	AcceptEventAction getAction(){
		return (AcceptEventAction) getEObject();
	}
	protected EReference getTriggerStructuralFeature(){
		return UMLPackage.eINSTANCE.getAcceptEventAction_Trigger();
	}

}
