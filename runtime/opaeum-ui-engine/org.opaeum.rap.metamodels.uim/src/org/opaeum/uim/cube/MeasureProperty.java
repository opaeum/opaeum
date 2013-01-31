package org.opaeum.uim.cube;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UmlReference;

public interface MeasureProperty extends EObject, UmlReference {
	public AggregationFormula getAggregationFormula();
	
	public void setAggregationFormula(AggregationFormula aggregationFormula);
	
	public String toString();

}