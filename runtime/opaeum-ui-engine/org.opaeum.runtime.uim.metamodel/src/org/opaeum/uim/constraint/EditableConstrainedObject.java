package org.opaeum.uim.constraint;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.w3c.dom.Element;

public interface EditableConstrainedObject extends EObject, ConstrainedObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public UserInteractionConstraint getEditability();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setEditability(UserInteractionConstraint editability);
	
	public void setUid(String uid);

}