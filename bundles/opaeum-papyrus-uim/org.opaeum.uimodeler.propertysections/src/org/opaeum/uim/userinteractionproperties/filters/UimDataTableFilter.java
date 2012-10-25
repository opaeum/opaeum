package org.opaeum.uim.userinteractionproperties.filters;

import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.component.UimDataTable;

public class UimDataTableFilter extends AbstractFilter{
	@Override
	public boolean select(UserInteractionElement e){
		return e instanceof UimDataTable;
	}
}
