package org.opaeum.uim.constraint;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.w3c.dom.Element;

public interface ConstrainedObject extends EObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public String getUid();
	
	public UserInteractionConstraint getVisibility();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setUid(String uid);
	
	public void setVisibility(UserInteractionConstraint visibility);

}