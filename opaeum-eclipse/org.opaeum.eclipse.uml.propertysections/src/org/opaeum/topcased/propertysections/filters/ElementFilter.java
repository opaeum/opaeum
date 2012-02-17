package org.opaeum.topcased.propertysections.filters;

import org.eclipse.uml2.uml.Element;

public class ElementFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return true;
	}
}
