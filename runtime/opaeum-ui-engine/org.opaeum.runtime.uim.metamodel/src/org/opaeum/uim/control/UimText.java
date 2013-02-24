package org.opaeum.uim.control;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.w3c.dom.Element;

public interface UimText extends EObject, UimControl {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setUid(String uid);

}