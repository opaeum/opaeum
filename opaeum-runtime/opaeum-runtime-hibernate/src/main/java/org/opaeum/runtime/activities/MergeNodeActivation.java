package org.opaeum.runtime.activities;

public class MergeNodeActivation<AE extends IActivityNodeContainerExecution, T extends ActivityToken<AE>>   extends ControlNodeActivation<AE,T>{

	public MergeNodeActivation(AE group,String id){
		super(group, id);
	}
}
