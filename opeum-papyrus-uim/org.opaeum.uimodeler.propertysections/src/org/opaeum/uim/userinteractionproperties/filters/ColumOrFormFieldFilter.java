package org.opaeum.uim.userinteractionproperties.filters;

import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.component.UimField;

public class ColumOrFormFieldFilter extends AbstractFilter{
	@Override
	public boolean select(UserInteractionElement e){
		if(e instanceof UimField){
			return true;
		}
		return false;
	}
}
