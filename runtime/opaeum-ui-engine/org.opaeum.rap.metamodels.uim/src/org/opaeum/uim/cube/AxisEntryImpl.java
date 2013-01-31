package org.opaeum.uim.cube;

import java.util.List;

import org.opaeum.ecore.EObject;

public class AxisEntryImpl implements AxisEntry {
	private DimensionBinding dimensionBinding;
	private List<LevelProperty> levelProperty;


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
	
	public void setDimensionBinding(DimensionBinding dimensionBinding) {
		this.dimensionBinding=dimensionBinding;
	}
	
	public void setLevelProperty(List<LevelProperty> levelProperty) {
		this.levelProperty=levelProperty;
	}

}