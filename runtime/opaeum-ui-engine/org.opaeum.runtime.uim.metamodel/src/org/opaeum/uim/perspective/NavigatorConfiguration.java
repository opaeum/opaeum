package org.opaeum.uim.perspective;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface NavigatorConfiguration extends EObject, ViewAllocation {
	public void buildTreeFromXml(Element xml);
	
	public List<ClassNavigationConstraint> getClasses();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setClasses(List<ClassNavigationConstraint> classes);
	
	public void setUid(String uid);

}