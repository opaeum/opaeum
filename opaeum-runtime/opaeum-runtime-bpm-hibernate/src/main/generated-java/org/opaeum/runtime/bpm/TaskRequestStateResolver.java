package org.opaeum.runtime.bpm;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class TaskRequestStateResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(int i) {
		IEnum result = null;
		switch ( i ) {
			case 510:
				result = TaskRequestState.SUSPENDED_INPROGRESSBUTSUSPENDED;
			break;
		
			case 498:
				result = TaskRequestState.ACTIVE_FINALSTATE1;
			break;
		
			case 508:
				result = TaskRequestState.SUSPENDED_READYBUTSUSPENDED;
			break;
		
			case 511:
				result = TaskRequestState.COMPLETED;
			break;
		
			case 506:
				result = TaskRequestState.SUSPENDED;
			break;
		
			case 503:
				result = TaskRequestState.OBSOLETE;
			break;
		
			case 495:
				result = TaskRequestState.ACTIVE;
			break;
		
			case 501:
				result = TaskRequestState.ACTIVE_READY;
			break;
		
			case 500:
				result = TaskRequestState.ACTIVE_INPROGRESS;
			break;
		
			case 509:
				result = TaskRequestState.SUSPENDED_RESERVEDBUTSUSPENDED;
			break;
		
			case 499:
				result = TaskRequestState.ACTIVE_RESERVED;
			break;
		
			case 504:
				result = TaskRequestState.CREATED;
			break;
		
		}
		
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.runtime.bpm.TaskRequestState.class;
	}
	
	public int toOpaeumId(IEnum en) {
		int result = -1;
		switch ( (TaskRequestState)en ) {
			case SUSPENDED_INPROGRESSBUTSUSPENDED:
				result = 510;
			break;
		
			case ACTIVE_FINALSTATE1:
				result = 498;
			break;
		
			case SUSPENDED_READYBUTSUSPENDED:
				result = 508;
			break;
		
			case COMPLETED:
				result = 511;
			break;
		
			case SUSPENDED:
				result = 506;
			break;
		
			case OBSOLETE:
				result = 503;
			break;
		
			case ACTIVE:
				result = 495;
			break;
		
			case ACTIVE_READY:
				result = 501;
			break;
		
			case ACTIVE_INPROGRESS:
				result = 500;
			break;
		
			case SUSPENDED_RESERVEDBUTSUSPENDED:
				result = 509;
			break;
		
			case ACTIVE_RESERVED:
				result = 499;
			break;
		
			case CREATED:
				result = 504;
			break;
		
		}
		
		return result;
	}

}