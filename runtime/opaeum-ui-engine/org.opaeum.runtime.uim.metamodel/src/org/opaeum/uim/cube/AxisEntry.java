package org.opaeum.uim.cube;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface AxisEntry extends EObject {
	public void buildTreeFromXml(Element xml);
	
	public DimensionBinding getDimensionBinding();
	
	public List<LevelProperty> getLevelProperty();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setDimensionBinding(DimensionBinding dimensionBinding);
	
	public void setLevelProperty(List<LevelProperty> levelProperty);
	
	public void setUid(String uid);

}