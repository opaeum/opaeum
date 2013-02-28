package org.opaeum.uim.binding;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.ecore.EObjectImpl;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.uim.UimInstantiator;
import org.opaeum.uim.component.UimField;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FieldBindingImpl extends EObjectImpl implements FieldBinding {
	private UimField field;
	private PropertyRef next;
	private String uid;
	private String umlElementUid;


	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("xmi:id"));
		if ( xml.getAttribute("umlElementUid").length()>0 ) {
			setUmlElementUid(EcoreDataTypeParser.getInstance().parseEString(xml.getAttribute("umlElementUid")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("next") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				PropertyRef curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="bind:PropertyRef";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setNext(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
				curVal.setBinding(this);
			}
		}
	}
	
	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public UimField getField() {
		return this.field;
	}
	
	public String getLastPropertyUuid() {
		String result = null;
		
		return result;
	}
	
	public PropertyRef getNext() {
		return this.next;
	}
	
	public String getUid() {
		return this.uid;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("next") ) {
				((org.opaeum.uim.binding.PropertyRef)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
		}
	}
	
	public void setField(UimField field) {
		this.field=field;
	}
	
	public void setNext(PropertyRef next) {
		this.next=next;
	}
	
	public void setUid(String uid) {
		this.uid=uid;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}

}