package org.opaeum.uim.model;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.uim.editor.BehaviorExecutionEditor;
import org.opaeum.uim.wizard.BehaviorInvocationWizard;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BehaviorUserInteractionModelImpl extends EObjectImpl implements BehaviorUserInteractionModel {
	private BehaviorExecutionEditor editor;
	private BehaviorInvocationWizard invocationWizard;
	private String linkedUmlResource;
	private String name;
	private String uid;
	private String umlElementUid;
	private boolean underUserControl;


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
				BehaviorInvocationWizard curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="wizard:BehaviorInvocationWizard";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setInvocationWizard(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
				curVal.setModel(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("editor") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				BehaviorExecutionEditor curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="editor:BehaviorExecutionEditor";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setEditor(curVal);
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
	
	public BehaviorExecutionEditor getEditor() {
		return this.editor;
	}
	
	public BehaviorInvocationWizard getInvocationWizard() {
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
	
	public boolean isUnderUserControl() {
		return this.underUserControl;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("invocationWizard") ) {
				((org.opaeum.uim.wizard.BehaviorInvocationWizard)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("editor") ) {
				((org.opaeum.uim.editor.BehaviorExecutionEditor)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
		}
	}
	
	public void setEditor(BehaviorExecutionEditor editor) {
		this.editor=editor;
	}
	
	public void setInvocationWizard(BehaviorInvocationWizard invocationWizard) {
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

}