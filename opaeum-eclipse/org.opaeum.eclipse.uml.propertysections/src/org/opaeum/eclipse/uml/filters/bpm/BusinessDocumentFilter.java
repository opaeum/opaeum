package org.opaeum.eclipse.uml.filters.bpm;

import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class BusinessDocumentFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return (e instanceof org.eclipse.uml2.uml.Class && StereotypesHelper.hasStereotype(e, StereotypeNames.BUSINESS_DOCUMENT));
	}
}
