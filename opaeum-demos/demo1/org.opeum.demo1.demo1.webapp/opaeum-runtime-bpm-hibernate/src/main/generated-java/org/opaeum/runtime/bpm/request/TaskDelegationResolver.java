package org.opaeum.runtime.bpm.request;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class TaskDelegationResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==5531240598007848110l ) {
			result = TaskDelegation.POTENTIALOWNERS;
		} else {
			if ( i==8669793748218427748l ) {
				result = TaskDelegation.NOBODY;
			} else {
				if ( i==2158399829271715402l ) {
					result = TaskDelegation.ANYBODY;
				} else {
					if ( i==8593265702287005578l ) {
						result = TaskDelegation.OTHER;
					} else {
					
					}
				}
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.runtime.bpm.request.TaskDelegation.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (TaskDelegation)en ) {
			case OTHER:
				result = 8593265702287005578l;
			break;
		
			case ANYBODY:
				result = 2158399829271715402l;
			break;
		
			case NOBODY:
				result = 8669793748218427748l;
			break;
		
			case POTENTIALOWNERS:
				result = 5531240598007848110l;
			break;
		
		}
		
		return result;
	}

}