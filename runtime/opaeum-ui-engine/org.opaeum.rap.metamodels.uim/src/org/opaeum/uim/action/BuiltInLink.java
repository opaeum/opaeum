package org.opaeum.uim.action;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.Labels;

public interface BuiltInLink extends EObject, AbstractLink {
	public BuiltInLinkKind getKind();
	
	public Labels getLabels();
	
	public void setKind(BuiltInLinkKind kind);
	
	public void setLabels(Labels labels);

}