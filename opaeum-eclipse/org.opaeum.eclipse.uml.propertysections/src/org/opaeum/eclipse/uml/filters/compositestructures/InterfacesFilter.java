package org.opaeum.eclipse.uml.filters.compositestructures;

import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;

public class InterfacesFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		if(e instanceof Property){
			Property p =(Property) e;
			if(p.getOwner() instanceof Component){
				return true;
			}
		}
		return false;
	}
}
