package org.opeum.runtime.bpm;

import org.opeum.hibernate.domain.AbstractEnumResolver;
import org.opeum.runtime.domain.EnumResolver;
import org.opeum.runtime.domain.IEnum;

public class AbstractRequestStateResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpeumId(int i) {
		IEnum result = null;
		switch ( i ) {
		}
		
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.opeum.runtime.bpm.AbstractRequestState.class;
	}
	
	public int toOpeumId(IEnum en) {
		int result = -1;
		switch ( (AbstractRequestState)en ) {
		}
		
		return result;
	}

}