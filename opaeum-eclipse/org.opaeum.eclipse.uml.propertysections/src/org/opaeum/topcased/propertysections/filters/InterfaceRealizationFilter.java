package org.opaeum.topcased.propertysections.filters;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InterfaceRealization;

public class InterfaceRealizationFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof InterfaceRealization;
	}
}
