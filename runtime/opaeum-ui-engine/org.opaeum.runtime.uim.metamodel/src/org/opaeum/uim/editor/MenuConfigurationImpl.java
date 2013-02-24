package org.opaeum.uim.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MenuConfigurationImpl extends EObjectImpl implements MenuConfiguration {
	private InstanceEditor editor;
	private String name;
	private List<OperationMenuItem> operations = new ArrayList<OperationMenuItem>();
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
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("operations") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				OperationMenuItem curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="editor:OperationMenuItem";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.getOperations().add(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
				curVal.setMenuConfiguration(this);
			}
		}
	}
	
	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public InstanceEditor getEditor() {
		return this.editor;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<OperationMenuItem> getOperations() {
		return this.operations;
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
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("operations") ) {
				((org.opaeum.uim.editor.OperationMenuItem)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
		}
	}
	
	public void setEditor(InstanceEditor editor) {
		this.editor=editor;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setOperations(List<OperationMenuItem> operations) {
		this.operations=operations;
	}
	
	public void setUid(String uid) {
		this.uid=uid;
	}
	
	public void setUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}

}