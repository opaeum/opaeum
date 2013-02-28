package org.opaeum.uim;

import java.util.Map;

import org.opaeum.ecore.EAnnotation;
import org.opaeum.ecore.EAnnotationImpl;
import org.opaeum.ecore.EObject;
import org.opaeum.ecore.EStringToStringMapEntry;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LabelsImpl extends EAnnotationImpl implements Labels {
	private String uid;


	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("xmi:id"));
		if ( xml.getAttribute("source").length()>0 ) {
			setSource(EcoreDataTypeParser.getInstance().parseEString(xml.getAttribute("source")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("eAnnotations") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				EAnnotation curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="EAnnotation";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.getEAnnotations().add(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
				curVal.setEModelElement(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("details") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				EStringToStringMapEntry curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="EStringToStringMapEntry";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.getDetails().add(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("contents") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				EObject curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="EObject";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.getContents().add(curVal);
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
	
	public String getUid() {
		return this.uid;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("eAnnotations") ) {
				((org.opaeum.ecore.EAnnotation)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("details") ) {
				((org.opaeum.ecore.EStringToStringMapEntry)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("contents") ) {
				((org.opaeum.ecore.EObject)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("references") ) {
				getReferences().add((org.opaeum.ecore.EObject)map.get(((Element)currentPropertyNode).getAttribute("xmi:id")));
			}
		}
	}
	
	public void setUid(String uid) {
		this.uid=uid;
	}

}