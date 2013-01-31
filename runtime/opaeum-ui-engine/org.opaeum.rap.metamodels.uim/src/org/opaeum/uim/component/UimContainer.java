package org.opaeum.uim.component;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.constraint.EditableConstrainedObject;

public interface UimContainer extends EObject, UimComponent, EditableConstrainedObject {
	public List<UimComponent> getChildren();
	
	public void setChildren(List<UimComponent> children);

}