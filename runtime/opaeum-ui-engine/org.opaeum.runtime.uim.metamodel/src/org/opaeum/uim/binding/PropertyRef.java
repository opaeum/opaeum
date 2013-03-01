package org.opaeum.uim.binding;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.UmlReference;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface PropertyRef extends EObject, UmlReference {
	public void buildTreeFromXml(Element xml);
	
	public UimBinding getBinding();
	
	public PropertyRef getNext();
	
	public PropertyRef getPrevious();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setBinding(UimBinding binding);
	
	public void setNext(PropertyRef next);
	
	public void setPrevious(PropertyRef previous);
	
	public void setUid(String uid);

}