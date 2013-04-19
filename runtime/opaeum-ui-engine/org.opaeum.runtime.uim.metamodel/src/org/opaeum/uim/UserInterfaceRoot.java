package org.opaeum.uim;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface UserInterfaceRoot extends EObject, UserInteractionElement, LabeledElement {
	public void buildTreeFromXml(Element xml);
	
	public RootUserInteractionConstraint getEditability();
	
	public List<IgnoredElement> getIgnoredElements();
	
	public List<PageOrdering> getPageOrdering();
	
	public List<? extends Page> getPages();
	
	public List<UserInterfaceRoot> getSuperUserInterfaces();
	
	public String getUid();
	
	public RootUserInteractionConstraint getVisibility();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setEditability(RootUserInteractionConstraint editability);
	
	public void setIgnoredElements(List<IgnoredElement> ignoredElements);
	
	public void setPageOrdering(List<PageOrdering> pageOrdering);
	
	public void setSuperUserInterfaces(List<UserInterfaceRoot> superUserInterfaces);
	
	public void setUid(String uid);
	
	public void setVisibility(RootUserInteractionConstraint visibility);

}