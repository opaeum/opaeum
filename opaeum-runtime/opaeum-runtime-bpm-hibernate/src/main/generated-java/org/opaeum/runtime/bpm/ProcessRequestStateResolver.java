package org.opaeum.runtime.bpm;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class ProcessRequestStateResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(int i) {
		IEnum result = null;
		switch ( i ) {
		}
		
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opaeum.runtime.bpm.ProcessRequestState.class;
	}
	
	public int toOpaeumId(IEnum en) {
		int result = -1;
		switch ( (ProcessRequestState)en ) {
		}
		
		return result;
	}

}