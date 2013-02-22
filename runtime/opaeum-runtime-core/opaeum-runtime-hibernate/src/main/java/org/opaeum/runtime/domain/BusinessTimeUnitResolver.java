package org.opaeum.runtime.domain;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.event.NotificationTypeResolver;

public class BusinessTimeUnitResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		throw new RuntimeException();
	}
	
	public Class<?> returnedClass() {
		return BusinessTimeUnit.class;
	}
	
	public long toOpaeumId(IEnum en) {
		throw new RuntimeException();
	}

}
