package org.opaeum.uim.perspective;

import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface ExplorerConfiguration extends EObject, ViewAllocation {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public List<ExplorerClassConstraint> getClasses();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setClasses(List<ExplorerClassConstraint> classes);
	
	public void setUid(String uid);

}