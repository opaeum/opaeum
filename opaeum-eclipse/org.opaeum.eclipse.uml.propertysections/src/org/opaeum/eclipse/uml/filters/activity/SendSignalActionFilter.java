package org.opaeum.eclipse.uml.filters.activity;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.SendSignalAction;
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class SendSignalActionFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		if(e instanceof SendSignalAction){
			return !StereotypesHelper.hasStereotype(e, StereotypeNames.SEND_NOTIFICATION_ACTION);
		}else{
			return false;
		}
	}
}
