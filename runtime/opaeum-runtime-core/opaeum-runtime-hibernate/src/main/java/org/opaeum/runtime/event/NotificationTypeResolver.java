package org.opaeum.runtime.event;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class NotificationTypeResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		throw new RuntimeException();
	}
	
	public Class<?> returnedClass() {
		return NotificationType.class;
	}
	
	public long toOpaeumId(IEnum en) {
		throw new RuntimeException();
	}

}