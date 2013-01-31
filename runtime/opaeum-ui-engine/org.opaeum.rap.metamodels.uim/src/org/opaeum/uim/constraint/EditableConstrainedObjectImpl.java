package org.opaeum.uim.constraint;

import org.opaeum.ecore.EObject;

public class EditableConstrainedObjectImpl implements EditableConstrainedObject {
	private UserInteractionConstraint editability;
	private UserInteractionConstraint visibility;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public UserInteractionConstraint getEditability() {
		return this.editability;
	}
	
	public UserInteractionConstraint getVisibility() {
		return this.visibility;
	}
	
	public void setEditability(UserInteractionConstraint editability) {
		this.editability=editability;
	}
	
	public void setVisibility(UserInteractionConstraint visibility) {
		this.visibility=visibility;
	}

}