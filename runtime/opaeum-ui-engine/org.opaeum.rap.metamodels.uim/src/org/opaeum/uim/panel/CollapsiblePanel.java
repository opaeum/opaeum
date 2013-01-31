package org.opaeum.uim.panel;

import org.opaeum.ecore.EObject;

public interface CollapsiblePanel extends EObject, Outlayable, AbstractPanel {
	public Boolean getIsCollapsible();
	
	public void setIsCollapsible(Boolean isCollapsible);

}