package org.nakeduml.runtime.bpm;

import org.nakeduml.hibernate.domain.AbstractEnumResolver;
import org.nakeduml.runtime.domain.EnumResolver;
import org.nakeduml.runtime.domain.IEnum;

public class TaskRequestStateResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromNakedUmlId(int i) {
		IEnum result = null;
		switch ( i ) {
			case 1055:
				result = TaskRequestState.TASKINSTANCEREGION_SUSPENDED_REGION1_INPROGRESSBUTSUSPENDED;
			break;
		
			case 1045:
				result = TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_FINALSTATE1;
			break;
		
			case 1056:
				result = TaskRequestState.TASKINSTANCEREGION_SUSPENDED_REGION1_READYBUTSUSPENDED;
			break;
		
			case 1050:
				result = TaskRequestState.TASKINSTANCEREGION_COMPLETED;
			break;
		
			case 1052:
				result = TaskRequestState.TASKINSTANCEREGION_SUSPENDED;
			break;
		
			case 1058:
				result = TaskRequestState.TASKINSTANCEREGION_OBSOLETE;
			break;
		
			case 1042:
				result = TaskRequestState.TASKINSTANCEREGION_ACTIVE;
			break;
		
			case 1047:
				result = TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_INPROGRESS;
			break;
		
			case 1049:
				result = TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_READY;
			break;
		
			case 1054:
				result = TaskRequestState.TASKINSTANCEREGION_SUSPENDED_REGION1_RESERVEDBUTSUSPENDED;
			break;
		
			case 1044:
				result = TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_RESERVED;
			break;
		
			case 1051:
				result = TaskRequestState.TASKINSTANCEREGION_CREATED;
			break;
		
		}
		
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.nakeduml.runtime.bpm.TaskRequestState.class;
	}
	
	public int toNakedUmlId(IEnum en) {
		int result = -1;
		switch ( (TaskRequestState)en ) {
			case TASKINSTANCEREGION_SUSPENDED_REGION1_INPROGRESSBUTSUSPENDED:
				result = 1055;
			break;
		
			case TASKINSTANCEREGION_ACTIVE_REGION1_FINALSTATE1:
				result = 1045;
			break;
		
			case TASKINSTANCEREGION_SUSPENDED_REGION1_READYBUTSUSPENDED:
				result = 1056;
			break;
		
			case TASKINSTANCEREGION_COMPLETED:
				result = 1050;
			break;
		
			case TASKINSTANCEREGION_SUSPENDED:
				result = 1052;
			break;
		
			case TASKINSTANCEREGION_OBSOLETE:
				result = 1058;
			break;
		
			case TASKINSTANCEREGION_ACTIVE:
				result = 1042;
			break;
		
			case TASKINSTANCEREGION_ACTIVE_REGION1_INPROGRESS:
				result = 1047;
			break;
		
			case TASKINSTANCEREGION_ACTIVE_REGION1_READY:
				result = 1049;
			break;
		
			case TASKINSTANCEREGION_SUSPENDED_REGION1_RESERVEDBUTSUSPENDED:
				result = 1054;
			break;
		
			case TASKINSTANCEREGION_ACTIVE_REGION1_RESERVED:
				result = 1044;
			break;
		
			case TASKINSTANCEREGION_CREATED:
				result = 1051;
			break;
		
		}
		
		return result;
	}

}