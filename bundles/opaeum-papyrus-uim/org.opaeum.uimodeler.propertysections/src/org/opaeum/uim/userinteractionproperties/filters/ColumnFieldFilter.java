package org.opaeum.uim.userinteractionproperties.filters;

import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uim.component.UimField;

public class ColumnFieldFilter extends AbstractFilter{
	@Override
	public boolean select(UserInteractionElement e){
		if(e instanceof UimField && e.eContainer() instanceof UimDataTable){
			return true;
		}
		return false;
	}
}
