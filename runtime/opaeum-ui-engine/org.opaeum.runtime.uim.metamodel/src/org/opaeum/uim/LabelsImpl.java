package org.opaeum.uim;

import org.opaeum.ecore.EAnnotation;
import org.opaeum.ecore.EAnnotationImpl;
import org.opaeum.ecore.EObject;
import org.opaeum.ecore.EStringToStringMapEntry;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LabelsImpl extends EAnnotationImpl implements Labels {



	public void buildTreeFromXml(Element xml) {
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
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
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
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("contents") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				EObject curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="EObject";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.getContents().add(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
			}
		}
	}
	
	public void populateReferencesFromXml(Element xml) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("eAnnotations") ) {
				((org.opaeum.ecore.EAnnotation)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("details") ) {
				((org.opaeum.ecore.EStringToStringMapEntry)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("contents") ) {
				((org.opaeum.ecore.EObject)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("references") ) {
				setReferences((java.util.List)this.eResource().getResourceSet().getReferences((Element)currentPropertyNode));
			}
		}
	}

}