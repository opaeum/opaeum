package org.opaeum.uim.binding;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.UmlReference;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface UimBinding extends EObject, UmlReference {
	public void buildTreeFromXml(Element xml);
	
	public String getLastPropertyUuid();
	
	public PropertyRef getNext();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setNext(PropertyRef next);
	
	public void setUid(String uid);

}