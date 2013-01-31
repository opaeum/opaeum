package org.opaeum.uim.binding;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.control.UimLookup;

public interface LookupBinding extends EObject, UimBinding {
	public UimLookup getLookup();
	
	public void setLookup(UimLookup lookup);

}