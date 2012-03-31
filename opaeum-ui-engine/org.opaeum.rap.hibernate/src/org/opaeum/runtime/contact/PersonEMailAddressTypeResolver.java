package org.opaeum.runtime.contact;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class PersonEMailAddressTypeResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==2569478031111177750l ) {
			result = PersonEMailAddressType.WORK;
		} else {
			if ( i==6316649075823617580l ) {
				result = PersonEMailAddressType.HOME;
			} else {
			
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.runtime.contact.PersonEMailAddressType.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (PersonEMailAddressType)en ) {
			case HOME:
				result = 6316649075823617580l;
			break;
		
			case WORK:
				result = 2569478031111177750l;
			break;
		
		}
		
		return result;
	}

}