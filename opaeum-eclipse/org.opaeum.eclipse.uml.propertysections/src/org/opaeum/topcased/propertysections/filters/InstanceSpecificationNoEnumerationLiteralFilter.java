package org.opaeum.topcased.propertysections.filters;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.InstanceSpecification;

public class InstanceSpecificationNoEnumerationLiteralFilter extends AbstractFilter{
	public boolean select(Element element){
		return element instanceof InstanceSpecification && ! (element instanceof EnumerationLiteral);
	}
}
