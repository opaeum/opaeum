package org.opaeum.runtime.bpm.contact;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class OrganizationPhoneNumberTypeResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==4917448850126404380l ) {
			result = OrganizationPhoneNumberType.LANDLINE;
		} else {
			if ( i==3657378413704772210l ) {
				result = OrganizationPhoneNumberType.FAX;
			} else {
			
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.runtime.bpm.contact.OrganizationPhoneNumberType.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (OrganizationPhoneNumberType)en ) {
			case FAX:
				result = 3657378413704772210l;
			break;
		
			case LANDLINE:
				result = 4917448850126404380l;
			break;
		
		}
		
		return result;
	}

}