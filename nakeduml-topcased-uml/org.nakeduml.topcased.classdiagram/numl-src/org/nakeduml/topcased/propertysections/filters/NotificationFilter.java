package org.nakeduml.topcased.propertysections.filters;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.uml2.uml.Element;

public class NotificationFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return StereotypesHelper.hasStereotype(e, StereotypeNames.NOTIFICATION);
	}
}
