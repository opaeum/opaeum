package org.opaeum.topcased.activitydiagram.filters;

import org.eclipse.uml2.uml.Element;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.topcased.propertysections.filters.AbstractFilter;

public class AcceptTaskEventActionTaskEvent extends AbstractFilter{
	@Override
	public boolean select(Element e){
		if(StereotypesHelper.hasStereotype(e, StereotypeNames.ACCEPT_TASK_EVENT_ACTION)){
			return true;
		}
		return false;
	}
}
