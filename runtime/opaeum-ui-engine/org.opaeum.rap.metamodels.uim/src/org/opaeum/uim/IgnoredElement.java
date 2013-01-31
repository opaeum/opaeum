package org.opaeum.uim;

import org.opaeum.ecore.EObject;

public interface IgnoredElement extends EObject, UmlReference {
	public UserInterfaceRoot getUserInterfaceRoot();
	
	public void setUserInterfaceRoot(UserInterfaceRoot userInterfaceRoot);

}