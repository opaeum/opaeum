package org.opaeum.eclipse.uml.filters.bpm;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.TimeEvent;
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class BusinessTimeEventFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof TimeEvent && StereotypesHelper.hasKeyword(e, StereotypeNames.CONTEXTUAL_BUSINESS_TIME_EVENT);
	}
}
