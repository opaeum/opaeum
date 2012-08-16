package org.opaeum.runtime.activities;


public class OutputPinActivation<AE extends IActivityNodeContainerExecution, T extends IActivityToken<AE>>   extends PinActivation<AE,T>{

	public OutputPinActivation(AE group,String id,String name, int lower,int upper){
		super(group, id, name, lower, upper);
	}
}
