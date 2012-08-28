package org.opaeum.eclipse.uml.filters.core;

import org.eclipse.uml2.uml.Element;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class EscalationFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return StereotypesHelper.hasStereotype(e, StereotypeNames.ESCALATION);
	}
}
