package org.opaeum.runtime.domain;


import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class TaskDelegationResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==3561801672124045355l ) {
			result = TaskDelegation.POTENTIALOWNERS;
		} else {
			if ( i==683908055359230403l ) {
				result = TaskDelegation.NOBODY;
			} else {
				if ( i==7195301974305942749l ) {
					result = TaskDelegation.ANYBODY;
				} else {
					if ( i==760436101290652573l ) {
						result = TaskDelegation.OTHER;
					} else {
					
					}
				}
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return TaskDelegation.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (TaskDelegation)en ) {
			case OTHER:
				result = 760436101290652573l;
			break;
		
			case ANYBODY:
				result = 7195301974305942749l;
			break;
		
			case NOBODY:
				result = 683908055359230403l;
			break;
		
			case POTENTIALOWNERS:
				result = 3561801672124045355l;
			break;
		
		}
		
		return result;
	}

}