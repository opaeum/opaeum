package org.opaeum.uim.component;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.constraint.UserInteractionConstraint;

public class DetailComponentImpl implements DetailComponent {
	private MasterComponent masterComponent;
	private String name;
	private List<PanelForClass> panelsForClasses;
	private boolean underUserControl;
	private UserInteractionConstraint visibility;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public MasterComponent getMasterComponent() {
		return this.masterComponent;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<PanelForClass> getPanelsForClasses() {
		return this.panelsForClasses;
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
	
	public void setMasterComponent(MasterComponent masterComponent) {
		this.masterComponent=masterComponent;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setPanelsForClasses(List<PanelForClass> panelsForClasses) {
		this.panelsForClasses=panelsForClasses;
	}
	
	public void setVisibility(UserInteractionConstraint visibility) {
		this.visibility=visibility;
	}

}