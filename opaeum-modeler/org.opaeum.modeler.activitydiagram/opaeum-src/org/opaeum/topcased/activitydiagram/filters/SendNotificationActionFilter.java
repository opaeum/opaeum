package org.opaeum.topcased.activitydiagram.filters;

import org.eclipse.uml2.uml.Element;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.topcased.propertysections.filters.AbstractFilter;

public class SendNotificationActionFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return StereotypesHelper.hasStereotype(e, StereotypeNames.SEND_NOTIFICATION_ACTION);
	}
}
