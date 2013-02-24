package org.opaeum.uim.binding;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UmlReference;
import org.w3c.dom.Element;

public interface PropertyRef extends EObject, UmlReference {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public UimBinding getBinding();
	
	public PropertyRef getNext();
	
	public PropertyRef getPrevious();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setBinding(UimBinding binding);
	
	public void setNext(PropertyRef next);
	
	public void setPrevious(PropertyRef previous);
	
	public void setUid(String uid);

}