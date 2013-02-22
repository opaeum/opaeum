package org.opaeum.uim.model;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface AbstractUserInteractionModel extends EObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public String getLinkedUmlResource();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setLinkedUmlResource(String linkedUmlResource);
	
	public void setUid(String uid);

}