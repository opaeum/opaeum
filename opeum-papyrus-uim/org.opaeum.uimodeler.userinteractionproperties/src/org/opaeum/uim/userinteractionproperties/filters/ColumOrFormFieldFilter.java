package org.opaeum.uim.userinteractionproperties.filters;

import org.opaeum.uim.UimField;
import org.opaeum.uim.UserInteractionElement;

public class ColumOrFormFieldFilter extends AbstractFilter{
	@Override
	public boolean select(UserInteractionElement e){
		if(e instanceof UimField){
			return true;
		}
		return false;
	}
}
