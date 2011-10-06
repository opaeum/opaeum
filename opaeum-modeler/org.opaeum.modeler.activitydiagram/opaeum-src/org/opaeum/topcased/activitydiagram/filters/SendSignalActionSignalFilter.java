package org.opaeum.topcased.activitydiagram.filters;

import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.SendSignalAction;
import org.opaeum.topcased.propertysections.filters.AbstractFilter;

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
