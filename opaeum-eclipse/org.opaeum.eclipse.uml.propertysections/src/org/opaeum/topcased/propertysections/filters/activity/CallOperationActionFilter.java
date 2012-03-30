package org.opaeum.topcased.propertysections.filters.activity;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Element;
import org.opaeum.topcased.propertysections.filters.AbstractFilter;

public class CallOperationActionFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof CallOperationAction;
	}
}