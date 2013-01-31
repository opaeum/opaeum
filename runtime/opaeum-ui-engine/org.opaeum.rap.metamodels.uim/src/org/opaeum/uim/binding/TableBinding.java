package org.opaeum.uim.binding;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.component.UimDataTable;

public interface TableBinding extends EObject, UimBinding {
	public UimDataTable getTable();
	
	public void setTable(UimDataTable table);

}