package org.nakeduml.topcased.activitydiagram.filters;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Element;
import org.nakeduml.topcased.propertysections.filters.AbstractFilter;

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