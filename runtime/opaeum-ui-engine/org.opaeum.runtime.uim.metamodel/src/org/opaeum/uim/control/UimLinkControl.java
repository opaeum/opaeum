package org.opaeum.uim.control;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.editor.ObjectEditor;
import org.w3c.dom.Element;

public interface UimLinkControl extends EObject, UimControl {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public ObjectEditor getEditorToOpen();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setEditorToOpen(ObjectEditor editorToOpen);
	
	public void setUid(String uid);

}