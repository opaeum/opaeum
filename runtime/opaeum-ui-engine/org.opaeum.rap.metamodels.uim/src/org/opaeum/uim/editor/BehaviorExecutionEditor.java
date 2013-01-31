package org.opaeum.uim.editor;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.model.BehaviorUserInteractionModel;

public interface BehaviorExecutionEditor extends EObject, InstanceEditor {
	public BehaviorUserInteractionModel getModel();
	
	public void setModel(BehaviorUserInteractionModel model);

}