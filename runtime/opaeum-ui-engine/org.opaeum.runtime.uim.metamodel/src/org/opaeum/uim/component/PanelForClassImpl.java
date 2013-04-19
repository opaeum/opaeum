package org.opaeum.uim.component;

import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.panel.AbstractPanel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PanelForClassImpl extends EObjectImpl implements PanelForClass {
	private DetailComponent detailComponent;
	private AbstractPanel panel;
	private String umlElementUid;


	public void buildTreeFromXml(Element xml) {
		if ( xml.getAttribute("umlElementUid").length()>0 ) {
			setUmlElementUid(EcoreDataTypeParser.getInstance().parseEString(xml.getAttribute("umlElementUid")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public DetailComponent getDetailComponent() {
		return this.detailComponent;
	}
	
	public AbstractPanel getPanel() {
		return this.panel;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public void populateReferencesFromXml(Element xml) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("panel") ) {
				setPanel((org.opaeum.uim.panel.AbstractPanel)this.eResource().getResourceSet().getReference((Element)currentPropertyNode));
			}
		}
	}
	
	public void setDetailComponent(DetailComponent detailComponent) {
		this.detailComponent=detailComponent;
	}
	
	public void setPanel(AbstractPanel panel) {
		this.panel=panel;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}

}