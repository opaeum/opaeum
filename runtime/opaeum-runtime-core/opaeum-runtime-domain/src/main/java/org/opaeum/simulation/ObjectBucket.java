package org.opaeum.simulation;


public class ObjectBucket extends AbstractBucket{
	Object value;
	public ObjectBucket(Object en,double ratio2){
		super(ratio2);
		value = en;
	}
	public Object nextValue(){
		count++;
		return value;
	}
}