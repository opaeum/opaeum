package org.opaeum.uim.perspective;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.UserInteractionElement;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface PerspectiveConfiguration extends EObject, UserInteractionElement {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public EditorConfiguration getEditor();
	
	public ExplorerConfiguration getExplorer();
	
	public InboxConfiguration getInbox();
	
	public OutboxConfiguration getOutbox();
	
	public PropertiesConfiguration getProperties();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setEditor(EditorConfiguration editor);
	
	public void setExplorer(ExplorerConfiguration explorer);
	
	public void setInbox(InboxConfiguration inbox);
	
	public void setOutbox(OutboxConfiguration outbox);
	
	public void setProperties(PropertiesConfiguration properties);
	
	public void setUid(String uid);

}