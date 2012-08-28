package org.opaeum.eclipse.uml.filters.core;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;

public class InstanceSpecificationFilter extends AbstractFilter{
	public boolean select(Element element){
		return element instanceof InstanceSpecification;
	}
}
