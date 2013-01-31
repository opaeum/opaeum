package org.opaeum.uim;

import org.opaeum.ecore.EObject;

public interface UmlReference extends EObject {
	public String getUmlElementUid();
	
	public void setUmlElementUid(String umlElementUid);

}