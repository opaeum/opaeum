package org.opaeum.eclipse.uml.filters.bpm;

import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class AcceptEventActionTriggerSectionFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
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
