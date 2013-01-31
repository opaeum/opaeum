package org.opaeum.uim;

import org.opaeum.ecore.EObject;

public interface LabeledElement extends EObject, UmlReference, UserInteractionElement {
	public Labels getLabelOverride();
	
	public void setLabelOverride(Labels labelOverride);

}