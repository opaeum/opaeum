package org.opaeum.uim.perspective;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.w3c.dom.Element;

public interface MultiplicityElementNavigationConstraint extends EObject, NavigationConstraint {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public NavigationConstraint getAddConstraint();
	
	public NavigationConstraint getRemoveConstraint();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setAddConstraint(NavigationConstraint addConstraint);
	
	public void setRemoveConstraint(NavigationConstraint removeConstraint);
	
	public void setUid(String uid);

}