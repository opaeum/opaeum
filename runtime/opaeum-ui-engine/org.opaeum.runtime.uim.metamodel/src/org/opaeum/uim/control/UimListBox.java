package org.opaeum.uim.control;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.w3c.dom.Element;

public interface UimListBox extends EObject, UimLookup {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public Integer getRows();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setRows(Integer rows);
	
	public void setUid(String uid);

}