package org.opaeum.uim.perspective;

import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OutboxConfigurationImpl extends EObjectImpl implements OutboxConfiguration {
	private Integer height;
	private String name;
	private PositionInPerspective position;
	private boolean underUserControl;
	private Integer width;


	public void buildTreeFromXml(Element xml) {
		if ( xml.getAttribute("name").length()>0 ) {
			setName(EcoreDataTypeParser.getInstance().parseEString(xml.getAttribute("name")));
		}
		if ( xml.getAttribute("underUserControl").length()>0 ) {
			setUnderUserControl(EcoreDataTypeParser.getInstance().parseEBoolean(xml.getAttribute("underUserControl")));
		}
		if ( xml.getAttribute("width").length()>0 ) {
			setWidth(EcoreDataTypeParser.getInstance().parseEIntegerObject(xml.getAttribute("width")));
		}
		if ( xml.getAttribute("height").length()>0 ) {
			setHeight(EcoreDataTypeParser.getInstance().parseEIntegerObject(xml.getAttribute("height")));
		}
		if ( xml.getAttribute("position").length()==0 ) {
			setPosition(PositionInPerspective.values()[0]);
		} else {
			setPosition(PositionInPerspective.getByName(xml.getAttribute("position")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public Integer getHeight() {
		return this.height;
	}
	
	public String getName() {
		return this.name;
	}
	
	public PositionInPerspective getPosition() {
		return this.position;
	}
	
	public Integer getWidth() {
		return this.width;
	}
	
	public boolean isUnderUserControl() {
		return this.underUserControl;
	}
	
	public void populateReferencesFromXml(Element xml) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void setHeight(Integer height) {
		this.height=height;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setPosition(PositionInPerspective position) {
		this.position=position;
	}
	
	public void setUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setWidth(Integer width) {
		this.width=width;
	}

}