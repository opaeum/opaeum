package org.opaeum.uim.perspective;

import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.w3c.dom.Element;

public interface NavigatorConfiguration extends EObject, ViewAllocation {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public List<ClassNavigationConstraint> getClasses();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setClasses(List<ClassNavigationConstraint> classes);
	
	public void setUid(String uid);

}