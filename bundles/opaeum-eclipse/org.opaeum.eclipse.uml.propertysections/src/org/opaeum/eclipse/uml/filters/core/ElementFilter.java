package org.opaeum.eclipse.uml.filters.core;

import org.eclipse.uml2.uml.Element;

public class ElementFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return true;
	}
}
