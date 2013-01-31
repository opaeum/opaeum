package org.opaeum.uim.constraint;

import org.opaeum.ecore.EObject;

public class RequiredStateImpl implements RequiredState {
	private RootUserInteractionConstraint constraint;
	private String umlElementUid;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public RootUserInteractionConstraint getConstraint() {
		return this.constraint;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public void setConstraint(RootUserInteractionConstraint constraint) {
		this.constraint=constraint;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}

}