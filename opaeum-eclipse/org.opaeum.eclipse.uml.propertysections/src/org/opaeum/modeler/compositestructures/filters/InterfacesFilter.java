package org.opaeum.modeler.compositestructures.filters;

import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.opaeum.topcased.propertysections.filters.AbstractFilter;

public class InterfacesFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		if(e instanceof Property){
			Property p =(Property) e;
			if(p.getOwner() instanceof Component){
				Component c=(Component) p.getOwner() ;
				return true;
			}
		}
		return false;
	}
}
