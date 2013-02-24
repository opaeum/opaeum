package org.opaeum.uim.cube;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UmlReference;
import org.w3c.dom.Element;

public interface MeasureProperty extends EObject, UmlReference {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public AggregationFormula getAggregationFormula();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setAggregationFormula(AggregationFormula aggregationFormula);
	
	public void setUid(String uid);
	
	public String toString();

}