package org.opaeum.uim.model;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.cube.CubeQueryEditor;
import org.opaeum.uim.editor.ObjectEditor;
import org.opaeum.uim.wizard.NewObjectWizard;

public class ClassUserInteractionModelImpl implements ClassUserInteractionModel {
	private CubeQueryEditor cubeQueryEditor;
	private String linkedUmlResource;
	private String name;
	private NewObjectWizard newObjectWizard;
	private ObjectEditor primaryEditor;
	private List<ObjectEditor> secondaryEditors;
	private String umlElementUid;
	private boolean underUserControl;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public CubeQueryEditor getCubeQueryEditor() {
		return this.cubeQueryEditor;
	}
	
	public String getLinkedUmlResource() {
		return this.linkedUmlResource;
	}
	
	public String getName() {
		return this.name;
	}
	
	public NewObjectWizard getNewObjectWizard() {
		return this.newObjectWizard;
	}
	
	public ObjectEditor getPrimaryEditor() {
		return this.primaryEditor;
	}
	
	public List<ObjectEditor> getSecondaryEditors() {
		return this.secondaryEditors;
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
	
	public void setCubeQueryEditor(CubeQueryEditor cubeQueryEditor) {
		this.cubeQueryEditor=cubeQueryEditor;
	}
	
	public void setLinkedUmlResource(String linkedUmlResource) {
		this.linkedUmlResource=linkedUmlResource;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setNewObjectWizard(NewObjectWizard newObjectWizard) {
		this.newObjectWizard=newObjectWizard;
	}
	
	public void setPrimaryEditor(ObjectEditor primaryEditor) {
		this.primaryEditor=primaryEditor;
	}
	
	public void setSecondaryEditors(List<ObjectEditor> secondaryEditors) {
		this.secondaryEditors=secondaryEditors;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}

}