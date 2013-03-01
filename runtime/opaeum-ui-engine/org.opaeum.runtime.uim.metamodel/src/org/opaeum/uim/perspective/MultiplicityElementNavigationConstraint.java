package org.opaeum.uim.perspective;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface MultiplicityElementNavigationConstraint extends EObject, NavigationConstraint {
	public void buildTreeFromXml(Element xml);
	
	public NavigationConstraint getAddConstraint();
	
	public NavigationConstraint getRemoveConstraint();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setAddConstraint(NavigationConstraint addConstraint);
	
	public void setRemoveConstraint(NavigationConstraint removeConstraint);
	
	public void setUid(String uid);

}