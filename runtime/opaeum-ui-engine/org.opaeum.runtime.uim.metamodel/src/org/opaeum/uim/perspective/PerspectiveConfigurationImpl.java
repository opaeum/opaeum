package org.opaeum.uim.perspective;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.rap.metamodels.uim.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PerspectiveConfigurationImpl extends EObjectImpl implements PerspectiveConfiguration {
	private EditorConfiguration editor;
	private ExplorerConfiguration explorer;
	private InboxConfiguration inbox;
	private String name;
	private OutboxConfiguration outbox;
	private PropertiesConfiguration properties;
	private String uid;
	private boolean underUserControl;


	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("xmi:id"));
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
				ExplorerConfiguration curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="persp:ExplorerConfiguration";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setExplorer(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("editor") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				EditorConfiguration curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="persp:EditorConfiguration";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setEditor(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("properties") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				PropertiesConfiguration curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="persp:PropertiesConfiguration";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setProperties(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("inbox") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				InboxConfiguration curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="persp:InboxConfiguration";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setInbox(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
			}
		}
	}
	
	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public EditorConfiguration getEditor() {
		return this.editor;
	}
	
	public ExplorerConfiguration getExplorer() {
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
	
	public String getUid() {
		return this.uid;
	}
	
	public boolean isUnderUserControl() {
		return this.underUserControl;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("explorer") ) {
				((org.opaeum.uim.perspective.ExplorerConfiguration)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("editor") ) {
				((org.opaeum.uim.perspective.EditorConfiguration)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("properties") ) {
				((org.opaeum.uim.perspective.PropertiesConfiguration)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("inbox") ) {
				((org.opaeum.uim.perspective.InboxConfiguration)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("outbox") ) {
				setOutbox((org.opaeum.uim.perspective.OutboxConfiguration)map.get(((Element)currentPropertyNode).getAttribute("xmi:id")));
			}
		}
	}
	
	public void setEditor(EditorConfiguration editor) {
		this.editor=editor;
	}
	
	public void setExplorer(ExplorerConfiguration explorer) {
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
	
	public void setUid(String uid) {
		this.uid=uid;
	}
	
	public void setUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}

}