package org.opaeum.runtime.domain;




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
