package org.opaeum.eclipse.uml.filters.bpm;

import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class BusinessDurationObservationFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof DurationObservation && StereotypesHelper.hasStereotype(e, StereotypeNames.BUSINESS_DURATION_OBSERVATION);
	}
}
