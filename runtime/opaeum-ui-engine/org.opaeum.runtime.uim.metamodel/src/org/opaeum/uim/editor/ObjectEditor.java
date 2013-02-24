package org.opaeum.uim.editor;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.model.ClassUserInteractionModel;
import org.w3c.dom.Element;

public interface ObjectEditor extends EObject, InstanceEditor {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public ClassUserInteractionModel getModel();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setModel(ClassUserInteractionModel model);
	
	public void setUid(String uid);

}