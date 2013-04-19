package org.opaeum.uim.component;

import java.util.ArrayList;
import java.util.List;

import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.Labels;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DetailComponentImpl extends EObjectImpl implements DetailComponent {
	private Labels labelOverride;
	private MasterComponent masterComponent;
	private String name;
	private List<PanelForClass> panelsForClasses = new ArrayList<PanelForClass>();
	private boolean underUserControl;
	private UserInteractionConstraint visibility;


	public void buildTreeFromXml(Element xml) {
		if ( xml.getAttribute("name").length()>0 ) {
			setName(EcoreDataTypeParser.getInstance().parseEString(xml.getAttribute("name")));
		}
		if ( xml.getAttribute("underUserControl").length()>0 ) {
			setUnderUserControl(EcoreDataTypeParser.getInstance().parseEBoolean(xml.getAttribute("underUserControl")));
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
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("labelOverride") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				Labels curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="Labels";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setLabelOverride(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("panelsForClasses") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				PanelForClass curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="comp:PanelForClass";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.getPanelsForClasses().add(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
				curVal.setDetailComponent(this);
			}
		}
	}
	
	public Labels getLabelOverride() {
		return this.labelOverride;
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
	
	public UserInteractionConstraint getVisibility() {
		return this.visibility;
	}
	
	public boolean isUnderUserControl() {
		return this.underUserControl;
	}
	
	public void populateReferencesFromXml(Element xml) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("visibility") ) {
				((org.opaeum.uim.constraint.UserInteractionConstraint)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("labelOverride") ) {
				((org.opaeum.uim.Labels)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("masterComponent") ) {
				setMasterComponent((org.opaeum.uim.component.MasterComponent)this.eResource().getResourceSet().getReference((Element)currentPropertyNode));
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("panelsForClasses") ) {
				((org.opaeum.uim.component.PanelForClass)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
		}
	}
	
	public void setLabelOverride(Labels labelOverride) {
		this.labelOverride=labelOverride;
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
	
	public void setUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setVisibility(UserInteractionConstraint visibility) {
		this.visibility=visibility;
	}

}