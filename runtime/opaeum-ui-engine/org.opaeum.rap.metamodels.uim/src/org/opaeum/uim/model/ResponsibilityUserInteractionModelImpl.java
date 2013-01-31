package org.opaeum.uim.model;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.editor.ResponsibilityViewer;
import org.opaeum.uim.wizard.ResponsibilityInvocationWizard;

public class ResponsibilityUserInteractionModelImpl implements ResponsibilityUserInteractionModel {
	private ResponsibilityInvocationWizard invocationWizard;
	private String linkedUmlResource;
	private String name;
	private String umlElementUid;
	private boolean underUserControl;
	private ResponsibilityViewer viewer;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public ResponsibilityInvocationWizard getInvocationWizard() {
		return this.invocationWizard;
	}
	
	public String getLinkedUmlResource() {
		return this.linkedUmlResource;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public boolean getUnderUserControl() {
		return this.underUserControl;
	}
	
	public ResponsibilityViewer getViewer() {
		return this.viewer;
	}
	
	public void isUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setInvocationWizard(ResponsibilityInvocationWizard invocationWizard) {
		this.invocationWizard=invocationWizard;
	}
	
	public void setLinkedUmlResource(String linkedUmlResource) {
		this.linkedUmlResource=linkedUmlResource;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}
	
	public void setViewer(ResponsibilityViewer viewer) {
		this.viewer=viewer;
	}

}