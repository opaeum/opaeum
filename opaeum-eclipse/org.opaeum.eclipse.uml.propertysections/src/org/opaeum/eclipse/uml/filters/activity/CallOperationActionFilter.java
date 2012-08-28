package org.opaeum.eclipse.uml.filters.activity;

import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;

public class CallOperationActionFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof CallOperationAction;
	}
}
