package org.opaeum.uim.panel;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.component.UimContainer;
import org.w3c.dom.Element;

public interface AbstractPanel extends EObject, UimContainer, LabeledElement {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setUid(String uid);

}