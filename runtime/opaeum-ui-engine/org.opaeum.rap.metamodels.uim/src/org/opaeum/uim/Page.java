package org.opaeum.uim;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.panel.AbstractPanel;

public interface Page extends EObject, EditableConstrainedObject, LabeledElement {
	public AbstractPanel getPanel();
	
	public void setPanel(AbstractPanel panel);

}