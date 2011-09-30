package org.opeum.topcased.activitydiagram.filters;

import org.opeum.emf.extraction.StereotypesHelper;
import org.opeum.metamodel.core.internal.StereotypeNames;

import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Element;
import org.opeum.topcased.propertysections.filters.AbstractFilter;

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
