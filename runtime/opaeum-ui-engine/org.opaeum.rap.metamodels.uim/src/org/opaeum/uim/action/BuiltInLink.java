package org.opaeum.uim.action;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.Labels;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface BuiltInLink extends EObject, AbstractLink {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public BuiltInLinkKind getKind();
	
	public Labels getLabels();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setKind(BuiltInLinkKind kind);
	
	public void setLabels(Labels labels);
	
	public void setUid(String uid);

}