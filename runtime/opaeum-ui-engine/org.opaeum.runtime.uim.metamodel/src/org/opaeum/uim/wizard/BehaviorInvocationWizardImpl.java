package org.opaeum.uim.wizard;

import java.util.ArrayList;
import java.util.List;

import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.IgnoredElement;
import org.opaeum.uim.Labels;
import org.opaeum.uim.PageOrdering;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;
import org.opaeum.uim.model.BehaviorUserInteractionModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BehaviorInvocationWizardImpl extends EObjectImpl implements BehaviorInvocationWizard {
	private RootUserInteractionConstraint editability;
	private List<IgnoredElement> ignoredElements = new ArrayList<IgnoredElement>();
	private Labels labelOverride;
	private BehaviorUserInteractionModel model;
	private String name;
	private List<PageOrdering> pageOrdering = new ArrayList<PageOrdering>();
	private List<WizardPage> pages = new ArrayList<WizardPage>();
	private List<UserInterfaceRoot> superUserInterfaces = new ArrayList<UserInterfaceRoot>();
	private String umlElementUid;
	private boolean underUserControl;
	private RootUserInteractionConstraint visibility;


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
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("editability") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				RootUserInteractionConstraint curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="constr:RootUserInteractionConstraint";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setEditability(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("visibility") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				RootUserInteractionConstraint curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="constr:RootUserInteractionConstraint";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setVisibility(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("ignoredElements") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				IgnoredElement curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="IgnoredElement";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.getIgnoredElements().add(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
				curVal.setUserInterfaceRoot(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("pageOrdering") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				PageOrdering curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="PageOrdering";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.getPageOrdering().add(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("pages") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				WizardPage curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="wizard:WizardPage";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.getPages().add(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
				curVal.setWizard(this);
			}
		}
	}
	
	public RootUserInteractionConstraint getEditability() {
		return this.editability;
	}
	
	public List<IgnoredElement> getIgnoredElements() {
		return this.ignoredElements;
	}
	
	public Labels getLabelOverride() {
		return this.labelOverride;
	}
	
	public BehaviorUserInteractionModel getModel() {
		return this.model;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<PageOrdering> getPageOrdering() {
		return this.pageOrdering;
	}
	
	public List<WizardPage> getPages() {
		return this.pages;
	}
	
	public List<UserInterfaceRoot> getSuperUserInterfaces() {
		return this.superUserInterfaces;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public RootUserInteractionConstraint getVisibility() {
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
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("labelOverride") ) {
				((org.opaeum.uim.Labels)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("editability") ) {
				((org.opaeum.uim.constraint.RootUserInteractionConstraint)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("visibility") ) {
				((org.opaeum.uim.constraint.RootUserInteractionConstraint)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("ignoredElements") ) {
				((org.opaeum.uim.IgnoredElement)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("superUserInterfaces") ) {
				setSuperUserInterfaces((java.util.List)this.eResource().getResourceSet().getReferences((Element)currentPropertyNode));
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("pageOrdering") ) {
				((org.opaeum.uim.PageOrdering)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("pages") ) {
				((org.opaeum.uim.wizard.WizardPage)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
		}
	}
	
	public void setEditability(RootUserInteractionConstraint editability) {
		this.editability=editability;
	}
	
	public void setIgnoredElements(List<IgnoredElement> ignoredElements) {
		this.ignoredElements=ignoredElements;
	}
	
	public void setLabelOverride(Labels labelOverride) {
		this.labelOverride=labelOverride;
	}
	
	public void setModel(BehaviorUserInteractionModel model) {
		this.model=model;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setPageOrdering(List<PageOrdering> pageOrdering) {
		this.pageOrdering=pageOrdering;
	}
	
	public void setPages(List<WizardPage> pages) {
		this.pages=pages;
	}
	
	public void setSuperUserInterfaces(List<UserInterfaceRoot> superUserInterfaces) {
		this.superUserInterfaces=superUserInterfaces;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}
	
	public void setUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setVisibility(RootUserInteractionConstraint visibility) {
		this.visibility=visibility;
	}

}