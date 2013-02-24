package org.opaeum.uim.model;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.editor.AbstractEditor;
import org.w3c.dom.Element;

public interface EmbeddedTaskEditor extends EObject, AbstractUserInteractionModel, AbstractEditor {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setUid(String uid);

}