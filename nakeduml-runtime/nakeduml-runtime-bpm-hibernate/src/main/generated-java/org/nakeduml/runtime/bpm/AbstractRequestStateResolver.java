package org.nakeduml.runtime.bpm;

import org.nakeduml.hibernate.domain.AbstractEnumResolver;
import org.nakeduml.runtime.domain.EnumResolver;
import org.nakeduml.runtime.domain.IEnum;

public class AbstractRequestStateResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromNakedUmlId(int i) {
		IEnum result = null;
		switch ( i ) {
		}
		
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.nakeduml.runtime.bpm.AbstractRequestState.class;
	}
	
	public int toNakedUmlId(IEnum en) {
		int result = -1;
		switch ( (AbstractRequestState)en ) {
		}
		
		return result;
	}

}