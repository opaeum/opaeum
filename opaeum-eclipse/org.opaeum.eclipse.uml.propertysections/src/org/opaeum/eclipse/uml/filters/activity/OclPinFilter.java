package org.opaeum.eclipse.uml.filters.activity;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ValuePin;
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class OclPinFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof ValuePin && !StereotypesHelper.hasKeyword(e, StereotypeNames.NEW_OBJECT_INPUT);
	}
}
