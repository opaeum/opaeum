package org.opaeum.uim.control;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.component.UimField;
import org.w3c.dom.Element;

public interface UimControl extends EObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public UimField getField();
	
	public String getMimumWidth();
	
	public Integer getMinimumHeight();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setField(UimField field);
	
	public void setMimumWidth(String mimumWidth);
	
	public void setMinimumHeight(Integer minimumHeight);
	
	public void setUid(String uid);

}