package org.opaeum.uim;

import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;
import org.w3c.dom.Element;

public interface UserInterfaceRoot extends EObject, UserInteractionElement, LabeledElement {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public RootUserInteractionConstraint getEditability();
	
	public List<IgnoredElement> getIgnoredElements();
	
	public List<PageOrdering> getPageOrdering();
	
	public List<? extends Page> getPages();
	
	public List<UserInterfaceRoot> getSuperUserInterfaces();
	
	public String getUid();
	
	public RootUserInteractionConstraint getVisibility();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setEditability(RootUserInteractionConstraint editability);
	
	public void setIgnoredElements(List<IgnoredElement> ignoredElements);
	
	public void setPageOrdering(List<PageOrdering> pageOrdering);
	
	public void setSuperUserInterfaces(List<UserInterfaceRoot> superUserInterfaces);
	
	public void setUid(String uid);
	
	public void setVisibility(RootUserInteractionConstraint visibility);

}