package org.opaeum.runtime.activities;


public class MergeNodeActivation<AE extends IActivityNodeContainerExecution, T extends IActivityToken<AE>>   extends ControlNodeActivation<AE,T>{

	public MergeNodeActivation(AE group,String id){
		super(group, id);
	}
}
