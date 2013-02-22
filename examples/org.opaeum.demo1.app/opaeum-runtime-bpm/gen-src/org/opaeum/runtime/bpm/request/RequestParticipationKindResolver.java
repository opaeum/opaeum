package org.opaeum.runtime.bpm.request;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class RequestParticipationKindResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==4714450189639896024l ) {
			result = RequestParticipationKind.BUSINESSOWNER;
		} else {
			if ( i==3293883879134782196l ) {
				result = RequestParticipationKind.STAKEHOLDER;
			} else {
				if ( i==8214635375160583394l ) {
					result = RequestParticipationKind.INITIATOR;
				} else {
				
				}
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.runtime.bpm.request.RequestParticipationKind.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (RequestParticipationKind)en ) {
			case INITIATOR:
				result = 8214635375160583394l;
			break;
		
			case STAKEHOLDER:
				result = 3293883879134782196l;
			break;
		
			case BUSINESSOWNER:
				result = 4714450189639896024l;
			break;
		
		}
		
		return result;
	}

}