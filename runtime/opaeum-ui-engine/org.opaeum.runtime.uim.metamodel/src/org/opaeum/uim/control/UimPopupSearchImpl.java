package org.opaeum.uim.control;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
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
	private String uid;


	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("xmi:id"));
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
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
				curVal.setLookup(this);
			}
		}
	}
	
	public EObject eContainer() {
		EObject result = null;
		
		return result;
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
	
	public String getUid() {
		return this.uid;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("lookupSource") ) {
				((org.opaeum.uim.binding.LookupBinding)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
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
	
	public void setUid(String uid) {
		this.uid=uid;
	}

}