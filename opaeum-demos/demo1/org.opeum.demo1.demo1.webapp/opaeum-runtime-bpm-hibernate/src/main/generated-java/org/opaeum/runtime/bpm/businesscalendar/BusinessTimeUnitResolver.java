package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class BusinessTimeUnitResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==740725224320381721l ) {
			result = BusinessTimeUnit.BUSINESSMINUTE;
		} else {
			if ( i==4268581702345915507l ) {
				result = BusinessTimeUnit.ACTUALMINUTE;
			} else {
				if ( i==6996300721920231445l ) {
					result = BusinessTimeUnit.BUSINESSHOUR;
				} else {
					if ( i==554468044379753549l ) {
						result = BusinessTimeUnit.ACTUALHOUR;
					} else {
						if ( i==6623944496920666261l ) {
							result = BusinessTimeUnit.BUSINESSDAY;
						} else {
							if ( i==584364451741523593l ) {
								result = BusinessTimeUnit.CALENDARDAY;
							} else {
								if ( i==2632438004198371721l ) {
									result = BusinessTimeUnit.CALENDARWEEK;
								} else {
									if ( i==6209813124850597525l ) {
										result = BusinessTimeUnit.BUSINESSWEEK;
									} else {
										if ( i==5609565353635958667l ) {
											result = BusinessTimeUnit.CALENDARMONTH;
										} else {
											if ( i==243193134777882399l ) {
												result = BusinessTimeUnit.BUSINESSMONTH;
											} else {
											
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.runtime.bpm.businesscalendar.BusinessTimeUnit.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (BusinessTimeUnit)en ) {
			case BUSINESSMONTH:
				result = 243193134777882399l;
			break;
		
			case CALENDARMONTH:
				result = 5609565353635958667l;
			break;
		
			case BUSINESSWEEK:
				result = 6209813124850597525l;
			break;
		
			case CALENDARWEEK:
				result = 2632438004198371721l;
			break;
		
			case CALENDARDAY:
				result = 584364451741523593l;
			break;
		
			case BUSINESSDAY:
				result = 6623944496920666261l;
			break;
		
			case ACTUALHOUR:
				result = 554468044379753549l;
			break;
		
			case BUSINESSHOUR:
				result = 6996300721920231445l;
			break;
		
			case ACTUALMINUTE:
				result = 4268581702345915507l;
			break;
		
			case BUSINESSMINUTE:
				result = 740725224320381721l;
			break;
		
		}
		
		return result;
	}

}