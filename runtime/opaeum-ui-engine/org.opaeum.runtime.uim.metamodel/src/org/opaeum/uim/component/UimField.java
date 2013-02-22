package org.opaeum.uim.component;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.Labels;
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
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public FieldBinding getBinding();
	
	public UimControl getControl();
	
	public ControlKind getControlKind();
	
	public Labels getLabelOverride();
	
	public Integer getMinimumLabelWidth();
	
	public Orientation getOrientation();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setBinding(FieldBinding binding);
	
	public void setControl(UimControl control);
	
	public void setControlKind(ControlKind controlKind);
	
	public void setLabelOverride(Labels labelOverride);
	
	public void setMinimumLabelWidth(Integer minimumLabelWidth);
	
	public void setOrientation(Orientation orientation);
	
	public void setUid(String uid);

}