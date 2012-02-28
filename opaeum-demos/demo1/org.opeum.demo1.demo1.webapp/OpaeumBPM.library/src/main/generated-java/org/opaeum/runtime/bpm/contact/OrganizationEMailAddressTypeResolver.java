package org.opaeum.runtime.bpm.contact;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class OrganizationEMailAddressTypeResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==3394756903880631170l ) {
			result = OrganizationEMailAddressType.INFO;
		} else {
			if ( i==2861333294210682425l ) {
				result = OrganizationEMailAddressType.ADMIN;
			} else {
			
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.runtime.bpm.contact.OrganizationEMailAddressType.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (OrganizationEMailAddressType)en ) {
			case ADMIN:
				result = 2861333294210682425l;
			break;
		
			case INFO:
				result = 3394756903880631170l;
			break;
		
		}
		
		return result;
	}

}