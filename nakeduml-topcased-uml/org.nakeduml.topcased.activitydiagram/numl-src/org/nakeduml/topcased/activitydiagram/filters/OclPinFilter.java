package org.nakeduml.topcased.activitydiagram.filters;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.ValuePin;
import org.nakeduml.topcased.propertysections.filters.AbstractFilter;

public class OclPinFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof ValuePin && !StereotypesHelper.hasKeyword(e, StereotypeNames.NEW_OBJECT_INPUT);
	}
}
