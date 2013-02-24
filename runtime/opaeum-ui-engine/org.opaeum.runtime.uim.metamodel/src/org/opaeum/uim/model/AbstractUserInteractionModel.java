package org.opaeum.uim.model;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.w3c.dom.Element;

public interface AbstractUserInteractionModel extends EObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public String getLinkedUmlResource();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setLinkedUmlResource(String linkedUmlResource);
	
	public void setUid(String uid);

}