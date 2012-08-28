package org.opaeum.eclipse.uml.filters.bpm;

import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class AcceptTaskEventActionTaskEvent extends AbstractFilter{
	@Override
	public boolean select(Element e){
		if(StereotypesHelper.hasStereotype(e, StereotypeNames.ACCEPT_TASK_EVENT_ACTION)){
			return true;
		}
		return false;
	}
}
