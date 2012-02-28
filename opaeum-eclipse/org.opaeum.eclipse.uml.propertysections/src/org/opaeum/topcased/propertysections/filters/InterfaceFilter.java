package org.opaeum.topcased.propertysections.filters;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;

public class InterfaceFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof Interface;
	}
}
