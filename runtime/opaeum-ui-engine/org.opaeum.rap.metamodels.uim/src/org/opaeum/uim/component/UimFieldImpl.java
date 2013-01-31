package org.opaeum.uim.component;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.Labels;
import org.opaeum.uim.binding.FieldBinding;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.control.UimControl;
import org.opaeum.uim.panel.Orientation;

public class UimFieldImpl implements UimField {
	private FieldBinding binding;
	private UimControl control;
	private ControlKind controlKind;
	private UserInteractionConstraint editability;
	private Boolean fillHorizontally;
	private Boolean fillVertically;
	private Labels labelOverride;
	private Integer minimumLabelWidth;
	private String name;
	private Orientation orientation;
	private Integer preferredHeight;
	private Integer preferredWidth;
	private boolean underUserControl;
	private UserInteractionConstraint visibility;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public FieldBinding getBinding() {
		return this.binding;
	}
	
	public UimControl getControl() {
		return this.control;
	}
	
	public ControlKind getControlKind() {
		return this.controlKind;
	}
	
	public UserInteractionConstraint getEditability() {
		return this.editability;
	}
	
	public Boolean getFillHorizontally() {
		return this.fillHorizontally;
	}
	
	public Boolean getFillVertically() {
		return this.fillVertically;
	}
	
	public Labels getLabelOverride() {
		return this.labelOverride;
	}
	
	public Integer getMinimumLabelWidth() {
		return this.minimumLabelWidth;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Orientation getOrientation() {
		return this.orientation;
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
	
	public void setBinding(FieldBinding binding) {
		this.binding=binding;
	}
	
	public void setControl(UimControl control) {
		this.control=control;
	}
	
	public void setControlKind(ControlKind controlKind) {
		this.controlKind=controlKind;
	}
	
	public void setEditability(UserInteractionConstraint editability) {
		this.editability=editability;
	}
	
	public void setFillHorizontally(Boolean fillHorizontally) {
		this.fillHorizontally=fillHorizontally;
	}
	
	public void setFillVertically(Boolean fillVertically) {
		this.fillVertically=fillVertically;
	}
	
	public void setLabelOverride(Labels labelOverride) {
		this.labelOverride=labelOverride;
	}
	
	public void setMinimumLabelWidth(Integer minimumLabelWidth) {
		this.minimumLabelWidth=minimumLabelWidth;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setOrientation(Orientation orientation) {
		this.orientation=orientation;
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