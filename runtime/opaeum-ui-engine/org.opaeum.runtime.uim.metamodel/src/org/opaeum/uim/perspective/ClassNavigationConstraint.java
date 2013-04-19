package org.opaeum.uim.perspective;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface ClassNavigationConstraint extends EObject, NavigationConstraint {
	public void buildTreeFromXml(Element xml);
	
	public List<BehaviorNavigationConstraint> getBehaviors();
	
	public NavigatorConfiguration getExplorerConfiguration();
	
	public List<OperationNavigationConstraint> getOperations();
	
	public List<PropertyNavigationConstraint> getProperties();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setBehaviors(List<BehaviorNavigationConstraint> behaviors);
	
	public void setExplorerConfiguration(NavigatorConfiguration explorerConfiguration);
	
	public void setOperations(List<OperationNavigationConstraint> operations);
	
	public void setProperties(List<PropertyNavigationConstraint> properties);
	
	public void setUid(String uid);

}