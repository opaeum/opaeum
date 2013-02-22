package org.opaeum.runtime.bpm.request;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class AbstractRequestStateResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==2621009303440568440l ) {
			result = AbstractRequestState.ACTIVE;
		} else {
			if ( i==463934878768429112l ) {
				result = AbstractRequestState.SUSPENDED;
			} else {
				if ( i==7850806052753459670l ) {
					result = AbstractRequestState.CREATED;
				} else {
					if ( i==3509965088053427528l ) {
						result = AbstractRequestState.COMPLETE;
					} else {
					
					}
				}
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.runtime.bpm.request.AbstractRequestState.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (AbstractRequestState)en ) {
			case COMPLETE:
				result = 3509965088053427528l;
			break;
		
			case CREATED:
				result = 7850806052753459670l;
			break;
		
			case SUSPENDED:
				result = 463934878768429112l;
			break;
		
			case ACTIVE:
				result = 2621009303440568440l;
			break;
		
		}
		
		return result;
	}

}