package org.opaeum.uim.userinteractionproperties.filters;

import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UserInteractionElement;

public class ColumnFieldFilter extends AbstractFilter{
	@Override
	public boolean select(UserInteractionElement e){
		if(e instanceof UimField && e.eContainer() instanceof UimDataTable){
			return true;
		}
		return false;
	}
}
