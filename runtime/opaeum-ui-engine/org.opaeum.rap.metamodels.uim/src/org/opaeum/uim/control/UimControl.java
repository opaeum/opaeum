package org.opaeum.uim.control;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.component.UimField;

public interface UimControl extends EObject {
	public UimField getField();
	
	public String getMimumWidth();
	
	public Integer getMinimumHeight();
	
	public void setField(UimField field);
	
	public void setMimumWidth(String mimumWidth);
	
	public void setMinimumHeight(Integer minimumHeight);

}