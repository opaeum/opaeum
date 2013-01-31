package org.opaeum.uim;

import org.opaeum.ecore.EObject;

public interface UserInteractionElement extends EObject {
	public String getName();
	
	public boolean getUnderUserControl();
	
	public void isUnderUserControl(boolean underUserControl);
	
	public void setName(String name);

}