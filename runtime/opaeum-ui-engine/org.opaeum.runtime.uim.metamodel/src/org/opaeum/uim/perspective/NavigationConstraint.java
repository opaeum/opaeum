package org.opaeum.uim.perspective;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.w3c.dom.Element;

public interface NavigationConstraint extends EObject, UserInteractionConstraint, LabeledElement {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public String getUid();
	
	public boolean isHidden();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setHidden(boolean hidden);
	
	public void setUid(String uid);

}