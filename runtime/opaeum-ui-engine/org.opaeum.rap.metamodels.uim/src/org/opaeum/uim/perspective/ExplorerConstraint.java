package org.opaeum.uim.perspective;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.constraint.UserInteractionConstraint;

public interface ExplorerConstraint extends EObject, UserInteractionConstraint, LabeledElement {
	public boolean getHidden();
	
	public void isHidden(boolean hidden);

}