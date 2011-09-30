package org.opeum.runtime.bpm;

import org.opeum.hibernate.domain.AbstractEnumResolver;
import org.opeum.runtime.domain.EnumResolver;
import org.opeum.runtime.domain.IEnum;

public class TaskRequestStateResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpeumId(int i) {
		IEnum result = null;
		switch ( i ) {
			case 196:
				result = TaskRequestState.TASKINSTANCEREGION_SUSPENDED_REGION1_INPROGRESSBUTSUSPENDED;
			break;
		
			case 204:
				result = TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_FINALSTATE1;
			break;
		
			case 198:
				result = TaskRequestState.TASKINSTANCEREGION_SUSPENDED_REGION1_READYBUTSUSPENDED;
			break;
		
			case 209:
				result = TaskRequestState.TASKINSTANCEREGION_COMPLETED;
			break;
		
			case 194:
				result = TaskRequestState.TASKINSTANCEREGION_SUSPENDED;
			break;
		
			case 200:
				result = TaskRequestState.TASKINSTANCEREGION_OBSOLETE;
			break;
		
			case 201:
				result = TaskRequestState.TASKINSTANCEREGION_ACTIVE;
			break;
		
			case 205:
				result = TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_INPROGRESS;
			break;
		
			case 203:
				result = TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_READY;
			break;
		
			case 197:
				result = TaskRequestState.TASKINSTANCEREGION_SUSPENDED_REGION1_RESERVEDBUTSUSPENDED;
			break;
		
			case 208:
				result = TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_RESERVED;
			break;
		
			case 199:
				result = TaskRequestState.TASKINSTANCEREGION_CREATED;
			break;
		
		}
		
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opeum.runtime.bpm.TaskRequestState.class;
	}
	
	public int toOpeumId(IEnum en) {
		int result = -1;
		switch ( (TaskRequestState)en ) {
			case TASKINSTANCEREGION_SUSPENDED_REGION1_INPROGRESSBUTSUSPENDED:
				result = 196;
			break;
		
			case TASKINSTANCEREGION_ACTIVE_REGION1_FINALSTATE1:
				result = 204;
			break;
		
			case TASKINSTANCEREGION_SUSPENDED_REGION1_READYBUTSUSPENDED:
				result = 198;
			break;
		
			case TASKINSTANCEREGION_COMPLETED:
				result = 209;
			break;
		
			case TASKINSTANCEREGION_SUSPENDED:
				result = 194;
			break;
		
			case TASKINSTANCEREGION_OBSOLETE:
				result = 200;
			break;
		
			case TASKINSTANCEREGION_ACTIVE:
				result = 201;
			break;
		
			case TASKINSTANCEREGION_ACTIVE_REGION1_INPROGRESS:
				result = 205;
			break;
		
			case TASKINSTANCEREGION_ACTIVE_REGION1_READY:
				result = 203;
			break;
		
			case TASKINSTANCEREGION_SUSPENDED_REGION1_RESERVEDBUTSUSPENDED:
				result = 197;
			break;
		
			case TASKINSTANCEREGION_ACTIVE_REGION1_RESERVED:
				result = 208;
			break;
		
			case TASKINSTANCEREGION_CREATED:
				result = 199;
			break;
		
		}
		
		return result;
	}

}