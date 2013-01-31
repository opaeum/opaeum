package org.opaeum.uim.cube;

import java.util.List;

import org.opaeum.ecore.EObject;

public interface AxisEntry extends EObject {
	public DimensionBinding getDimensionBinding();
	
	public List<LevelProperty> getLevelProperty();
	
	public void setDimensionBinding(DimensionBinding dimensionBinding);
	
	public void setLevelProperty(List<LevelProperty> levelProperty);

}