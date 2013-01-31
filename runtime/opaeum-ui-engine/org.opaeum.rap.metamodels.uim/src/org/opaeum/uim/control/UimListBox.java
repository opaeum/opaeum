package org.opaeum.uim.control;

import org.opaeum.ecore.EObject;

public interface UimListBox extends EObject, UimLookup {
	public Integer getRows();
	
	public void setRows(Integer rows);

}