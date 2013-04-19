package org.opaeum.uim.userinteractionproperties.filters;

import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.panel.GridPanel;

public class GridPanelFilter extends AbstractFilter{
	@Override
	public boolean select(UserInteractionElement e){
		return e instanceof GridPanel;
	}
}
