package org.opaeum.uim;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.rap.metamodels.uim.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PageOrderingImpl extends EObjectImpl implements PageOrdering {
	private Labels labelOverride;
	private Page page;
	private int position;
	private String uid;


	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("xmi:id"));
		if ( xml.getAttribute("position").length()>0 ) {
			setPosition(EcoreDataTypeParser.getInstance().parseEInt(xml.getAttribute("position")));
		}
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
	
	public Labels getLabelOverride() {
		return this.labelOverride;
	}
	
	public Page getPage() {
		return this.page;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public String getUid() {
		return this.uid;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("page") ) {
				setPage((org.opaeum.uim.Page)map.get(((Element)currentPropertyNode).getAttribute("xmi:id")));
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("labelOverride") ) {
				setLabelOverride((org.opaeum.uim.Labels)map.get(((Element)currentPropertyNode).getAttribute("xmi:id")));
			}
		}
	}
	
	public void setLabelOverride(Labels labelOverride) {
		this.labelOverride=labelOverride;
	}
	
	public void setPage(Page page) {
		this.page=page;
	}
	
	public void setPosition(int position) {
		this.position=position;
	}
	
	public void setUid(String uid) {
		this.uid=uid;
	}

}