package org.opaeum.uim.perspective;

import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface ExplorerClassConstraint extends EObject, ExplorerConstraint {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public List<ExplorerBehaviorConstraint> getBehaviors();
	
	public ExplorerConfiguration getExplorerConfiguration();
	
	public UserInteractionConstraint getNewObjectConstraint();
	
	public List<ExplorerOperationConstraint> getOperations();
	
	public List<ExplorerPropertyConstraint> getProperties();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setBehaviors(List<ExplorerBehaviorConstraint> behaviors);
	
	public void setExplorerConfiguration(ExplorerConfiguration explorerConfiguration);
	
	public void setNewObjectConstraint(UserInteractionConstraint newObjectConstraint);
	
	public void setOperations(List<ExplorerOperationConstraint> operations);
	
	public void setProperties(List<ExplorerPropertyConstraint> properties);
	
	public void setUid(String uid);

}