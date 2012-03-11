package org.opaeum.uim.userinteractionproperties.filters;

import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.panel.Outlayable;

public class UserInteractionElementFilter extends AbstractFilter{

	@Override
	public boolean select(UserInteractionElement e){
		return true;
	}
}
