package org.opaeum.uim.perspective;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface NavigationConstraint extends EObject, UserInteractionConstraint, LabeledElement {
	public void buildTreeFromXml(Element xml);
	
	public String getUid();
	
	public boolean isHidden();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setHidden(boolean hidden);
	
	public void setUid(String uid);

}