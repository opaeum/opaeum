package org.opaeum.simulation.filters;

import org.eclipse.uml2.uml.Element;
import org.opaeum.metamodels.simulation.simulation.WeightedInstanceValue;
import org.opaeum.topcased.propertysections.filters.AbstractFilter;

public class WeightedInstanceValueFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof WeightedInstanceValue;
	}
}
