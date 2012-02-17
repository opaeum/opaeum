package org.opaeum.topcased.propertysections.filters.activity;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.SendSignalAction;
import org.opaeum.topcased.propertysections.filters.AbstractFilter;

public class SendSignalActionFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof SendSignalAction;
	}
}
