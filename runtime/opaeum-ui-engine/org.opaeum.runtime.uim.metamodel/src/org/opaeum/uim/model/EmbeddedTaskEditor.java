package org.opaeum.uim.model;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.editor.AbstractEditor;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface EmbeddedTaskEditor extends EObject, AbstractUserInteractionModel, AbstractEditor {
	public void buildTreeFromXml(Element xml);
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setUid(String uid);

}