package org.opaeum.uim.constraint;

import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface RootUserInteractionConstraint extends EObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public Boolean getOpenToPublic();
	
	public List<RequiredRole> getRequiredRoles();
	
	public List<RequiredState> getRequiredStates();
	
	public String getUid();
	
	public boolean isRequiresGroupOwnership();
	
	public boolean isRequiresOwnership();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setOpenToPublic(Boolean openToPublic);
	
	public void setRequiredRoles(List<RequiredRole> requiredRoles);
	
	public void setRequiredStates(List<RequiredState> requiredStates);
	
	public void setRequiresGroupOwnership(boolean requiresGroupOwnership);
	
	public void setRequiresOwnership(boolean requiresOwnership);
	
	public void setUid(String uid);

}