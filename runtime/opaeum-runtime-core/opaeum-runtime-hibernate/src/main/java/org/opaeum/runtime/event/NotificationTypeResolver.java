package org.opaeum.runtime.event;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class NotificationTypeResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==6159557950617470138l ) {
			result = NotificationType.EMAIL;
		} else {
		
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.runtime.event.NotificationType.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (NotificationType)en ) {
			case EMAIL:
				result = 6159557950617470138l;
			break;
		
		}
		
		return result;
	}

}