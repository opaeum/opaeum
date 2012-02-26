package org.opaeum.topcased.propertysections.filters;

import org.eclipse.jface.viewers.IFilter;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.TypedElement;

public class TypedElementFilter extends AbstractFilter implements IFilter{
	@Override
	public boolean select(Element e){
		return e instanceof TypedElement;
	}
}
