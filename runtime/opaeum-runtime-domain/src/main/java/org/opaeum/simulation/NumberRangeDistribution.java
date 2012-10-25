package org.opaeum.simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberRangeDistribution extends ValueProvider{
	boolean integer = false;
	public NumberRangeDistribution(boolean integer){
		super();
		this.integer = integer;
	}
	List<NumberRange> buckets = new ArrayList<NumberRange>();
	public Number getNextValue(){
		Collections.sort(buckets);
		Double nextValue = buckets.get(0).nextValue();
		if(integer){
			return (int)Math.round(nextValue.doubleValue());
		}else{
			return nextValue;
		}
	}
}