package org.opaeum.uim.userinteractionproperties.filters;

import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UserInteractionElement;

public class UimDataTableFilter extends AbstractFilter{
	@Override
	public boolean select(UserInteractionElement e){
		return e instanceof UimDataTable;
	}
}
