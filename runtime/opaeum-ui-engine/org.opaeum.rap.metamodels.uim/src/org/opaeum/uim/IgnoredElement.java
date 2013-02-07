package org.opaeum.uim;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface IgnoredElement extends EObject, UmlReference {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public String getUid();
	
	public UserInterfaceRoot getUserInterfaceRoot();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setUid(String uid);
	
	public void setUserInterfaceRoot(UserInterfaceRoot userInterfaceRoot);

}