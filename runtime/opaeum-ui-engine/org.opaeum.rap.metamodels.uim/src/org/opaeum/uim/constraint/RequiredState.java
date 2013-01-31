package org.opaeum.uim.constraint;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UmlReference;

public interface RequiredState extends EObject, UmlReference {
	public RootUserInteractionConstraint getConstraint();
	
	public void setConstraint(RootUserInteractionConstraint constraint);

}