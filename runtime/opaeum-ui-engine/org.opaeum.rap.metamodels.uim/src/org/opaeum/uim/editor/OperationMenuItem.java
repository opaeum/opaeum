package org.opaeum.uim.editor;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.constraint.UserInteractionConstraint;

public interface OperationMenuItem extends EObject, UserInteractionConstraint, LabeledElement {
	public boolean getHidden();
	
	public MenuConfiguration getMenuConfiguration();
	
	public void isHidden(boolean hidden);
	
	public void setMenuConfiguration(MenuConfiguration menuConfiguration);

}