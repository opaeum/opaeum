package org.opaeum.uim.perspective;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface OperationNavigationConstraint extends EObject, NavigationConstraint {
	public void buildTreeFromXml(Element xml);
	
	public NavigationConstraint getInvocationConstraint();
	
	public ClassNavigationConstraint getOwner();
	
	public List<ParameterNavigationConstraint> getParameters();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setInvocationConstraint(NavigationConstraint invocationConstraint);
	
	public void setOwner(ClassNavigationConstraint owner);
	
	public void setParameters(List<ParameterNavigationConstraint> parameters);
	
	public void setUid(String uid);

}