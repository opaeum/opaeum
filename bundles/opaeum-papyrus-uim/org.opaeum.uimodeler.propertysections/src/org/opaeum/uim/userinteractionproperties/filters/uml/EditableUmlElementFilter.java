package org.opaeum.uim.userinteractionproperties.filters.uml;

import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;

public class EditableUmlElementFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return false;
	}
}
