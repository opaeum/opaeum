package org.opeum.topcased.activitydiagram.filters;

import org.opeum.emf.extraction.StereotypesHelper;
import org.opeum.metamodel.core.internal.StereotypeNames;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueAction;
import org.opeum.topcased.propertysections.filters.AbstractFilter;

public class EmbeddedSimpleTaskFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof OpaqueAction && StereotypesHelper.hasKeyword(e, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK);
	}
}
