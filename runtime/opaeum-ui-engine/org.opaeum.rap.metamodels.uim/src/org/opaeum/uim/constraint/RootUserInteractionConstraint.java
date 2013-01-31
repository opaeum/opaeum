package org.opaeum.uim.constraint;

import java.util.List;

import org.opaeum.ecore.EObject;

public interface RootUserInteractionConstraint extends EObject {
	public Boolean getOpenToPublic();
	
	public List<RequiredRole> getRequiredRoles();
	
	public List<RequiredState> getRequiredStates();
	
	public boolean getRequiresGroupOwnership();
	
	public boolean getRequiresOwnership();
	
	public void isRequiresGroupOwnership(boolean requiresGroupOwnership);
	
	public void isRequiresOwnership(boolean requiresOwnership);
	
	public void setOpenToPublic(Boolean openToPublic);
	
	public void setRequiredRoles(List<RequiredRole> requiredRoles);
	
	public void setRequiredStates(List<RequiredState> requiredStates);

}