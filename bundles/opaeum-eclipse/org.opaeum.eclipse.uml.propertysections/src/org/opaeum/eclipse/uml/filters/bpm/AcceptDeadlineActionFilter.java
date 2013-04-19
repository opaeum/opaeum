package org.opaeum.eclipse.uml.filters.bpm;

import org.eclipse.uml2.uml.AcceptEventAction;
import org.opaeum.eclipse.uml.filters.core.AbstractEObjectFilter;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class AcceptDeadlineActionFilter extends AbstractEObjectFilter<AcceptEventAction>{
	@Override
	public boolean select(AcceptEventAction e){
		return e instanceof AcceptEventAction && StereotypesHelper.hasStereotype(e, StereotypeNames.ACCEPT_DEADLINE_ACTION);
	}
}
