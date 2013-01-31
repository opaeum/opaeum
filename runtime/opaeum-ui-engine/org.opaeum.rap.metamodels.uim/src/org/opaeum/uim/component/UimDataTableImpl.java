package org.opaeum.uim.component;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.Labels;
import org.opaeum.uim.action.AbstractActionButton;
import org.opaeum.uim.binding.TableBinding;
import org.opaeum.uim.constraint.UserInteractionConstraint;

public class UimDataTableImpl implements UimDataTable {
	private List<AbstractActionButton> actionsOnMultipleSelection;
	private TableBinding binding;
	private List<UimComponent> children;
	private List<DetailComponent> detailComponents;
	private UserInteractionConstraint editability;
	private Boolean fillHorizontally;
	private Boolean fillVertically;
	private Labels labelOverride;
	private String name;
	private Integer preferredHeight;
	private Integer preferredWidth;
	private boolean underUserControl;
	private UserInteractionConstraint visibility;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public List<AbstractActionButton> getActionsOnMultipleSelection() {
		return this.actionsOnMultipleSelection;
	}
	
	public TableBinding getBinding() {
		return this.binding;
	}
	
	public List<UimComponent> getChildren() {
		return this.children;
	}
	
	public List<DetailComponent> getDetailComponents() {
		return this.detailComponents;
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
	
	public void setActionsOnMultipleSelection(List<AbstractActionButton> actionsOnMultipleSelection) {
		this.actionsOnMultipleSelection=actionsOnMultipleSelection;
	}
	
	public void setBinding(TableBinding binding) {
		this.binding=binding;
	}
	
	public void setChildren(List<UimComponent> children) {
		this.children=children;
	}
	
	public void setDetailComponents(List<DetailComponent> detailComponents) {
		this.detailComponents=detailComponents;
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