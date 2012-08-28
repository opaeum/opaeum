package org.opaeum.eclipse.uml.filters.core;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;

public class ModelFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof Model;
	}
}
