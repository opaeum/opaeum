package org.opaeum.uim.constraint;

import org.opaeum.ecore.EObject;

public interface UserInteractionConstraint extends EObject, RootUserInteractionConstraint {
	public boolean getInheritFromParent();
	
	public void isInheritFromParent(boolean inheritFromParent);

}