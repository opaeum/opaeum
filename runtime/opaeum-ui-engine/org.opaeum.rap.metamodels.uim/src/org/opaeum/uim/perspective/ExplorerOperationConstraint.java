package org.opaeum.uim.perspective;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface ExplorerOperationConstraint extends EObject, ExplorerConstraint {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public ExplorerClassConstraint getOwner();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setOwner(ExplorerClassConstraint owner);
	
	public void setUid(String uid);

}