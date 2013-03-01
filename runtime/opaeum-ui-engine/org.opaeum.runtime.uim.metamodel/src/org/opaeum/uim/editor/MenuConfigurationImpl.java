package org.opaeum.uim.editor;

import java.util.ArrayList;
import java.util.List;

import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MenuConfigurationImpl extends EObjectImpl implements MenuConfiguration {
	private InstanceEditor editor;
	private String name;
	private List<OperationMenuItem> operations = new ArrayList<OperationMenuItem>();
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
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("operations") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				OperationMenuItem curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="editor:OperationMenuItem";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.getOperations().add(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
				curVal.setMenuConfiguration(this);
			}
		}
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
	
	public boolean isUnderUserControl() {
		return this.underUserControl;
	}
	
	public void populateReferencesFromXml(Element xml) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("operations") ) {
				((org.opaeum.uim.editor.OperationMenuItem)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
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
	
	public void setUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}

}