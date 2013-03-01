package org.opaeum.uim.editor;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.panel.AbstractPanel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface ActionBar extends EObject, AbstractPanel {
	public void buildTreeFromXml(Element xml);
	
	public AbstractEditor getEditor();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setEditor(AbstractEditor editor);
	
	public void setUid(String uid);

}