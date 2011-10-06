package org.opaeum.topcased.activitydiagram.filters;

import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Element;
import org.opaeum.topcased.propertysections.filters.AbstractFilter;

public class AcceptDeadlineActionFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof AcceptEventAction && StereotypesHelper.hasStereotype(e, StereotypeNames.ACCEPT_DEADLINE_ACTION);
	}
}
