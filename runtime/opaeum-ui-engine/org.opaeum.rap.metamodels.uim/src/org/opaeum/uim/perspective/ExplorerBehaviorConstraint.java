package org.opaeum.uim.perspective;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.constraint.UserInteractionConstraint;

public interface ExplorerBehaviorConstraint extends EObject, ExplorerConstraint {
	public UserInteractionConstraint getInvocationConstraint();
	
	public ExplorerClassConstraint getOwner();
	
	public void setInvocationConstraint(UserInteractionConstraint invocationConstraint);
	
	public void setOwner(ExplorerClassConstraint owner);

}