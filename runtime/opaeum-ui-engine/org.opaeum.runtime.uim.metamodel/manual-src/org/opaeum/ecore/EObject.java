package org.opaeum.ecore;

import java.util.Map;

import org.w3c.dom.Element;

public interface EObject{
	EObject eContainer();
	String getUid();
	void setUid(String s);
	void populateReferencesFromXml(Element currentPropertyValueNode,Map<String,Object> map);
	public abstract void eContainer(EObject e);
	void buildTreeFromXml(Element currentPropertyValueNode,Map<String,Object> map);
}
