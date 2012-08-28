package org.opaeum.eclipse.uml.filters.bpm;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class DurationExpressionFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof OpaqueExpression && StereotypesHelper.hasStereotype(e, StereotypeNames.DURATION_EXPRESSION);
	}
}
