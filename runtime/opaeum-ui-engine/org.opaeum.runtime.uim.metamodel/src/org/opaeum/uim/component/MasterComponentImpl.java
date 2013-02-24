package org.opaeum.uim.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.ecore.EObjectImpl;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MasterComponentImpl extends EObjectImpl implements MasterComponent {
	private List<DetailComponent> detailComponents = new ArrayList<DetailComponent>();
	private String uid;


	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("xmi:id"));
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public List<DetailComponent> getDetailComponents() {
		return this.detailComponents;
	}
	
	public String getUid() {
		return this.uid;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("detailComponents") ) {
				getDetailComponents().add((org.opaeum.uim.component.DetailComponent)map.get(((Element)currentPropertyNode).getAttribute("xmi:id")));
			}
		}
	}
	
	public void setDetailComponents(List<DetailComponent> detailComponents) {
		this.detailComponents=detailComponents;
	}
	
	public void setUid(String uid) {
		this.uid=uid;
	}

}