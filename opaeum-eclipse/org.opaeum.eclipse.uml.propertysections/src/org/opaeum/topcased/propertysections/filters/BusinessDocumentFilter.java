package org.opaeum.topcased.propertysections.filters;

import org.eclipse.uml2.uml.Element;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class BusinessDocumentFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return (e instanceof org.eclipse.uml2.uml.Class && StereotypesHelper.hasStereotype(e, StereotypeNames.BUSINESS_DOCUMENT));
	}
}
