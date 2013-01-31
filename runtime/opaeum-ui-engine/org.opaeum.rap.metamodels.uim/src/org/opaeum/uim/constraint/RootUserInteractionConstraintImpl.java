package org.opaeum.uim.constraint;

import java.util.List;

import org.opaeum.ecore.EObject;

public class RootUserInteractionConstraintImpl implements RootUserInteractionConstraint {
	private Boolean openToPublic;
	private List<RequiredRole> requiredRoles;
	private List<RequiredState> requiredStates;
	private boolean requiresGroupOwnership;
	private boolean requiresOwnership;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
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
	
	public void isRequiresGroupOwnership(boolean requiresGroupOwnership) {
		this.requiresGroupOwnership=requiresGroupOwnership;
	}
	
	public void isRequiresOwnership(boolean requiresOwnership) {
		this.requiresOwnership=requiresOwnership;
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

}