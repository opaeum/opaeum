package org.opaeum.simulation;

import org.apache.commons.math3.distribution.UniformRealDistribution;


public class RangeBucket extends AbstractBucket {
	private UniformRealDistribution distribution;
	public RangeBucket(double lowerValue, double upperValue,double ratio2){
		super(ratio2);
		this.distribution=new UniformRealDistribution(lowerValue,upperValue);
	}
	public Object nextValue(){
		count++;
		return distribution.sample();
	}
}