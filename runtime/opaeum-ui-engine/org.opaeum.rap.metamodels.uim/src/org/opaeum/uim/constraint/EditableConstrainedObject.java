package org.opaeum.uim.constraint;

import org.opaeum.ecore.EObject;

public interface EditableConstrainedObject extends EObject, ConstrainedObject {
	public UserInteractionConstraint getEditability();
	
	public void setEditability(UserInteractionConstraint editability);

}