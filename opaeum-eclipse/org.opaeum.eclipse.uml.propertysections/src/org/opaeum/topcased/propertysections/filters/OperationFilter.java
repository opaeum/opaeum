package org.opaeum.topcased.propertysections.filters;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Operation;

public class OperationFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof Operation;
	}
}
