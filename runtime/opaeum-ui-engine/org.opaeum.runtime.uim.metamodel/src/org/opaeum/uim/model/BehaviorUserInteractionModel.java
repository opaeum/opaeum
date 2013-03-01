package org.opaeum.uim.model;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.editor.BehaviorExecutionEditor;
import org.opaeum.uim.wizard.BehaviorInvocationWizard;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface BehaviorUserInteractionModel extends EObject, AbstractUserInteractionModel {
	public void buildTreeFromXml(Element xml);
	
	public BehaviorExecutionEditor getEditor();
	
	public BehaviorInvocationWizard getInvocationWizard();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setEditor(BehaviorExecutionEditor editor);
	
	public void setInvocationWizard(BehaviorInvocationWizard invocationWizard);
	
	public void setUid(String uid);

}