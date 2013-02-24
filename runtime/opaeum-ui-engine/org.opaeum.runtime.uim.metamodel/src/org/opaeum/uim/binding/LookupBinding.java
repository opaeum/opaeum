package org.opaeum.uim.binding;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.control.UimLookup;
import org.w3c.dom.Element;

public interface LookupBinding extends EObject, UimBinding {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public UimLookup getLookup();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setLookup(UimLookup lookup);
	
	public void setUid(String uid);

}