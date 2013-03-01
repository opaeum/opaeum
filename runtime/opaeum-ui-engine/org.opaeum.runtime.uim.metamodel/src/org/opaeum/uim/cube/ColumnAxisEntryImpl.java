package org.opaeum.uim.cube;

import java.util.ArrayList;
import java.util.List;

import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ColumnAxisEntryImpl extends EObjectImpl implements ColumnAxisEntry {
	private DimensionBinding dimensionBinding;
	private List<LevelProperty> levelProperty = new ArrayList<LevelProperty>();


	public void buildTreeFromXml(Element xml) {
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
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("levelProperty") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				LevelProperty curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="cube:LevelProperty";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.getLevelProperty().add(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
			}
		}
	}
	
	public DimensionBinding getDimensionBinding() {
		return this.dimensionBinding;
	}
	
	public List<LevelProperty> getLevelProperty() {
		return this.levelProperty;
	}
	
	public void populateReferencesFromXml(Element xml) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("dimensionBinding") ) {
				((org.opaeum.uim.cube.DimensionBinding)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("levelProperty") ) {
				((org.opaeum.uim.cube.LevelProperty)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
		}
	}
	
	public void setDimensionBinding(DimensionBinding dimensionBinding) {
		this.dimensionBinding=dimensionBinding;
	}
	
	public void setLevelProperty(List<LevelProperty> levelProperty) {
		this.levelProperty=levelProperty;
	}

}