package org.opaeum.eclipse.uml.filters.core;

import org.eclipse.uml2.uml.Element;

public class PropertyNotInProfileFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof org.eclipse.uml2.uml.Property &&  e.getModel()!=null;
	}
}
