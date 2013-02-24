package org.opaeum.uim.panel;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.w3c.dom.Element;

public interface CollapsiblePanel extends EObject, Outlayable, AbstractPanel {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public Boolean getIsCollapsible();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setIsCollapsible(Boolean isCollapsible);
	
	public void setUid(String uid);

}