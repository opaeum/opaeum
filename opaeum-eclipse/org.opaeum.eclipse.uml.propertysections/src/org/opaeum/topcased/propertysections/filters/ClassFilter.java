package org.opaeum.topcased.propertysections.filters;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Element;

public class ClassFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof org.eclipse.uml2.uml.Class;
	}
}
