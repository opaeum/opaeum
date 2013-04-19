package org.opaeum.runtime.bpm.opaeumsimpletypes;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class MonthResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==640653935744425155l ) {
			result = Month.JANUARY;
		} else {
			if ( i==972801950013056379l ) {
				result = Month.FEBRUARY;
			} else {
				if ( i==5185348543107263063l ) {
					result = Month.MARCH;
				} else {
					if ( i==6921870273021791857l ) {
						result = Month.APRIL;
					} else {
						if ( i==4615887491455175497l ) {
							result = Month.MAY;
						} else {
							if ( i==3472788856023550217l ) {
								result = Month.JUNE;
							} else {
								if ( i==6688112956924166609l ) {
									result = Month.JULY;
								} else {
									if ( i==4398678130798243985l ) {
										result = Month.AUGUST;
									} else {
										if ( i==6336416570622065821l ) {
											result = Month.SEPTEMBER;
										} else {
											if ( i==5875481493733805759l ) {
												result = Month.OCTOBER;
											} else {
												if ( i==7294355607504504191l ) {
													result = Month.NOVEMBER;
												} else {
													if ( i==440334831924994583l ) {
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
		return org.opaeum.runtime.bpm.opaeumsimpletypes.Month.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (Month)en ) {
			case DECEMBER:
				result = 440334831924994583l;
			break;
		
			case NOVEMBER:
				result = 7294355607504504191l;
			break;
		
			case OCTOBER:
				result = 5875481493733805759l;
			break;
		
			case SEPTEMBER:
				result = 6336416570622065821l;
			break;
		
			case AUGUST:
				result = 4398678130798243985l;
			break;
		
			case JULY:
				result = 6688112956924166609l;
			break;
		
			case JUNE:
				result = 3472788856023550217l;
			break;
		
			case MAY:
				result = 4615887491455175497l;
			break;
		
			case APRIL:
				result = 6921870273021791857l;
			break;
		
			case MARCH:
				result = 5185348543107263063l;
			break;
		
			case FEBRUARY:
				result = 972801950013056379l;
			break;
		
			case JANUARY:
				result = 640653935744425155l;
			break;
		
		}
		
		return result;
	}

}