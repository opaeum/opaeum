package org.opaeum.topcased.propertysections.filters;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.ValueSpecification;

public class OpaqueExpressionFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof OpaqueExpression;
	}
}
