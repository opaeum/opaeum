package org.opaeum.eclipse.uml.filters.activity;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.VariableAction;
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;

public class VariableActionFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof VariableAction;
	}
}
