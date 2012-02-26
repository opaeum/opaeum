package org.opaeum.topcased.activitydiagram.filters;

import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Element;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.topcased.propertysections.filters.AbstractFilter;

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
