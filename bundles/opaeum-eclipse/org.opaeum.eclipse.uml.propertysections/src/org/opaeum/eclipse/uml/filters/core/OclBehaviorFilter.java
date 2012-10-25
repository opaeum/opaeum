package org.opaeum.eclipse.uml.filters.core;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class OclBehaviorFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof OpaqueBehavior && !StereotypesHelper.hasStereotype(e, StereotypeNames.STANDALONE_SINGLE_SCREEN_TASK);
	}
}
