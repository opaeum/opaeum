package org.nakeduml.topcased.propertysections;


import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Trigger;

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
}
