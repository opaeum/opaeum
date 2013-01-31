package org.opaeum.uim;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;

public interface UserInterfaceRoot extends EObject, UserInteractionElement, LabeledElement {
	public RootUserInteractionConstraint getEditability();
	
	public List<IgnoredElement> getIgnoredElements();
	
	public List<PageOrdering> getPageOrdering();
	
	public List<?extends Page> getPages();
	
	public List<UserInterfaceRoot> getSuperUserInterfaces();
	
	public RootUserInteractionConstraint getVisibility();
	
	public void setEditability(RootUserInteractionConstraint editability);
	
	public void setIgnoredElements(List<IgnoredElement> ignoredElements);
	
	public void setPageOrdering(List<PageOrdering> pageOrdering);
	
	public void setSuperUserInterfaces(List<UserInterfaceRoot> superUserInterfaces);
	
	public void setVisibility(RootUserInteractionConstraint visibility);

}