package org.opaeum.uim.model;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.editor.BehaviorExecutionEditor;
import org.opaeum.uim.wizard.BehaviorInvocationWizard;

public interface BehaviorUserInteractionModel extends EObject, AbstractUserInteractionModel {
	public BehaviorExecutionEditor getEditor();
	
	public BehaviorInvocationWizard getInvocationWizard();
	
	public void setEditor(BehaviorExecutionEditor editor);
	
	public void setInvocationWizard(BehaviorInvocationWizard invocationWizard);

}