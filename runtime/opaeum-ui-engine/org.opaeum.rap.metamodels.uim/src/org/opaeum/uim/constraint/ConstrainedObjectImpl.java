package org.opaeum.uim.constraint;

import org.opaeum.ecore.EObject;

public class ConstrainedObjectImpl implements ConstrainedObject {
	private UserInteractionConstraint visibility;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public UserInteractionConstraint getVisibility() {
		return this.visibility;
	}
	
	public void setVisibility(UserInteractionConstraint visibility) {
		this.visibility=visibility;
	}

}