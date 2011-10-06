package org.opaeum.topcased.activitydiagram.filters;

import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ValuePin;
import org.opaeum.topcased.propertysections.filters.AbstractFilter;

public class NewObjectPinFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof ValuePin && StereotypesHelper.hasKeyword(e, StereotypeNames.NEW_OBJECT_INPUT);
	}
}
