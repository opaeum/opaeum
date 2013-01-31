package org.opaeum.uim.perspective;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.Labels;
import org.opaeum.uim.constraint.RequiredRole;
import org.opaeum.uim.constraint.RequiredState;

public class ExplorerConstraintImpl implements ExplorerConstraint {
	private boolean hidden;
	private boolean inheritFromParent;
	private Labels labelOverride;
	private String name;
	private Boolean openToPublic;
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
	
	public Boolean getOpenToPublic() {
		return this.openToPublic;
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
	
	public void setLabelOverride(Labels labelOverride) {
		this.labelOverride=labelOverride;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setOpenToPublic(Boolean openToPublic) {
		this.openToPublic=openToPublic;
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