package org.opaeum.uim.model;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.editor.BehaviorExecutionEditor;
import org.opaeum.uim.wizard.BehaviorInvocationWizard;
import org.w3c.dom.Element;

public interface BehaviorUserInteractionModel extends EObject, AbstractUserInteractionModel {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public BehaviorExecutionEditor getEditor();
	
	public BehaviorInvocationWizard getInvocationWizard();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setEditor(BehaviorExecutionEditor editor);
	
	public void setInvocationWizard(BehaviorInvocationWizard invocationWizard);
	
	public void setUid(String uid);

}