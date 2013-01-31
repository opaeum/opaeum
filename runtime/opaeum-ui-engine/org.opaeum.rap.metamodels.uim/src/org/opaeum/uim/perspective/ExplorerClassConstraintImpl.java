package org.opaeum.uim.perspective;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.Labels;
import org.opaeum.uim.constraint.RequiredRole;
import org.opaeum.uim.constraint.RequiredState;
import org.opaeum.uim.constraint.UserInteractionConstraint;

public class ExplorerClassConstraintImpl implements ExplorerClassConstraint {
	private List<ExplorerBehaviorConstraint> behaviors;
	private ExplorerConfiguration explorerConfiguration;
	private boolean hidden;
	private boolean inheritFromParent;
	private Labels labelOverride;
	private String name;
	private UserInteractionConstraint newObjectConstraint;
	private Boolean openToPublic;
	private List<ExplorerOperationConstraint> operations;
	private List<ExplorerPropertyConstraint> properties;
	private List<RequiredRole> requiredRoles;
	private List<RequiredState> requiredStates;
	private boolean requiresGroupOwnership;
	private boolean requiresOwnership;
	private String umlElementUid;
	private boolean underUserControl;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public List<ExplorerBehaviorConstraint> getBehaviors() {
		return this.behaviors;
	}
	
	public ExplorerConfiguration getExplorerConfiguration() {
		return this.explorerConfiguration;
	}
	
	public boolean getHidden() {
		return this.hidden;
	}
	
	public boolean getInheritFromParent() {
		return this.inheritFromParent;
	}
	
	public Labels getLabelOverride() {
		return this.labelOverride;
	}
	
	public String getName() {
		return this.name;
	}
	
	public UserInteractionConstraint getNewObjectConstraint() {
		return this.newObjectConstraint;
	}
	
	public Boolean getOpenToPublic() {
		return this.openToPublic;
	}
	
	public List<ExplorerOperationConstraint> getOperations() {
		return this.operations;
	}
	
	public List<ExplorerPropertyConstraint> getProperties() {
		return this.properties;
	}
	
	public List<RequiredRole> getRequiredRoles() {
		return this.requiredRoles;
	}
	
	public List<RequiredState> getRequiredStates() {
		return this.requiredStates;
	}
	
	public boolean getRequiresGroupOwnership() {
		return this.requiresGroupOwnership;
	}
	
	public boolean getRequiresOwnership() {
		return this.requiresOwnership;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public boolean getUnderUserControl() {
		return this.underUserControl;
	}
	
	public void isHidden(boolean hidden) {
		this.hidden=hidden;
	}
	
	public void isInheritFromParent(boolean inheritFromParent) {
		this.inheritFromParent=inheritFromParent;
	}
	
	public void isRequiresGroupOwnership(boolean requiresGroupOwnership) {
		this.requiresGroupOwnership=requiresGroupOwnership;
	}
	
	public void isRequiresOwnership(boolean requiresOwnership) {
		this.requiresOwnership=requiresOwnership;
	}
	
	public void isUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setBehaviors(List<ExplorerBehaviorConstraint> behaviors) {
		this.behaviors=behaviors;
	}
	
	public void setExplorerConfiguration(ExplorerConfiguration explorerConfiguration) {
		this.explorerConfiguration=explorerConfiguration;
	}
	
	public void setLabelOverride(Labels labelOverride) {
		this.labelOverride=labelOverride;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setNewObjectConstraint(UserInteractionConstraint newObjectConstraint) {
		this.newObjectConstraint=newObjectConstraint;
	}
	
	public void setOpenToPublic(Boolean openToPublic) {
		this.openToPublic=openToPublic;
	}
	
	public void setOperations(List<ExplorerOperationConstraint> operations) {
		this.operations=operations;
	}
	
	public void setProperties(List<ExplorerPropertyConstraint> properties) {
		this.properties=properties;
	}
	
	public void setRequiredRoles(List<RequiredRole> requiredRoles) {
		this.requiredRoles=requiredRoles;
	}
	
	public void setRequiredStates(List<RequiredState> requiredStates) {
		this.requiredStates=requiredStates;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}

}