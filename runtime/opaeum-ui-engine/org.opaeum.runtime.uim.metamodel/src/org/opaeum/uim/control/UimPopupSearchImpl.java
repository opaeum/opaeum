package org.opaeum.uim.control;

import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.binding.LookupBinding;
import org.opaeum.uim.component.UimField;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UimPopupSearchImpl extends EObjectImpl implements UimPopupSearch {
	private UimField field;
	private LookupBinding lookupSource;
	private String mimumWidth;
	private Integer minimumHeight;


	public void buildTreeFromXml(Element xml) {
		if ( xml.getAttribute("mimumWidth").length()>0 ) {
			setMimumWidth(EcoreDataTypeParser.getInstance().parseEString(xml.getAttribute("mimumWidth")));
		}
		if ( xml.getAttribute("minimumHeight").length()>0 ) {
			setMinimumHeight(EcoreDataTypeParser.getInstance().parseEIntegerObject(xml.getAttribute("minimumHeight")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("lookupSource") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				LookupBinding curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="bind:LookupBinding";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setLookupSource(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
				curVal.setLookup(this);
			}
		}
	}
	
	public UimField getField() {
		return this.field;
	}
	
	public LookupBinding getLookupSource() {
		return this.lookupSource;
	}
	
	public String getMimumWidth() {
		return this.mimumWidth;
	}
	
	public Integer getMinimumHeight() {
		return this.minimumHeight;
	}
	
	public void populateReferencesFromXml(Element xml) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("lookupSource") ) {
				((org.opaeum.uim.binding.LookupBinding)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
		}
	}
	
	public void setField(UimField field) {
		this.field=field;
	}
	
	public void setLookupSource(LookupBinding lookupSource) {
		this.lookupSource=lookupSource;
	}
	
	public void setMimumWidth(String mimumWidth) {
		this.mimumWidth=mimumWidth;
	}
	
	public void setMinimumHeight(Integer minimumHeight) {
		this.minimumHeight=minimumHeight;
	}

}