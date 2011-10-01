package org.opeum.topcased.activitydiagram.filters;

import org.opeum.emf.extraction.StereotypesHelper;
import org.opeum.metamodel.core.internal.StereotypeNames;

import org.eclipse.uml2.uml.Element;
import org.opeum.topcased.propertysections.filters.AbstractFilter;

public class SendNotificationActionFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return StereotypesHelper.hasStereotype(e, StereotypeNames.SEND_NOTIFICATION_ACTION);
	}
}
