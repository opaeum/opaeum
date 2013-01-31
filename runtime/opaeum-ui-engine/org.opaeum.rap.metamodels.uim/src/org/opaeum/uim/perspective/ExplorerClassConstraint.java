package org.opaeum.uim.perspective;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.constraint.UserInteractionConstraint;

public interface ExplorerClassConstraint extends EObject, ExplorerConstraint {
	public List<ExplorerBehaviorConstraint> getBehaviors();
	
	public ExplorerConfiguration getExplorerConfiguration();
	
	public UserInteractionConstraint getNewObjectConstraint();
	
	public List<ExplorerOperationConstraint> getOperations();
	
	public List<ExplorerPropertyConstraint> getProperties();
	
	public void setBehaviors(List<ExplorerBehaviorConstraint> behaviors);
	
	public void setExplorerConfiguration(ExplorerConfiguration explorerConfiguration);
	
	public void setNewObjectConstraint(UserInteractionConstraint newObjectConstraint);
	
	public void setOperations(List<ExplorerOperationConstraint> operations);
	
	public void setProperties(List<ExplorerPropertyConstraint> properties);

}