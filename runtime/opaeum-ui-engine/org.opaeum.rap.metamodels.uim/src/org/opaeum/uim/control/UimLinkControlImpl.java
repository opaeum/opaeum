package org.opaeum.uim.control;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.component.UimField;
import org.opaeum.uim.editor.ObjectEditor;

public class UimLinkControlImpl implements UimLinkControl {
	private ObjectEditor editorToOpen;
	private UimField field;
	private String mimumWidth;
	private Integer minimumHeight;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public ObjectEditor getEditorToOpen() {
		return this.editorToOpen;
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
	
	public void setEditorToOpen(ObjectEditor editorToOpen) {
		this.editorToOpen=editorToOpen;
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

}