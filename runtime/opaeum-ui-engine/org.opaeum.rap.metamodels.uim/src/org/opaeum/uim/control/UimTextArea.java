package org.opaeum.uim.control;

import org.opaeum.ecore.EObject;

public interface UimTextArea extends EObject, UimControl {
	public Integer getRows();
	
	public void setRows(Integer rows);

}