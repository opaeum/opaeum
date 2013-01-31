package org.opaeum.uim.perspective;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UserInteractionElement;

public interface PerspectiveConfiguration extends EObject, UserInteractionElement {
	public EditorConfiguration getEditor();
	
	public ExplorerConfiguration getExplorer();
	
	public InboxConfiguration getInbox();
	
	public OutboxConfiguration getOutbox();
	
	public PropertiesConfiguration getProperties();
	
	public void setEditor(EditorConfiguration editor);
	
	public void setExplorer(ExplorerConfiguration explorer);
	
	public void setInbox(InboxConfiguration inbox);
	
	public void setOutbox(OutboxConfiguration outbox);
	
	public void setProperties(PropertiesConfiguration properties);

}