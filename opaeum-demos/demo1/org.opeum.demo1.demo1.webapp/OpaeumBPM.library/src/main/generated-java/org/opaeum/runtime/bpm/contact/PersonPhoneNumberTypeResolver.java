package org.opaeum.runtime.bpm.contact;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class PersonPhoneNumberTypeResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==9013144590782986798l ) {
			result = PersonPhoneNumberType.HOME;
		} else {
			if ( i==1762128726551794158l ) {
				result = PersonPhoneNumberType.CELL;
			} else {
				if ( i==7683582777343226626l ) {
					result = PersonPhoneNumberType.WORK;
				} else {
					if ( i==4389408779772903514l ) {
						result = PersonPhoneNumberType.FAX;
					} else {
					
					}
				}
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.runtime.bpm.contact.PersonPhoneNumberType.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (PersonPhoneNumberType)en ) {
			case FAX:
				result = 4389408779772903514l;
			break;
		
			case WORK:
				result = 7683582777343226626l;
			break;
		
			case CELL:
				result = 1762128726551794158l;
			break;
		
			case HOME:
				result = 9013144590782986798l;
			break;
		
		}
		
		return result;
	}

}