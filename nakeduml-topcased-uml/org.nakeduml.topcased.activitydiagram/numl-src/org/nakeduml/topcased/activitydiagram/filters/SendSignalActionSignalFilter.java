package org.nakeduml.topcased.activitydiagram.filters;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.SendSignalAction;
import org.nakeduml.topcased.propertysections.filters.AbstractFilter;

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
