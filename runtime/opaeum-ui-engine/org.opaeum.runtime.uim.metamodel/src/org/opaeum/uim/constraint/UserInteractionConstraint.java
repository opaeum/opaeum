package org.opaeum.uim.constraint;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.w3c.dom.Element;

public interface UserInteractionConstraint extends EObject, RootUserInteractionConstraint {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public String getUid();
	
	public boolean isInheritFromParent();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setInheritFromParent(boolean inheritFromParent);
	
	public void setUid(String uid);

}