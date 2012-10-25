package org.opaeum.eclipse.uml.filters.core;

import org.eclipse.uml2.uml.Element;

public class AssociationNotInProfileFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof org.eclipse.uml2.uml.Association &&  e.getModel()!=null;
	}
}
