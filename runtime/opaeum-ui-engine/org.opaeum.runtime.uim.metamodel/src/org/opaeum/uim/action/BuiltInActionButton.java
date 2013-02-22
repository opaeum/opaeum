package org.opaeum.uim.action;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.Labels;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface BuiltInActionButton extends EObject, AbstractActionButton {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public ActionKind getKind();
	
	public Labels getLabels();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setKind(ActionKind kind);
	
	public void setLabels(Labels labels);
	
	public void setUid(String uid);

}