package org.nakeduml.topcased.propertysections.filters;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Pin;

public class NamedElementNameSectionFilter extends AbstractFilter{
	public boolean select(Element element){
		if(element instanceof NamedElement && !(element instanceof Pin)){
			return true;
		}
		return false;
	}
}
