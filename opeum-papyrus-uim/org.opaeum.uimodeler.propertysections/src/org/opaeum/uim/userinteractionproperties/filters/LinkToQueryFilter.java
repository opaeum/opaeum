package org.opaeum.uim.userinteractionproperties.filters;

import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.action.LinkToQuery;

public class LinkToQueryFilter extends AbstractFilter{
	@Override
	public boolean select(UserInteractionElement e){
		return e instanceof LinkToQuery;
	}
}
