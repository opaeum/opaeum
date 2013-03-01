package org.opaeum.uim.control;

import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.component.UimField;
import org.opaeum.uim.editor.ObjectEditor;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UimLinkControlImpl extends EObjectImpl implements UimLinkControl {
	private ObjectEditor editorToOpen;
	private UimField field;
	private String mimumWidth;
	private Integer minimumHeight;


	public void buildTreeFromXml(Element xml) {
		if ( xml.getAttribute("mimumWidth").length()>0 ) {
			setMimumWidth(EcoreDataTypeParser.getInstance().parseEString(xml.getAttribute("mimumWidth")));
		}
		if ( xml.getAttribute("minimumHeight").length()>0 ) {
			setMinimumHeight(EcoreDataTypeParser.getInstance().parseEIntegerObject(xml.getAttribute("minimumHeight")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public ObjectEditor getEditorToOpen() {
		return this.editorToOpen;
	}
	
	public UimField getField() {
		return this.field;
	}
	
	public String getMimumWidth() {
		return this.mimumWidth;
	}
	
	public Integer getMinimumHeight() {
		return this.minimumHeight;
	}
	
	public void populateReferencesFromXml(Element xml) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("editorToOpen") ) {
				setEditorToOpen((org.opaeum.uim.editor.ObjectEditor)this.eResource().getResourceSet().getReference((Element)currentPropertyNode));
			}
		}
	}
	
	public void setEditorToOpen(ObjectEditor editorToOpen) {
		this.editorToOpen=editorToOpen;
	}
	
	public void setField(UimField field) {
		this.field=field;
	}
	
	public void setMimumWidth(String mimumWidth) {
		this.mimumWidth=mimumWidth;
	}
	
	public void setMinimumHeight(Integer minimumHeight) {
		this.minimumHeight=minimumHeight;
	}

}