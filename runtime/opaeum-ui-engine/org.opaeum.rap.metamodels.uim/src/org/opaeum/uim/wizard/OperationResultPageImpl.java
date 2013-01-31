package org.opaeum.uim.wizard;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.Labels;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.opaeum.uim.model.OperationInvocationWizard;
import org.opaeum.uim.panel.AbstractPanel;

public class OperationResultPageImpl implements OperationResultPage {
	private UserInteractionConstraint editability;
	private Labels labelOverride;
	private String name;
	private AbstractPanel panel;
	private String umlElementUid;
	private boolean underUserControl;
	private UserInteractionConstraint visibility;
	private OperationInvocationWizard wizard;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public UserInteractionConstraint getEditability() {
		return this.editability;
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
	
	public OperationInvocationWizard getWizard() {
		return this.wizard;
	}
	
	public void isUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setEditability(UserInteractionConstraint editability) {
		this.editability=editability;
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
	
	public void setWizard(OperationInvocationWizard wizard) {
		this.wizard=wizard;
	}

}