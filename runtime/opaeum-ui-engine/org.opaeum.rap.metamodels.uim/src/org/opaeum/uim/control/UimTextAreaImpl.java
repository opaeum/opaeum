package org.opaeum.uim.control;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.component.UimField;

public class UimTextAreaImpl implements UimTextArea {
	private UimField field;
	private String mimumWidth;
	private Integer minimumHeight;
	private Integer rows;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public UimField getField() {
		return this.field;
	}
	
	public String getMimumWidth() {
		return this.mimumWidth;
	}
	
	public Integer getMinimumHeight() {
		return this.minimumHeight;
	}
	
	public Integer getRows() {
		return this.rows;
	}
	
	public void setField(UimField field) {
		this.field=field;
	}
	
	public void setMimumWidth(String mimumWidth) {
		this.mimumWidth=mimumWidth;
	}
	
	public void setMinimumHeight(Integer minimumHeight) {
		this.minimumHeight=minimumHeight;
	}
	
	public void setRows(Integer rows) {
		this.rows=rows;
	}

}