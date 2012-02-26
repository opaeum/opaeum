package org.opaeum.topcased.propertysections;

import org.eclipse.uml2.uml.Element;
import org.opaeum.topcased.propertysections.filters.AbstractFilter;

public class ClassNotInProfileFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof org.eclipse.uml2.uml.Class &&  e.getModel()!=null;
	}
}
