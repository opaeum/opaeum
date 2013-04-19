package org.opaeum.runtime.domain;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class BusinessTimeUnitResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==8352317045811511744l ) {
			result = BusinessTimeUnit.BUSINESSMINUTE;
		} else {
			if ( i==5085120101231742644l ) {
				result = BusinessTimeUnit.ACTUALMINUTE;
			} else {
				if ( i==2096741548211662020l ) {
					result = BusinessTimeUnit.BUSINESSHOUR;
				} else {
					if ( i==8799233759197904602l ) {
						result = BusinessTimeUnit.ACTUALHOUR;
					} else {
						if ( i==2729757306656991890l ) {
							result = BusinessTimeUnit.BUSINESSDAY;
						} else {
							if ( i==8769337351836134558l ) {
								result = BusinessTimeUnit.CALENDARDAY;
							} else {
								if ( i==6721263799379286430l ) {
									result = BusinessTimeUnit.CALENDARWEEK;
								} else {
									if ( i==2883229145281295940l ) {
										result = BusinessTimeUnit.BUSINESSWEEK;
									} else {
										if ( i==3744136449941699484l ) {
											result = BusinessTimeUnit.CALENDARMONTH;
										} else {
											if ( i==8849849135354011066l ) {
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
		return BusinessTimeUnit.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (BusinessTimeUnit)en ) {
			case BUSINESSMONTH:
				result = 8849849135354011066l;
			break;
		
			case CALENDARMONTH:
				result = 3744136449941699484l;
			break;
		
			case BUSINESSWEEK:
				result = 2883229145281295940l;
			break;
		
			case CALENDARWEEK:
				result = 6721263799379286430l;
			break;
		
			case CALENDARDAY:
				result = 8769337351836134558l;
			break;
		
			case BUSINESSDAY:
				result = 2729757306656991890l;
			break;
		
			case ACTUALHOUR:
				result = 8799233759197904602l;
			break;
		
			case BUSINESSHOUR:
				result = 2096741548211662020l;
			break;
		
			case ACTUALMINUTE:
				result = 5085120101231742644l;
			break;
		
			case BUSINESSMINUTE:
				result = 8352317045811511744l;
			break;
		
		}
		
		return result;
	}

}