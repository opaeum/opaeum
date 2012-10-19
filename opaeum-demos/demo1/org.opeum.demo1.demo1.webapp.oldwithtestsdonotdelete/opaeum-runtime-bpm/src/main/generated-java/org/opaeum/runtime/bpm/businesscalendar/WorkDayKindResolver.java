package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class WorkDayKindResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==1582201863518161267l ) {
			result = WorkDayKind.WEEKDAY;
		} else {
			if ( i==6901241406743663175l ) {
				result = WorkDayKind.SATURDAY;
			} else {
				if ( i==4486357431481835835l ) {
					result = WorkDayKind.SUNDAY;
				} else {
				
				}
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.runtime.bpm.businesscalendar.WorkDayKind.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (WorkDayKind)en ) {
			case SUNDAY:
				result = 4486357431481835835l;
			break;
		
			case SATURDAY:
				result = 6901241406743663175l;
			break;
		
			case WEEKDAY:
				result = 1582201863518161267l;
			break;
		
		}
		
		return result;
	}

}