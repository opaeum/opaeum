package org.opaeum.runtime.bpm.opaeumsimpletypes;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class DayOfWeekResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==5497891836226107522l ) {
			result = DayOfWeek.SUNDAY;
		} else {
			if ( i==5710826815533593808l ) {
				result = DayOfWeek.MONDAY;
			} else {
				if ( i==7637501354073283762l ) {
					result = DayOfWeek.TUESDAY;
				} else {
					if ( i==5854374740816063880l ) {
						result = DayOfWeek.WEDNESDAY;
					} else {
						if ( i==6034081447840876170l ) {
							result = DayOfWeek.THURSDAY;
						} else {
							if ( i==1574103007809572002l ) {
								result = DayOfWeek.FRIDAY;
							} else {
								if ( i==3124615520862752976l ) {
									result = DayOfWeek.SATURDAY;
								} else {
								
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
		return org.opaeum.runtime.bpm.opaeumsimpletypes.DayOfWeek.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (DayOfWeek)en ) {
			case SATURDAY:
				result = 3124615520862752976l;
			break;
		
			case FRIDAY:
				result = 1574103007809572002l;
			break;
		
			case THURSDAY:
				result = 6034081447840876170l;
			break;
		
			case WEDNESDAY:
				result = 5854374740816063880l;
			break;
		
			case TUESDAY:
				result = 7637501354073283762l;
			break;
		
			case MONDAY:
				result = 5710826815533593808l;
			break;
		
			case SUNDAY:
				result = 5497891836226107522l;
			break;
		
		}
		
		return result;
	}

}