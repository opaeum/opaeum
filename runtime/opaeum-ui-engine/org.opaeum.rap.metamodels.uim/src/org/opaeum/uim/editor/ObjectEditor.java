package org.opaeum.uim.editor;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.model.ClassUserInteractionModel;

public interface ObjectEditor extends EObject, InstanceEditor {
	public ClassUserInteractionModel getModel();
	
	public void setModel(ClassUserInteractionModel model);

}