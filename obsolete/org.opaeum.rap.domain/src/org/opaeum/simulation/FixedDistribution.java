package org.opaeum.simulation;

import org.apache.commons.math3.distribution.AbstractIntegerDistribution;

public class FixedDistribution extends AbstractIntegerDistribution{
	int value =0;
	public FixedDistribution(int value){
		super();
		this.value = value;
	}
	@Override
	public double probability(int x){
		return value==x?1:0;
	}
	@Override
	public double cumulativeProbability(int x){
		return value==x?1:0;
	}
	@Override
	public double getNumericalMean(){
		return value;
	}
	@Override
	public double getNumericalVariance(){
		return 0;
	}
	@Override
	public int getSupportLowerBound(){
		return value;
	}
	@Override
	public int getSupportUpperBound(){
		return value;
	}
	@Override
	public boolean isSupportConnected(){
		return true;
	}
}
