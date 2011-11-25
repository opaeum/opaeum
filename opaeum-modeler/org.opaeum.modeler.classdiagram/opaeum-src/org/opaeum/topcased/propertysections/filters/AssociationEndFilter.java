package org.opaeum.topcased.propertysections.filters;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;

public class AssociationEndFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof Property && ((Property)e).getAssociation()!=null;
	}
}
