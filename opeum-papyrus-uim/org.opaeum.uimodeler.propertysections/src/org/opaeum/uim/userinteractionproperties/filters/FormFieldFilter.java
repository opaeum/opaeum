package org.opaeum.uim.userinteractionproperties.filters;

import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uim.component.UimField;

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
