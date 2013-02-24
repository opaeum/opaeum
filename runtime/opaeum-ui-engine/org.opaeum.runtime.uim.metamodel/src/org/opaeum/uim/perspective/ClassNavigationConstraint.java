package org.opaeum.uim.perspective;

import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.w3c.dom.Element;

public interface ClassNavigationConstraint extends EObject, NavigationConstraint {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public List<BehaviorNavigationConstraint> getBehaviors();
	
	public NavigatorConfiguration getExplorerConfiguration();
	
	public List<OperationNavigationConstraint> getOperations();
	
	public List<PropertyNavigationConstraint> getProperties();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setBehaviors(List<BehaviorNavigationConstraint> behaviors);
	
	public void setExplorerConfiguration(NavigatorConfiguration explorerConfiguration);
	
	public void setOperations(List<OperationNavigationConstraint> operations);
	
	public void setProperties(List<PropertyNavigationConstraint> properties);
	
	public void setUid(String uid);

}