package org.opaeum.ecore;

import org.opaeum.runtime.rwt.AbstractUimResource;
import org.w3c.dom.Element;

public interface EObject{
	EObject eContainer();
	String getUid();
	void setUid(String s);
	void populateReferencesFromXml(Element currentPropertyValueNode);
	public abstract void init(EObject e, AbstractUimResource resource, Element el);
	public abstract AbstractUimResource eResource();
	void buildTreeFromXml(Element currentPropertyValueNode);
}
