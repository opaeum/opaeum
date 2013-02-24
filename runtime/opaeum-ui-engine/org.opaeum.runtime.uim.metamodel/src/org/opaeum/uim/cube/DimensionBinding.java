package org.opaeum.uim.cube;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.binding.UimBinding;
import org.w3c.dom.Element;

public interface DimensionBinding extends EObject, UimBinding {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setUid(String uid);
	
	public String toString();

}