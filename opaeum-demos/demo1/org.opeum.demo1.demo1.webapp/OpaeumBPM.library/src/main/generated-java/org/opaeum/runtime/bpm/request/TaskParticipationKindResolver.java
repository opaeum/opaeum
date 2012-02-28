package org.opaeum.runtime.bpm.request;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class TaskParticipationKindResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==4935160483967374189l ) {
			result = TaskParticipationKind.OWNER;
		} else {
			if ( i==3500106936288394851l ) {
				result = TaskParticipationKind.POTENTIALOWNER;
			} else {
			
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.runtime.bpm.request.TaskParticipationKind.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (TaskParticipationKind)en ) {
			case POTENTIALOWNER:
				result = 3500106936288394851l;
			break;
		
			case OWNER:
				result = 4935160483967374189l;
			break;
		
		}
		
		return result;
	}

}