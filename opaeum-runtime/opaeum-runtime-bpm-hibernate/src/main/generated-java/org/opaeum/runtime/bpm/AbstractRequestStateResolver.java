package org.opaeum.runtime.bpm;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class AbstractRequestStateResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(int i) {
		IEnum result = null;
		switch ( i ) {
		}
		
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.runtime.bpm.AbstractRequestState.class;
	}
	
	public int toOpaeumId(IEnum en) {
		int result = -1;
		switch ( (AbstractRequestState)en ) {
		}
		
		return result;
	}

}