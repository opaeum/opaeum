package org.opaeum.uim.userinteractionproperties.filters;

import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UserInteractionElement;

public class FormFieldFilter extends AbstractFilter{
	@Override
	public boolean select(UserInteractionElement e){
		if(e instanceof UimField){
			if(e.eContainer() instanceof UimDataTable){
				return false;
			}else{
				return true;
			}
		}
		return false;
	}
}
