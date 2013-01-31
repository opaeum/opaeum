package org.opaeum.uim.editor;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.model.ResponsibilityUserInteractionModel;

public interface ResponsibilityViewer extends EObject, AbstractEditor {
	public ResponsibilityUserInteractionModel getModel();
	
	public void setModel(ResponsibilityUserInteractionModel model);

}