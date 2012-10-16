package org.opaeum.simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DistributionAcrossObjects extends ValueProvider{
	List<ObjectBucket> buckets = new ArrayList<ObjectBucket>();
	public Object getNextValue(){
		Collections.sort(buckets);
		return buckets.get(0).nextValue();
	}
}