package org.opaeum.uim.editor;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UserInteractionElement;

public interface MenuConfiguration extends EObject, UserInteractionElement {
	public InstanceEditor getEditor();
	
	public List<OperationMenuItem> getOperations();
	
	public void setEditor(InstanceEditor editor);
	
	public void setOperations(List<OperationMenuItem> operations);

}