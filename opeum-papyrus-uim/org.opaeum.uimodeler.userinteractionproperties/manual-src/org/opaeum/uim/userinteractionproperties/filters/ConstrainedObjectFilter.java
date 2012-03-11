package org.opaeum.uim.userinteractionproperties.filters;

import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.constraint.ConstrainedObject;

public class ConstrainedObjectFilter extends AbstractFilter{

	@Override
	public boolean select(UserInteractionElement e){
		return e instanceof ConstrainedObject;
	}
}
