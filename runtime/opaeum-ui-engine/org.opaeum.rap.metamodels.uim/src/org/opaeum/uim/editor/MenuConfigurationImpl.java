package org.opaeum.uim.editor;

import java.util.List;

import org.opaeum.ecore.EObject;

public class MenuConfigurationImpl implements MenuConfiguration {
	private InstanceEditor editor;
	private String name;
	private List<OperationMenuItem> operations;
	private boolean underUserControl;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public InstanceEditor getEditor() {
		return this.editor;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<OperationMenuItem> getOperations() {
		return this.operations;
	}
	
	public boolean getUnderUserControl() {
		return this.underUserControl;
	}
	
	public void isUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setEditor(InstanceEditor editor) {
		this.editor=editor;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setOperations(List<OperationMenuItem> operations) {
		this.operations=operations;
	}

}