package org.opaeum.eclipse.uml.filters.bpm;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.SendSignalAction;
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class SendNotificationActionFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof SendSignalAction && StereotypesHelper.hasStereotype(e, StereotypeNames.SEND_NOTIFICATION_ACTION);
	}
}
