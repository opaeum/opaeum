package org.opaeum.uim.editor;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.model.BehaviorUserInteractionModel;
import org.w3c.dom.Element;

public interface BehaviorExecutionEditor extends EObject, InstanceEditor {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public BehaviorUserInteractionModel getModel();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setModel(BehaviorUserInteractionModel model);
	
	public void setUid(String uid);

}