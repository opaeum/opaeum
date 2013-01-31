package org.opaeum.uim.component;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.constraint.UserInteractionConstraint;

public class UimComponentImpl implements UimComponent {
	private String name;
	private boolean underUserControl;
	private UserInteractionConstraint visibility;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public String getName() {
		return this.name;
	}
	
	public UimContainer getParent() {
		UimContainer result = null;
		
		return result;
	}
	
	public boolean getUnderUserControl() {
		return this.underUserControl;
	}
	
	public UserInteractionConstraint getVisibility() {
		return this.visibility;
	}
	
	public void isUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setVisibility(UserInteractionConstraint visibility) {
		this.visibility=visibility;
	}

}