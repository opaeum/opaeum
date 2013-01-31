package org.opaeum.uim.binding;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UmlReference;

public interface UimBinding extends EObject, UmlReference {
	public String getLastPropertyUuid();
	
	public PropertyRef getNext();
	
	public void setNext(PropertyRef next);

}