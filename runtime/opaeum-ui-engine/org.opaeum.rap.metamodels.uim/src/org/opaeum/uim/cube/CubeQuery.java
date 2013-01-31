package org.opaeum.uim.cube;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.Page;

public interface CubeQuery extends EObject, Page {
	public List<ColumnAxisEntry> getColumnAxis();
	
	public List<MeasureProperty> getMeasures();
	
	public List<RowAxisEntry> getRowAxis();
	
	public void setColumnAxis(List<ColumnAxisEntry> columnAxis);
	
	public void setMeasures(List<MeasureProperty> measures);
	
	public void setRowAxis(List<RowAxisEntry> rowAxis);

}