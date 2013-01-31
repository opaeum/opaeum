package org.opaeum.uim.component;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.Labels;
import org.opaeum.uim.binding.FieldBinding;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.control.UimControl;
import org.opaeum.uim.panel.Orientation;
import org.opaeum.uim.panel.Outlayable;

public interface UimField extends EObject, UimComponent, EditableConstrainedObject, Outlayable {
	public FieldBinding getBinding();
	
	public UimControl getControl();
	
	public ControlKind getControlKind();
	
	public Labels getLabelOverride();
	
	public Integer getMinimumLabelWidth();
	
	public Orientation getOrientation();
	
	public void setBinding(FieldBinding binding);
	
	public void setControl(UimControl control);
	
	public void setControlKind(ControlKind controlKind);
	
	public void setLabelOverride(Labels labelOverride);
	
	public void setMinimumLabelWidth(Integer minimumLabelWidth);
	
	public void setOrientation(Orientation orientation);

}