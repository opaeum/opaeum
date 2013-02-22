package org.opaeum.eclipse.uml.filters.bpm;

import org.eclipse.uml2.uml.AcceptEventAction;
import org.opaeum.eclipse.uml.filters.core.AbstractEObjectFilter;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class AcceptEventActionTriggerSectionFilter extends AbstractEObjectFilter<AcceptEventAction>{
	@Override
	public boolean select(AcceptEventAction e){
		if(e instanceof AcceptEventAction){
			if(StereotypesHelper.hasStereotype(e, StereotypeNames.ACCEPT_DEADLINE_ACTION) ||StereotypesHelper.hasStereotype(e, StereotypeNames.ACCEPT_TASK_EVENT_ACTION)){
				return false;
			}else{
				return true;
			}
		}else{
			return false;
		}
	}
}
