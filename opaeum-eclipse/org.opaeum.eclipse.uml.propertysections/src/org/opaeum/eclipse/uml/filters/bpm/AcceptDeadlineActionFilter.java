package org.opaeum.eclipse.uml.filters.bpm;

import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class AcceptDeadlineActionFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof AcceptEventAction && StereotypesHelper.hasStereotype(e, StereotypeNames.ACCEPT_DEADLINE_ACTION);
	}
}
