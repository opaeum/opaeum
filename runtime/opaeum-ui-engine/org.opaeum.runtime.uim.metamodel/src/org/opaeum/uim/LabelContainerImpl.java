package org.opaeum.uim;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.ecore.EObjectImpl;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LabelContainerImpl extends EObjectImpl implements LabelContainer {
	private Labels labelOverride;
	private String uid;


	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("xmi:id"));
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
	
	public Labels getLabelOverride() {
		return this.labelOverride;
	}
	
	public String getUid() {
		return this.uid;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("labelOverride") ) {
				((org.opaeum.uim.Labels)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
		}
	}
	
	public void setLabelOverride(Labels labelOverride) {
		this.labelOverride=labelOverride;
	}
	
	public void setUid(String uid) {
		this.uid=uid;
	}

}