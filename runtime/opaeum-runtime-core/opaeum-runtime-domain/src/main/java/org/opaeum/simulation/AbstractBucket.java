package org.opaeum.simulation;

public class AbstractBucket implements Comparable<AbstractBucket>{
	protected int count;
	protected double ratio;

	public AbstractBucket(){
		super();
	}

	public AbstractBucket(double ratio2){
		ratio=ratio2;
	}

	@Override
	public int compareTo(AbstractBucket o){
		if(getWeightedCount() >= o.getWeightedCount()){
			return 1;
		}else{
			return -1;
		}
	}

	private double getWeightedCount(){
		return count * ratio;
	}
}