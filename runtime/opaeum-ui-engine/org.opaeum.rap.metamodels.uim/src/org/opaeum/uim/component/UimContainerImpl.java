package org.opaeum.uim.component;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.constraint.UserInteractionConstraint;

public class UimContainerImpl implements UimContainer {
	private List<UimComponent> children;
	private UserInteractionConstraint editability;
	private String name;
	private boolean underUserControl;
	private UserInteractionConstraint visibility;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public List<UimComponent> getChildren() {
		return this.children;
	}
	
	public UserInteractionConstraint getEditability() {
		return this.editability;
	}
	
	public String getName() {
		return this.name;
	}
	
	public UimContainer getParent() {
		UimContainer result = null;
		
		return result;
	}
	
	public boolean getUnderUserControl() {
		return this.underUserControl;
	}
	
	public UserInteractionConstraint getVisibility() {
		return this.visibility;
	}
	
	public void isUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setChildren(List<UimComponent> children) {
		this.children=children;
	}
	
	public void setEditability(UserInteractionConstraint editability) {
		this.editability=editability;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setVisibility(UserInteractionConstraint visibility) {
		this.visibility=visibility;
	}

}