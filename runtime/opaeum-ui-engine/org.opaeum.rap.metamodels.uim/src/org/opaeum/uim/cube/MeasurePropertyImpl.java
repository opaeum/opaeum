package org.opaeum.uim.cube;

import org.opaeum.ecore.EObject;

public class MeasurePropertyImpl implements MeasureProperty {
	private AggregationFormula aggregationFormula;
	private String umlElementUid;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public AggregationFormula getAggregationFormula() {
		return this.aggregationFormula;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public void setAggregationFormula(AggregationFormula aggregationFormula) {
		this.aggregationFormula=aggregationFormula;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}
	
	public String toString() {
		String result = null;
		
		return result;
	}

}