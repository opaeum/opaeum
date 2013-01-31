package org.opaeum.uim.action;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.Labels;

public interface BuiltInActionButton extends EObject, AbstractActionButton {
	public ActionKind getKind();
	
	public Labels getLabels();
	
	public void setKind(ActionKind kind);
	
	public void setLabels(Labels labels);

}