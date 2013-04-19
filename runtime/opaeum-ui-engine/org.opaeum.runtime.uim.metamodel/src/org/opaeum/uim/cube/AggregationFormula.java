package org.opaeum.uim.cube;


public enum AggregationFormula {
	SUM,
	AVERAGE,
	COUNT;


	static public AggregationFormula getByName(String name) {
		AggregationFormula result = null;
		if ( "SUM".equals(name) ) {
			return SUM;
		}
		if ( "AVERAGE".equals(name) ) {
			return AVERAGE;
		}
		if ( "COUNT".equals(name) ) {
			return COUNT;
		}
		return result;
	}

}