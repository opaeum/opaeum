package org.opaeum.eclipse.uml.filters.core;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Operation;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class OperationNotResponsibilityFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof Operation && !StereotypesHelper.hasStereotype(e, StereotypeNames.RESPONSIBILITY);
	}
}
