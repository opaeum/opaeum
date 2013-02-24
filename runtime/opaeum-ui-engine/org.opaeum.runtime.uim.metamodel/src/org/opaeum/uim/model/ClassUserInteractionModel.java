package org.opaeum.uim.model;

import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.cube.CubeQueryEditor;
import org.opaeum.uim.editor.ObjectEditor;
import org.opaeum.uim.wizard.NewObjectWizard;
import org.w3c.dom.Element;

public interface ClassUserInteractionModel extends EObject, AbstractUserInteractionModel {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public CubeQueryEditor getCubeQueryEditor();
	
	public NewObjectWizard getNewObjectWizard();
	
	public ObjectEditor getPrimaryEditor();
	
	public List<ObjectEditor> getSecondaryEditors();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setCubeQueryEditor(CubeQueryEditor cubeQueryEditor);
	
	public void setNewObjectWizard(NewObjectWizard newObjectWizard);
	
	public void setPrimaryEditor(ObjectEditor primaryEditor);
	
	public void setSecondaryEditors(List<ObjectEditor> secondaryEditors);
	
	public void setUid(String uid);

}