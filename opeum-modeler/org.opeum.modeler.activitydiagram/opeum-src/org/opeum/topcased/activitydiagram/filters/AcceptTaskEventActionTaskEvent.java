package org.opeum.topcased.activitydiagram.filters;

import org.opeum.emf.extraction.StereotypesHelper;
import org.opeum.metamodel.core.internal.StereotypeNames;

import org.eclipse.uml2.uml.Element;
import org.opeum.topcased.propertysections.filters.AbstractFilter;

public class AcceptTaskEventActionTaskEvent extends AbstractFilter{
	@Override
	public boolean select(Element e){
		if(StereotypesHelper.hasStereotype(e, StereotypeNames.ACCEPT_TASK_EVENT_ACTION)){
			return true;
		}
		return false;
	}
}
