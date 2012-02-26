package org.opaeum.topcased.propertysections.filters.activity;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.VariableAction;
import org.opaeum.topcased.propertysections.filters.AbstractFilter;

public class VariableActionFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof VariableAction;
	}
}
