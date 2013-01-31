package org.opaeum.uim;

import org.opaeum.ecore.EObject;

public class UmlReferenceImpl implements UmlReference {
	private String umlElementUid;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}

}