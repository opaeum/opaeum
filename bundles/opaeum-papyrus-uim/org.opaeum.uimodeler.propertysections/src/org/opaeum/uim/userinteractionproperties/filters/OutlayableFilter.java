package org.opaeum.uim.userinteractionproperties.filters;

import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uim.panel.Outlayable;

public class OutlayableFilter extends AbstractFilter{

	@Override
	public boolean select(UserInteractionElement e){
		return e instanceof Outlayable && !(e.eContainer() instanceof UimDataTable);
	}
}
