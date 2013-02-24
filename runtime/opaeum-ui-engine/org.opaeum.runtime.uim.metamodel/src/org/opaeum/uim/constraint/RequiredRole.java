package org.opaeum.uim.constraint;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UmlReference;
import org.w3c.dom.Element;

public interface RequiredRole extends EObject, UmlReference {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public RootUserInteractionConstraint getConstraint();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setConstraint(RootUserInteractionConstraint constraint);
	
	public void setUid(String uid);

}