package org.opaeum.uim.component;

import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.w3c.dom.Element;

public interface UimContainer extends EObject, UimComponent, EditableConstrainedObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public List<UimComponent> getChildren();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setChildren(List<UimComponent> children);
	
	public void setUid(String uid);

}