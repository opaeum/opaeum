package org.opaeum.topcased.propertysections.filters;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;

public class ConstraintFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof Constraint;
	}
}
