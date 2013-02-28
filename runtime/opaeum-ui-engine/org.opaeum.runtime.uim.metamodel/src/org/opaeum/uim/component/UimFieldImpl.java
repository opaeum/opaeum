package org.opaeum.uim.component;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.ecore.EObjectImpl;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.uim.Labels;
import org.opaeum.uim.UimInstantiator;
import org.opaeum.uim.binding.FieldBinding;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.control.UimControl;
import org.opaeum.uim.panel.Orientation;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UimFieldImpl extends EObjectImpl implements UimField {
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
	private String uid;
	private boolean underUserControl;
	private UserInteractionConstraint visibility;


	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("xmi:id"));
		if ( xml.getAttribute("name").length()>0 ) {
			setName(EcoreDataTypeParser.getInstance().parseEString(xml.getAttribute("name")));
		}
		if(getName().equals("Initiation datell")){
			System.out.println();
		}
		if ( xml.getAttribute("underUserControl").length()>0 ) {
			setUnderUserControl(EcoreDataTypeParser.getInstance().parseEBoolean(xml.getAttribute("underUserControl")));
		}
		if ( xml.getAttribute("preferredWidth").length()>0 ) {
			setPreferredWidth(EcoreDataTypeParser.getInstance().parseEIntegerObject(xml.getAttribute("preferredWidth")));
		}
		if ( xml.getAttribute("preferredHeight").length()>0 ) {
			setPreferredHeight(EcoreDataTypeParser.getInstance().parseEIntegerObject(xml.getAttribute("preferredHeight")));
		}
		if ( xml.getAttribute("fillHorizontally").length()>0 ) {
			setFillHorizontally(EcoreDataTypeParser.getInstance().parseEBooleanObject(xml.getAttribute("fillHorizontally")));
		}
		if ( xml.getAttribute("fillVertically").length()>0 ) {
			setFillVertically(EcoreDataTypeParser.getInstance().parseEBooleanObject(xml.getAttribute("fillVertically")));
		}
		if ( xml.getAttribute("controlKind").length()>0 ) {
			setControlKind(ControlKind.getByName(xml.getAttribute("controlKind")));
		}
		if ( xml.getAttribute("minimumLabelWidth").length()>0 ) {
			setMinimumLabelWidth(EcoreDataTypeParser.getInstance().parseEIntegerObject(xml.getAttribute("minimumLabelWidth")));
		}
		if ( xml.getAttribute("orientation").length()>0 ) {
			setOrientation(Orientation.getByName(xml.getAttribute("orientation")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("visibility") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				UserInteractionConstraint curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="constr:UserInteractionConstraint";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setVisibility(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("editability") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				UserInteractionConstraint curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="constr:UserInteractionConstraint";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setEditability(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("labelOverride") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				Labels curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="Labels";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setLabelOverride(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("control") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				UimControl curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="ctl:UimControl";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setControl(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
				curVal.setField(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("binding") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				FieldBinding curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="bind:FieldBinding";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setBinding(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
				curVal.setField(this);
			}
		}
	}
	
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
	
	public String getUid() {
		return this.uid;
	}
	
	public UserInteractionConstraint getVisibility() {
		return this.visibility;
	}
	
	public boolean isUnderUserControl() {
		return this.underUserControl;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("visibility") ) {
				((org.opaeum.uim.constraint.UserInteractionConstraint)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("editability") ) {
				((org.opaeum.uim.constraint.UserInteractionConstraint)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("labelOverride") ) {
				((org.opaeum.uim.Labels)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("control") ) {
				((org.opaeum.uim.control.UimControl)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("binding") ) {
				((org.opaeum.uim.binding.FieldBinding)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
		}
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
	
	public void setUid(String uid) {
		this.uid=uid;
	}
	
	public void setUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setVisibility(UserInteractionConstraint visibility) {
		this.visibility=visibility;
	}

}