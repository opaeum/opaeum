package org.opaeum.uim.component;

import java.util.ArrayList;
import java.util.List;

import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MasterComponentImpl extends EObjectImpl implements MasterComponent {
	private List<DetailComponent> detailComponents = new ArrayList<DetailComponent>();


	public void buildTreeFromXml(Element xml) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public List<DetailComponent> getDetailComponents() {
		return this.detailComponents;
	}
	
	public void populateReferencesFromXml(Element xml) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("detailComponents") ) {
				setDetailComponents((java.util.List)this.eResource().getResourceSet().getReferences((Element)currentPropertyNode));
			}
		}
	}
	
	public void setDetailComponents(List<DetailComponent> detailComponents) {
		this.detailComponents=detailComponents;
	}

}