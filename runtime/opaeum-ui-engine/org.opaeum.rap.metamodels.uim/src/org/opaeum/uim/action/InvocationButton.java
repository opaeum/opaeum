package org.opaeum.uim.action;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.UserInterfaceRoot;

public interface InvocationButton extends EObject, AbstractActionButton, LabeledElement {
	public UserInterfaceRoot getPopup();
	
	public void setPopup(UserInterfaceRoot popup);

}