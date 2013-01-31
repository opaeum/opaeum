package org.opaeum.uim.model;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.editor.BehaviorExecutionEditor;
import org.opaeum.uim.wizard.BehaviorInvocationWizard;

public class BehaviorUserInteractionModelImpl implements BehaviorUserInteractionModel {
	private BehaviorExecutionEditor editor;
	private BehaviorInvocationWizard invocationWizard;
	private String linkedUmlResource;
	private String name;
	private String umlElementUid;
	private boolean underUserControl;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public BehaviorExecutionEditor getEditor() {
		return this.editor;
	}
	
	public BehaviorInvocationWizard getInvocationWizard() {
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
	
	public void isUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setEditor(BehaviorExecutionEditor editor) {
		this.editor=editor;
	}
	
	public void setInvocationWizard(BehaviorInvocationWizard invocationWizard) {
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

}