package org.opaeum.eclipse.uml.filters.core;

import org.eclipse.uml2.uml.Element;

public class ClassNotInProfileFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof org.eclipse.uml2.uml.Class &&  e.getModel()!=null;
	}
}
