package org.opaeum.uim.action;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.Labels;
import org.opaeum.uim.component.UimContainer;
import org.opaeum.uim.constraint.UserInteractionConstraint;

public class BuiltInActionButtonImpl implements BuiltInActionButton {
	private Boolean fillHorizontally;
	private Boolean fillVertically;
	private ActionKind kind;
	private Labels labels;
	private String name;
	private Integer preferredHeight;
	private Integer preferredWidth;
	private boolean underUserControl;
	private UserInteractionConstraint visibility;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public Boolean getFillHorizontally() {
		return this.fillHorizontally;
	}
	
	public Boolean getFillVertically() {
		return this.fillVertically;
	}
	
	public ActionKind getKind() {
		return this.kind;
	}
	
	public Labels getLabels() {
		return this.labels;
	}
	
	public String getName() {
		return this.name;
	}
	
	public UimContainer getParent() {
		UimContainer result = null;
		
		return result;
	}
	
	public Integer getPreferredHeight() {
		return this.preferredHeight;
	}
	
	public Integer getPreferredWidth() {
		return this.preferredWidth;
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
	
	public void setFillHorizontally(Boolean fillHorizontally) {
		this.fillHorizontally=fillHorizontally;
	}
	
	public void setFillVertically(Boolean fillVertically) {
		this.fillVertically=fillVertically;
	}
	
	public void setKind(ActionKind kind) {
		this.kind=kind;
	}
	
	public void setLabels(Labels labels) {
		this.labels=labels;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setPreferredHeight(Integer preferredHeight) {
		this.preferredHeight=preferredHeight;
	}
	
	public void setPreferredWidth(Integer preferredWidth) {
		this.preferredWidth=preferredWidth;
	}
	
	public void setVisibility(UserInteractionConstraint visibility) {
		this.visibility=visibility;
	}

}