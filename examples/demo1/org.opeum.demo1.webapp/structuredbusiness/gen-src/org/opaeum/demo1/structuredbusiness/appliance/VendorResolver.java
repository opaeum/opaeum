package org.opaeum.demo1.structuredbusiness.appliance;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class VendorResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==7658578000613130252l ) {
			result = Vendor.WHIRLPOOL;
		} else {
			if ( i==377325257822563020l ) {
				result = Vendor.BOSCH;
			} else {
				if ( i==7281287795211476570l ) {
					result = Vendor.KELVINATOR;
				} else {
				
				}
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.demo1.structuredbusiness.appliance.Vendor.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (Vendor)en ) {
			case KELVINATOR:
				result = 7281287795211476570l;
			break;
		
			case BOSCH:
				result = 377325257822563020l;
			break;
		
			case WHIRLPOOL:
				result = 7658578000613130252l;
			break;
		
		}
		
		return result;
	}

}