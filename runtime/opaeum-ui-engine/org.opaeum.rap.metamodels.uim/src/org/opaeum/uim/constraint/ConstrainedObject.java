package org.opaeum.uim.constraint;

import org.opaeum.ecore.EObject;

public interface ConstrainedObject extends EObject {
	public UserInteractionConstraint getVisibility();
	
	public void setVisibility(UserInteractionConstraint visibility);

}