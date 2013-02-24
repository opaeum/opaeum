package org.opaeum.uim.component;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.LabelContainer;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.w3c.dom.Element;

public interface UimComponent extends EObject, UserInteractionElement, LabelContainer, ConstrainedObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public UimContainer getParent();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setUid(String uid);

}