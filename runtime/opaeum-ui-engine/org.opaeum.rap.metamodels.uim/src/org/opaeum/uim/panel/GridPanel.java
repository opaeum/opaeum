package org.opaeum.uim.panel;

import org.opaeum.ecore.EObject;

public interface GridPanel extends EObject, CollapsiblePanel {
	public Integer getNumberOfColumns();
	
	public void setNumberOfColumns(Integer numberOfColumns);

}