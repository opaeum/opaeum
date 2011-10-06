package org.opaeum.topcased.activitydiagram.filters;

import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueAction;
import org.opaeum.topcased.propertysections.filters.AbstractFilter;

public class OclOpaqueActionFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof OpaqueAction && !StereotypesHelper.hasKeyword(e, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK);
	}
}
