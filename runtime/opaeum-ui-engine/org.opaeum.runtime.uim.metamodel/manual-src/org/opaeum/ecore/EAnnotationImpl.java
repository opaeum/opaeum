package org.opaeum.ecore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.opaeum.org.opaeum.rap.metamodels.uim.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EAnnotationImpl extends EModelElementImpl implements EAnnotation{
	private EModelElement eModelElement;
	Map<String,String> detailsMap;
	List<EObject> contents = new ArrayList<EObject>();
	List<EObject> references = new ArrayList<EObject>();
	List<EStringToStringMapEntry> details = new ArrayList<EStringToStringMapEntry>();
	private String src;
	@Override
	public List<EObject> getContents(){
		return contents;
	}
	public List<EStringToStringMapEntry> getDetails(){
		return details;
	}
	@Override
	public void setSource(String src){
		this.src = src;
	}
	@Override
	public String getSource(){
		return src;
	}
	@Override
	public List<EObject> getReferences(){
		return references;
	}
	public void populateReferencesFromXml(Element xml,Map<String,Object> map){
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while(i < propertyNodes.getLength()){
			Node currentPropertyNode = propertyNodes.item(i++);
			if(currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("eAnnotations")){
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while(j < propertyValueNodes.getLength()){
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if(currentPropertyValueNode instanceof Element){
						((org.opaeum.ecore.EAnnotation) map.get(((Element) currentPropertyValueNode).getAttributeNS("http://www.omg.org/XMI", "id")))
								.populateReferencesFromXml((Element) currentPropertyValueNode, map);
					}
				}
			}
			if(currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("details")){
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while(j < propertyValueNodes.getLength()){
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if(currentPropertyValueNode instanceof Element){
						((org.opaeum.ecore.EStringToStringMapEntry) map.get(((Element) currentPropertyValueNode).getAttributeNS(
								"http://www.omg.org/XMI", "id"))).populateReferencesFromXml((Element) currentPropertyValueNode, map);
					}
				}
			}
			if(currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("eModelElement")){
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while(j < propertyValueNodes.getLength()){
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if(currentPropertyValueNode instanceof Element){
						setEModelElement((org.opaeum.ecore.EModelElement) map.get(((Element) currentPropertyValueNode).getAttributeNS(
								"http://www.omg.org/XMI", "id")));
					}
				}
			}
			if(currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("contents")){
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while(j < propertyValueNodes.getLength()){
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if(currentPropertyValueNode instanceof Element){
						((org.opaeum.ecore.EObject) map.get(((Element) currentPropertyValueNode).getAttributeNS("http://www.omg.org/XMI", "id")))
								.populateReferencesFromXml((Element) currentPropertyValueNode, map);
					}
				}
			}
			if(currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("references")){
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while(j < propertyValueNodes.getLength()){
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if(currentPropertyValueNode instanceof Element){
						getReferences().add(
								(org.opaeum.ecore.EObject) map.get(((Element) currentPropertyValueNode).getAttributeNS("http://www.omg.org/XMI", "id")));
					}
				}
			}
		}
	}
	public EModelElement getEModelElement(){
		return eModelElement;
	}
	public void setEModelElement(EModelElement eModelElement){
		this.eModelElement = eModelElement;
	}
	@Override
	public void buildTreeFromXml(Element xml,Map<String,Object> map){
		setUid(xml.getAttribute("xmi:id"));
		if(xml.getAttribute("source").length() > 0){
			setSource(EcoreDataTypeParser.getInstance().parseEString(xml.getAttribute("source")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while(i < propertyNodes.getLength()){
			Node currentPropertyNode = propertyNodes.item(i++);
			if(currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("eAnnotations")){
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while(j < propertyValueNodes.getLength()){
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if(currentPropertyValueNode instanceof Element){
						String typeString = ((Element) currentPropertyValueNode).getAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "type");
						EAnnotation curVal;
						if(typeString == null || typeString.trim().length() == 0){
							typeString = "EAnnotation";
						}
						curVal = UimInstantiator.INSTANCE.newInstance(typeString);
						this.getEAnnotations().add(curVal);
						map.put(curVal.getUid(), curVal);
						curVal.eContainer(this);
						curVal.buildTreeFromXml((Element) currentPropertyValueNode, map);
					}
				}
			}
			if(currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("details")){
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while(j < propertyValueNodes.getLength()){
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if(currentPropertyValueNode instanceof Element){
						String typeString = ((Element) currentPropertyValueNode).getAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "type");
						EStringToStringMapEntry curVal;
						if(typeString == null || typeString.trim().length() == 0){
							typeString = "EStringToStringMapEntry";
						}
						curVal = UimInstantiator.INSTANCE.newInstance(typeString);
						this.getDetails().add(curVal);
						map.put(curVal.getUid(), curVal);
						curVal.eContainer(this);
						curVal.buildTreeFromXml((Element) currentPropertyValueNode, map);
					}
				}
			}
			if(currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("contents")){
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while(j < propertyValueNodes.getLength()){
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if(currentPropertyValueNode instanceof Element){
						String typeString = ((Element) currentPropertyValueNode).getAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "type");
						EObject curVal;
						if(typeString == null || typeString.trim().length() == 0){
							typeString = "EObject";
						}
						curVal = UimInstantiator.INSTANCE.newInstance(typeString);
						this.getContents().add(curVal);
						map.put(curVal.getUid(), curVal);
						curVal.eContainer(this);
						curVal.buildTreeFromXml((Element) currentPropertyValueNode, map);
					}
				}
			}
		}
	}
}
