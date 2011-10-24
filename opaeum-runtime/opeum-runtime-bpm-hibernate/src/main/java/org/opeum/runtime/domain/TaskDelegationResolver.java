package org.opaeum.runtime.domain;

import org.opeum.runtime.domain.EnumResolver;
import org.opeum.runtime.domain.IEnum;
import org.opeum.runtime.domain.TaskDelegation;



public class TaskDelegationResolver implements EnumResolver{

	@Override
	public IEnum fromOpeumId(int i){
		return TaskDelegation.values()[i];
	}

	@Override
	public int toOpeumId(IEnum e){
		TaskDelegation[] values = TaskDelegation.values();
		for(int i = 0; i < values.length; i++){
			if(values[i]==e){
				return i;
			}
		}
		return -1;
	}
}
