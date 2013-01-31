package org.opaeum.uim.binding;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UmlReference;

public interface PropertyRef extends EObject, UmlReference {
	public UimBinding getBinding();
	
	public PropertyRef getNext();
	
	public PropertyRef getPrevious();
	
	public void setBinding(UimBinding binding);
	
	public void setNext(PropertyRef next);
	
	public void setPrevious(PropertyRef previous);

}