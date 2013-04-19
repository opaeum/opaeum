package org.opaeum.uim.editor;

import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.Labels;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.opaeum.uim.panel.AbstractPanel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class QueryResultPageImpl extends EObjectImpl implements QueryResultPage {
	private UserInteractionConstraint editability;
	private Labels labelOverride;
	private String name;
	private AbstractPanel panel;
	private String umlElementUid;
	private boolean underUserControl;
	private UserInteractionConstraint visibility;


	public void buildTreeFromXml(Element xml) {
		if ( xml.getAttribute("name").length()>0 ) {
			setName(EcoreDataTypeParser.getInstance().parseEString(xml.getAttribute("name")));
		}
		if ( xml.getAttribute("underUserControl").length()>0 ) {
			setUnderUserControl(EcoreDataTypeParser.getInstance().parseEBoolean(xml.getAttribute("underUserControl")));
		}
		if ( xml.getAttribute("umlElementUid").length()>0 ) {
			setUmlElementUid(EcoreDataTypeParser.getInstance().parseEString(xml.getAttribute("umlElementUid")));
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
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("editability") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				UserInteractionConstraint curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="constr:UserInteractionConstraint";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setEditability(curVal);
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
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("panel") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				AbstractPanel curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="panel:AbstractPanel";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setPanel(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
			}
		}
	}
	
	public UserInteractionConstraint getEditability() {
		return this.editability;
	}
	
	public Labels getLabelOverride() {
		return this.labelOverride;
	}
	
	public String getName() {
		return this.name;
	}
	
	public AbstractPanel getPanel() {
		return this.panel;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
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
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("editability") ) {
				((org.opaeum.uim.constraint.UserInteractionConstraint)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("labelOverride") ) {
				((org.opaeum.uim.Labels)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("panel") ) {
				((org.opaeum.uim.panel.AbstractPanel)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
		}
	}
	
	public void setEditability(UserInteractionConstraint editability) {
		this.editability=editability;
	}
	
	public void setLabelOverride(Labels labelOverride) {
		this.labelOverride=labelOverride;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setPanel(AbstractPanel panel) {
		this.panel=panel;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}
	
	public void setUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setVisibility(UserInteractionConstraint visibility) {
		this.visibility=visibility;
	}

}