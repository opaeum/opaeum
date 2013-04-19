package org.opaeum.eclipse.uml.filters.core;

import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;

public class AssociationEndFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof Property && ((Property)e).getAssociation()!=null && !(e.getOwner() instanceof Collaboration);//Collaborations "should" not have associations from the user's perspective 
	}
}
