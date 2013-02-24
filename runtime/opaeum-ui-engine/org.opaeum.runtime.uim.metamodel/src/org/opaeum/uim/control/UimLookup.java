package org.opaeum.uim.control;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.binding.LookupBinding;
import org.w3c.dom.Element;

public interface UimLookup extends EObject, UimControl {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public LookupBinding getLookupSource();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setLookupSource(LookupBinding lookupSource);
	
	public void setUid(String uid);

}