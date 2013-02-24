package org.opaeum.uim.userinteractionproperties.filters;

import org.opaeum.uim.LabelContainer;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.UserInteractionElement;

public class LabeledElementFilter extends AbstractFilter{

	@Override
	public boolean select(UserInteractionElement e){
		return e instanceof LabelContainer;
	}
}
