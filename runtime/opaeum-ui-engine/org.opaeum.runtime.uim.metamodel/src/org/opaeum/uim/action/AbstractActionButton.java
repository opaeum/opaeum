package org.opaeum.uim.action;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.panel.Outlayable;
import org.w3c.dom.Element;

public interface AbstractActionButton extends EObject, UimComponent, Outlayable {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setUid(String uid);

}