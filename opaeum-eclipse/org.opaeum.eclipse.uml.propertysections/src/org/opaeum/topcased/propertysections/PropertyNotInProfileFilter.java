package org.opaeum.topcased.propertysections;

import org.eclipse.uml2.uml.Element;
import org.opaeum.topcased.propertysections.filters.AbstractFilter;

public class PropertyNotInProfileFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof org.eclipse.uml2.uml.Property &&  e.getModel()!=null;
	}
}
