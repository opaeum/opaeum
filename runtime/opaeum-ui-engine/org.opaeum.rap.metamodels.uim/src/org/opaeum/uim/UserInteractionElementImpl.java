package org.opaeum.uim;

import org.opaeum.ecore.EObject;

public class UserInteractionElementImpl implements UserInteractionElement {
	private String name;
	private boolean underUserControl;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean getUnderUserControl() {
		return this.underUserControl;
	}
	
	public void isUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setName(String name) {
		this.name=name;
	}

}