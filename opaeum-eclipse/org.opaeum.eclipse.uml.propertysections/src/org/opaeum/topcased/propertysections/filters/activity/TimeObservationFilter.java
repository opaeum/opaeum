package org.opaeum.topcased.propertysections.filters.activity;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.TimeObservation;
import org.opaeum.topcased.propertysections.filters.AbstractFilter;

public class TimeObservationFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof TimeObservation;
	}
}
