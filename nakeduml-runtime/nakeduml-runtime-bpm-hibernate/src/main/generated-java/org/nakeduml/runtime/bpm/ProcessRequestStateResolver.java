package org.nakeduml.runtime.bpm;

import org.nakeduml.hibernate.domain.AbstractEnumResolver;
import org.nakeduml.runtime.domain.EnumResolver;
import org.nakeduml.runtime.domain.IEnum;

public class ProcessRequestStateResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromNakedUmlId(int i) {
		IEnum result = null;
		switch ( i ) {
		}
		
		return result;
	}
	
	public Class<?> returnedClass() {
		return org.nakeduml.runtime.bpm.ProcessRequestState.class;
	}
	
	public int toNakedUmlId(IEnum en) {
		int result = -1;
		switch ( (ProcessRequestState)en ) {
		}
		
		return result;
	}

}