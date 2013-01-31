package org.opaeum.uim.binding;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.component.UimField;

public interface FieldBinding extends EObject, UimBinding {
	public UimField getField();
	
	public void setField(UimField field);

}