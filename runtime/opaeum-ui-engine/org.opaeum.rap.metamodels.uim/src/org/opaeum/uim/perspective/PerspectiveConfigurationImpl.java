package org.opaeum.uim.perspective;

import org.opaeum.ecore.EObject;

public class PerspectiveConfigurationImpl implements PerspectiveConfiguration {
	private EditorConfiguration editor;
	private ExplorerConfiguration explorer;
	private InboxConfiguration inbox;
	private String name;
	private OutboxConfiguration outbox;
	private PropertiesConfiguration properties;
	private boolean underUserControl;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public EditorConfiguration getEditor() {
		return this.editor;
	}
	
	public ExplorerConfiguration getExplorer() {
		return this.explorer;
	}
	
	public InboxConfiguration getInbox() {
		return this.inbox;
	}
	
	public String getName() {
		return this.name;
	}
	
	public OutboxConfiguration getOutbox() {
		return this.outbox;
	}
	
	public PropertiesConfiguration getProperties() {
		return this.properties;
	}
	
	public boolean getUnderUserControl() {
		return this.underUserControl;
	}
	
	public void isUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setEditor(EditorConfiguration editor) {
		this.editor=editor;
	}
	
	public void setExplorer(ExplorerConfiguration explorer) {
		this.explorer=explorer;
	}
	
	public void setInbox(InboxConfiguration inbox) {
		this.inbox=inbox;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setOutbox(OutboxConfiguration outbox) {
		this.outbox=outbox;
	}
	
	public void setProperties(PropertiesConfiguration properties) {
		this.properties=properties;
	}

}