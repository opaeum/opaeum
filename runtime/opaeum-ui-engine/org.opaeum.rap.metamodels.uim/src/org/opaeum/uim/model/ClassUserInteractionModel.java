package org.opaeum.uim.model;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.cube.CubeQueryEditor;
import org.opaeum.uim.editor.ObjectEditor;
import org.opaeum.uim.wizard.NewObjectWizard;

public interface ClassUserInteractionModel extends EObject, AbstractUserInteractionModel {
	public CubeQueryEditor getCubeQueryEditor();
	
	public NewObjectWizard getNewObjectWizard();
	
	public ObjectEditor getPrimaryEditor();
	
	public List<ObjectEditor> getSecondaryEditors();
	
	public void setCubeQueryEditor(CubeQueryEditor cubeQueryEditor);
	
	public void setNewObjectWizard(NewObjectWizard newObjectWizard);
	
	public void setPrimaryEditor(ObjectEditor primaryEditor);
	
	public void setSecondaryEditors(List<ObjectEditor> secondaryEditors);

}