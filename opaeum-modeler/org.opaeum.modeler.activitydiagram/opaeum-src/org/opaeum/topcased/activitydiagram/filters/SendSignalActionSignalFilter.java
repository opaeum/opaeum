package org.opeum.topcased.activitydiagram.filters;

import org.opeum.emf.extraction.StereotypesHelper;
import org.opeum.metamodel.core.internal.StereotypeNames;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.SendSignalAction;
import org.opeum.topcased.propertysections.filters.AbstractFilter;

public class SendSignalActionSignalFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		if(e instanceof SendSignalAction){
			return !StereotypesHelper.hasStereotype(e, StereotypeNames.SEND_NOTIFICATION_ACTION);
		}else{
			return false;
		}
	}
}
