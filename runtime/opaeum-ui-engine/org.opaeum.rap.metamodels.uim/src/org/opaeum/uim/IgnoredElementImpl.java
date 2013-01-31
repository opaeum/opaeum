package org.opaeum.uim;

import org.opaeum.ecore.EObject;

public class IgnoredElementImpl implements IgnoredElement {
	private String umlElementUid;
	private UserInterfaceRoot userInterfaceRoot;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public UserInterfaceRoot getUserInterfaceRoot() {
		return this.userInterfaceRoot;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}
	
	public void setUserInterfaceRoot(UserInterfaceRoot userInterfaceRoot) {
		this.userInterfaceRoot=userInterfaceRoot;
	}

}