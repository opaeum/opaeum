package org.opaeum.uim.model;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.rap.metamodels.uim.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.editor.ResponsibilityViewer;
import org.opaeum.uim.wizard.ResponsibilityInvocationWizard;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ResponsibilityUserInteractionModelImpl extends EObjectImpl implements ResponsibilityUserInteractionModel {
	private ResponsibilityInvocationWizard invocationWizard;
	private String linkedUmlResource;
	private String name;
	private String uid;
	private String umlElementUid;
	private boolean underUserControl;
	private ResponsibilityViewer viewer;


	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("xmi:id"));
		if ( xml.getAttribute("umlElementUid").length()>0 ) {
			setUmlElementUid(EcoreDataTypeParser.getInstance().parseEString(xml.getAttribute("umlElementUid")));
		}
		if ( xml.getAttribute("name").length()>0 ) {
			setName(EcoreDataTypeParser.getInstance().parseEString(xml.getAttribute("name")));
		}
		if ( xml.getAttribute("underUserControl").length()>0 ) {
			setUnderUserControl(EcoreDataTypeParser.getInstance().parseEBoolean(xml.getAttribute("underUserControl")));
		}
		if ( xml.getAttribute("linkedUmlResource").length()>0 ) {
			setLinkedUmlResource(EcoreDataTypeParser.getInstance().parseEString(xml.getAttribute("linkedUmlResource")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("invocationWizard") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				ResponsibilityInvocationWizard curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="wizard:ResponsibilityInvocationWizard";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setInvocationWizard(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
				curVal.setModel(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("viewer") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				ResponsibilityViewer curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="editor:ResponsibilityViewer";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setViewer(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
				curVal.setModel(this);
			}
		}
	}
	
	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public ResponsibilityInvocationWizard getInvocationWizard() {
		return this.invocationWizard;
	}
	
	public String getLinkedUmlResource() {
		return this.linkedUmlResource;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getUid() {
		return this.uid;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public ResponsibilityViewer getViewer() {
		return this.viewer;
	}
	
	public boolean isUnderUserControl() {
		return this.underUserControl;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("invocationWizard") ) {
				((org.opaeum.uim.wizard.ResponsibilityInvocationWizard)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("viewer") ) {
				((org.opaeum.uim.editor.ResponsibilityViewer)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
		}
	}
	
	public void setInvocationWizard(ResponsibilityInvocationWizard invocationWizard) {
		this.invocationWizard=invocationWizard;
	}
	
	public void setLinkedUmlResource(String linkedUmlResource) {
		this.linkedUmlResource=linkedUmlResource;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setUid(String uid) {
		this.uid=uid;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}
	
	public void setUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setViewer(ResponsibilityViewer viewer) {
		this.viewer=viewer;
	}

}