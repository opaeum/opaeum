package org.opaeum.uim;

import java.util.Map;

import org.opaeum.ecore.EAnnotation;
import org.opaeum.ecore.EObject;
import org.w3c.dom.Element;

public interface Labels extends EObject, EAnnotation {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setUid(String uid);

}