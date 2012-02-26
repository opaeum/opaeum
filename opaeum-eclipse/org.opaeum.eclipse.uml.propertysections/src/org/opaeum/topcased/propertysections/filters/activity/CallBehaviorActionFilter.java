package org.opaeum.topcased.propertysections.filters.activity;

import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Element;
import org.opaeum.topcased.propertysections.filters.AbstractFilter;

public class CallBehaviorActionFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof CallBehaviorAction;
	}
}
