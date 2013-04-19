package org.opaeum.runtime.bpm.request;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class AbstractRequestStateResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.runtime.bpm.request.AbstractRequestState.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (AbstractRequestState)en ) {
		}
		
		return result;
	}

}