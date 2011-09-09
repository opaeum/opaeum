package org.nakeduml.runtime.domain;


public class TaskDelegationResolver implements EnumResolver{

	@Override
	public IEnum fromNakedUmlId(int i){
		return TaskDelegation.values()[i];
	}

	@Override
	public int toNakedUmlId(IEnum e){
		TaskDelegation[] values = TaskDelegation.values();
		for(int i = 0; i < values.length; i++){
			if(values[i]==e){
				return i;
			}
		}
		return -1;
	}
}
