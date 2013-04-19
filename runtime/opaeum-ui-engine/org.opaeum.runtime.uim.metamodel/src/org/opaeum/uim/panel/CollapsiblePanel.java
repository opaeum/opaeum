package org.opaeum.uim.panel;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface CollapsiblePanel extends EObject, Outlayable, AbstractPanel {
	public void buildTreeFromXml(Element xml);
	
	public Boolean getIsCollapsible();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setIsCollapsible(Boolean isCollapsible);
	
	public void setUid(String uid);

}