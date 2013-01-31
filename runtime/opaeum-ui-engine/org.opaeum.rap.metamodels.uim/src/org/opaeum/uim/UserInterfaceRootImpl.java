package org.opaeum.uim;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;

public class UserInterfaceRootImpl implements UserInterfaceRoot {
	private RootUserInteractionConstraint editability;
	private List<IgnoredElement> ignoredElements;
	private Labels labelOverride;
	private String name;
	private List<PageOrdering> pageOrdering;
	private List<UserInterfaceRoot> superUserInterfaces;
	private String umlElementUid;
	private boolean underUserControl;
	private RootUserInteractionConstraint visibility;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public RootUserInteractionConstraint getEditability() {
		return this.editability;
	}
	
	public List<IgnoredElement> getIgnoredElements() {
		return this.ignoredElements;
	}
	
	public Labels getLabelOverride() {
		return this.labelOverride;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<PageOrdering> getPageOrdering() {
		return this.pageOrdering;
	}
	
	public List<Page> getPages() {
		List<Page> result = null;
		
		return result;
	}
	
	public List<UserInterfaceRoot> getSuperUserInterfaces() {
		return this.superUserInterfaces;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public boolean getUnderUserControl() {
		return this.underUserControl;
	}
	
	public RootUserInteractionConstraint getVisibility() {
		return this.visibility;
	}
	
	public void isUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setEditability(RootUserInteractionConstraint editability) {
		this.editability=editability;
	}
	
	public void setIgnoredElements(List<IgnoredElement> ignoredElements) {
		this.ignoredElements=ignoredElements;
	}
	
	public void setLabelOverride(Labels labelOverride) {
		this.labelOverride=labelOverride;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setPageOrdering(List<PageOrdering> pageOrdering) {
		this.pageOrdering=pageOrdering;
	}
	
	public void setSuperUserInterfaces(List<UserInterfaceRoot> superUserInterfaces) {
		this.superUserInterfaces=superUserInterfaces;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}
	
	public void setVisibility(RootUserInteractionConstraint visibility) {
		this.visibility=visibility;
	}

}