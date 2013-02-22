package org.opaeum.uim.cube;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.rap.metamodels.uim.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RowAxisEntryImpl extends EObjectImpl implements RowAxisEntry {
	private DimensionBinding dimensionBinding;
	private List<LevelProperty> levelProperty = new ArrayList<LevelProperty>();
	private String uid;


	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("xmi:id"));
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("dimensionBinding") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				DimensionBinding curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="cube:DimensionBinding";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setDimensionBinding(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("levelProperty") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				LevelProperty curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="cube:LevelProperty";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.getLevelProperty().add(curVal);
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
	
	public DimensionBinding getDimensionBinding() {
		return this.dimensionBinding;
	}
	
	public List<LevelProperty> getLevelProperty() {
		return this.levelProperty;
	}
	
	public String getUid() {
		return this.uid;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("dimensionBinding") ) {
				((org.opaeum.uim.cube.DimensionBinding)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("levelProperty") ) {
				((org.opaeum.uim.cube.LevelProperty)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
		}
	}
	
	public void setDimensionBinding(DimensionBinding dimensionBinding) {
		this.dimensionBinding=dimensionBinding;
	}
	
	public void setLevelProperty(List<LevelProperty> levelProperty) {
		this.levelProperty=levelProperty;
	}
	
	public void setUid(String uid) {
		this.uid=uid;
	}

}