package org.opaeum.uim.control;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.editor.ObjectEditor;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface UimLinkControl extends EObject, UimControl {
	public void buildTreeFromXml(Element xml);
	
	public ObjectEditor getEditorToOpen();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setEditorToOpen(ObjectEditor editorToOpen);
	
	public void setUid(String uid);

}