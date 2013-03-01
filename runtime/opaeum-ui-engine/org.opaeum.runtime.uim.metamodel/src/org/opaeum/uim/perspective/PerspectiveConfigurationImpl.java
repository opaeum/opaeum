package org.opaeum.uim.perspective;

import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PerspectiveConfigurationImpl extends EObjectImpl implements PerspectiveConfiguration {
	private EditorConfiguration editor;
	private NavigatorConfiguration explorer;
	private InboxConfiguration inbox;
	private String name;
	private OutboxConfiguration outbox;
	private PropertiesConfiguration properties;
	private boolean underUserControl;


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
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("explorer") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				NavigatorConfiguration curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="persp:NavigatorConfiguration";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setExplorer(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("editor") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				EditorConfiguration curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="persp:EditorConfiguration";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setEditor(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("properties") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				PropertiesConfiguration curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="persp:PropertiesConfiguration";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setProperties(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("inbox") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				InboxConfiguration curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="persp:InboxConfiguration";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setInbox(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("outbox") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				OutboxConfiguration curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="persp:OutboxConfiguration";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setOutbox(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
			}
		}
	}
	
	public EditorConfiguration getEditor() {
		return this.editor;
	}
	
	public NavigatorConfiguration getExplorer() {
		return this.explorer;
	}
	
	public InboxConfiguration getInbox() {
		return this.inbox;
	}
	
	public String getName() {
		return this.name;
	}
	
	public OutboxConfiguration getOutbox() {
		return this.outbox;
	}
	
	public PropertiesConfiguration getProperties() {
		return this.properties;
	}
	
	public boolean isUnderUserControl() {
		return this.underUserControl;
	}
	
	public void populateReferencesFromXml(Element xml) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("explorer") ) {
				((org.opaeum.uim.perspective.NavigatorConfiguration)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("editor") ) {
				((org.opaeum.uim.perspective.EditorConfiguration)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("properties") ) {
				((org.opaeum.uim.perspective.PropertiesConfiguration)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("inbox") ) {
				((org.opaeum.uim.perspective.InboxConfiguration)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("outbox") ) {
				((org.opaeum.uim.perspective.OutboxConfiguration)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
		}
	}
	
	public void setEditor(EditorConfiguration editor) {
		this.editor=editor;
	}
	
	public void setExplorer(NavigatorConfiguration explorer) {
		this.explorer=explorer;
	}
	
	public void setInbox(InboxConfiguration inbox) {
		this.inbox=inbox;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setOutbox(OutboxConfiguration outbox) {
		this.outbox=outbox;
	}
	
	public void setProperties(PropertiesConfiguration properties) {
		this.properties=properties;
	}
	
	public void setUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}

}