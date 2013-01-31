package org.opaeum.uim.editor;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.Labels;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.opaeum.uim.panel.AbstractPanel;

public class EditorPageImpl implements EditorPage {
	private UserInteractionConstraint editability;
	private AbstractEditor editor;
	private Labels labelOverride;
	private String name;
	private AbstractPanel panel;
	private String umlElementUid;
	private boolean underUserControl;
	private UserInteractionConstraint visibility;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public UserInteractionConstraint getEditability() {
		return this.editability;
	}
	
	public AbstractEditor getEditor() {
		return this.editor;
	}
	
	public Labels getLabelOverride() {
		return this.labelOverride;
	}
	
	public String getName() {
		return this.name;
	}
	
	public AbstractPanel getPanel() {
		return this.panel;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public boolean getUnderUserControl() {
		return this.underUserControl;
	}
	
	public UserInteractionConstraint getVisibility() {
		return this.visibility;
	}
	
	public void isUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setEditability(UserInteractionConstraint editability) {
		this.editability=editability;
	}
	
	public void setEditor(AbstractEditor editor) {
		this.editor=editor;
	}
	
	public void setLabelOverride(Labels labelOverride) {
		this.labelOverride=labelOverride;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setPanel(AbstractPanel panel) {
		this.panel=panel;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}
	
	public void setVisibility(UserInteractionConstraint visibility) {
		this.visibility=visibility;
	}

}