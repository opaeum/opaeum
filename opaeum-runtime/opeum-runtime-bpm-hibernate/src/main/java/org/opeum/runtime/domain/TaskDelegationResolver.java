package org.opaeum.runtime.domain;

import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;
import org.opaeum.runtime.domain.TaskDelegation;



public class TaskDelegationResolver implements EnumResolver{

	@Override
	public IEnum fromOpaeumId(int i){
		return TaskDelegation.values()[i];
	}

	@Override
	public int toOpaeumId(IEnum e){
		TaskDelegation[] values = TaskDelegation.values();
		for(int i = 0; i < values.length; i++){
			if(values[i]==e){
				return i;
			}
		}
		return -1;
	}
}
