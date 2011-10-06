package org.opaeum.topcased.propertysections.filters;

import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

import org.eclipse.uml2.uml.Element;

public class NotificationFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return StereotypesHelper.hasStereotype(e, StereotypeNames.NOTIFICATION);
	}
}
