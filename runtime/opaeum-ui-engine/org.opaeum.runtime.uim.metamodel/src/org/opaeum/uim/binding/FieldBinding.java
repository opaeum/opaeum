package org.opaeum.uim.binding;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.component.UimField;
import org.w3c.dom.Element;

public interface FieldBinding extends EObject, UimBinding {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public UimField getField();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setField(UimField field);
	
	public void setUid(String uid);

}