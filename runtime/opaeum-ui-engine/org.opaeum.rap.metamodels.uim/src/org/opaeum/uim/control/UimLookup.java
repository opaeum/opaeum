package org.opaeum.uim.control;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.binding.LookupBinding;

public interface UimLookup extends EObject, UimControl {
	public LookupBinding getLookupSource();
	
	public void setLookupSource(LookupBinding lookupSource);

}