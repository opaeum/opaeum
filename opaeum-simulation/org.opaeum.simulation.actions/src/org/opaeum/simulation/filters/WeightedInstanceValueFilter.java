package org.opaeum.simulation.filters;

import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;
import org.opaeum.metamodels.simulation.simulation.WeightedInstanceValue;

public class WeightedInstanceValueFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof WeightedInstanceValue;
	}
}
