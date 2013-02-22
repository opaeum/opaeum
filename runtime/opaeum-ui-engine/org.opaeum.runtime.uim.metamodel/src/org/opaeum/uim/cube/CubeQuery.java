package org.opaeum.uim.cube;

import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.Page;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface CubeQuery extends EObject, Page {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public List<ColumnAxisEntry> getColumnAxis();
	
	public List<MeasureProperty> getMeasures();
	
	public List<RowAxisEntry> getRowAxis();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setColumnAxis(List<ColumnAxisEntry> columnAxis);
	
	public void setMeasures(List<MeasureProperty> measures);
	
	public void setRowAxis(List<RowAxisEntry> rowAxis);
	
	public void setUid(String uid);

}