package org.opeum.topcased.statemachinediagram;

import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLPackage;
import org.opeum.topcased.propertysections.AbstractTriggerSection;

public class TransitionTriggerSection extends AbstractTriggerSection{
	@Override
	protected List<Trigger> getTriggers(){
		return getTransition().getTriggers();
	}
	private Transition getTransition(){
		return (Transition) getEObject();
	}
	@Override
	protected NamedElement getOwner(){
		return getTransition();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getTransition_Trigger();
	}
	protected String getEventDetailsLabel(){
		return "Cause Details";
	}
	protected String getEventTypeLabel(){
		return "Cause Type";
	}

	protected EReference getTriggerStructuralFeature(){
		return UMLPackage.eINSTANCE.getTransition_Trigger();
	}

}
