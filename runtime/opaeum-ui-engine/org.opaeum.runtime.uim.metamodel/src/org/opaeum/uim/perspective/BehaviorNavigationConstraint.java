package org.opaeum.uim.perspective;

import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.w3c.dom.Element;

public interface BehaviorNavigationConstraint extends EObject, NavigationConstraint {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public NavigationConstraint getInvocationConstraint();
	
	public ClassNavigationConstraint getOwner();
	
	public List<ParameterNavigationConstraint> getParameters();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setInvocationConstraint(NavigationConstraint invocationConstraint);
	
	public void setOwner(ClassNavigationConstraint owner);
	
	public void setParameters(List<ParameterNavigationConstraint> parameters);
	
	public void setUid(String uid);

}