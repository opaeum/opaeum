package org.opaeum.uim.userinteractionproperties.filters;

import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.action.LinkToEntity;

public class LinkToEntityFilter extends AbstractFilter{
	@Override
	public boolean select(UserInteractionElement e){
		return e instanceof LinkToEntity;
	}
}
