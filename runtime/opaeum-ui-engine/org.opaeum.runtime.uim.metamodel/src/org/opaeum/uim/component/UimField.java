package org.opaeum.uim.component;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.binding.FieldBinding;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.control.UimControl;
import org.opaeum.uim.panel.Orientation;
import org.opaeum.uim.panel.Outlayable;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface UimField extends EObject, UimComponent, EditableConstrainedObject, Outlayable {
	public void buildTreeFromXml(Element xml);
	
	public FieldBinding getBinding();
	
	public UimControl getControl();
	
	public ControlKind getControlKind();
	
	public Integer getMinimumLabelWidth();
	
	public Orientation getOrientation();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setBinding(FieldBinding binding);
	
	public void setControl(UimControl control);
	
	public void setControlKind(ControlKind controlKind);
	
	public void setMinimumLabelWidth(Integer minimumLabelWidth);
	
	public void setOrientation(Orientation orientation);
	
	public void setUid(String uid);

}