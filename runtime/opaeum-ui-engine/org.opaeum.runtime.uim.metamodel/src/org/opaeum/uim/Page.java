package org.opaeum.uim;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.panel.AbstractPanel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface Page extends EObject, EditableConstrainedObject, LabeledElement {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public AbstractPanel getPanel();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setPanel(AbstractPanel panel);
	
	public void setUid(String uid);

}