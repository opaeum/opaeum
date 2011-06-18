package org.nakeduml.topcased.activitydiagram.filters;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.uml2.uml.Element;
import org.nakeduml.topcased.propertysections.filters.AbstractFilter;

public class AcceptTaskEventActionTaskEvent extends AbstractFilter{
	@Override
	public boolean select(Element e){
		if(StereotypesHelper.hasStereotype(e, StereotypeNames.ACCEPT_TASK_EVENT_ACTION)){
			return true;
		}
		return false;
	}
}
