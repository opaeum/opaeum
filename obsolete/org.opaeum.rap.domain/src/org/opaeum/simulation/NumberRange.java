package org.opaeum.simulation;

import org.apache.commons.math3.distribution.UniformRealDistribution;

public class NumberRange extends AbstractBucket{
	private UniformRealDistribution distribution;
	public NumberRange(double lower,double upper,double ratio2){
		this.distribution = new UniformRealDistribution(lower, upper);
	}
	public Double nextValue(){
		count++;
		return distribution.sample();
	}
}
