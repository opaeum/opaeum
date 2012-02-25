package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class MonthResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==4295270312928861877l ) {
			result = Month.JANUARY;
		} else {
			if ( i==5908726198686343411l ) {
				result = Month.FEBRUARY;
			} else {
				if ( i==8325471281929001521l ) {
					result = Month.MARCH;
				} else {
					if ( i==1985946024348504825l ) {
						result = Month.APRIL;
					} else {
						if ( i==320036757218111535l ) {
							result = Month.MAY;
						} else {
							if ( i==1463135392649736815l ) {
								result = Month.JUNE;
							} else {
								if ( i==6822706868112097975l ) {
									result = Month.JULY;
								} else {
									if ( i==537246117875043047l ) {
										result = Month.AUGUST;
									} else {
										if ( i==7174403254414198763l ) {
											result = Month.SEPTEMBER;
										} else {
											if ( i==7635338331302458825l ) {
												result = Month.OCTOBER;
											} else {
												if ( i==2358431358831217159l ) {
													result = Month.NOVEMBER;
												} else {
													if ( i==4495589416748292449l ) {
														result = Month.DECEMBER;
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
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.runtime.bpm.businesscalendar.Month.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (Month)en ) {
			case DECEMBER:
				result = 4495589416748292449l;
			break;
		
			case NOVEMBER:
				result = 2358431358831217159l;
			break;
		
			case OCTOBER:
				result = 7635338331302458825l;
			break;
		
			case SEPTEMBER:
				result = 7174403254414198763l;
			break;
		
			case AUGUST:
				result = 537246117875043047l;
			break;
		
			case JULY:
				result = 6822706868112097975l;
			break;
		
			case JUNE:
				result = 1463135392649736815l;
			break;
		
			case MAY:
				result = 320036757218111535l;
			break;
		
			case APRIL:
				result = 1985946024348504825l;
			break;
		
			case MARCH:
				result = 8325471281929001521l;
			break;
		
			case FEBRUARY:
				result = 5908726198686343411l;
			break;
		
			case JANUARY:
				result = 4295270312928861877l;
			break;
		
		}
		
		return result;
	}

}