package org.opaeum.uim.component;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.constraint.ConstrainedObject;

public interface UimComponent extends EObject, UserInteractionElement, ConstrainedObject {
	public UimContainer getParent();

}