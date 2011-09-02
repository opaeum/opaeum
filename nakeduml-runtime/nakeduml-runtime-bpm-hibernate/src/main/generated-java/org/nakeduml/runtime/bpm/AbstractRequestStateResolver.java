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
	
	public int toNakedUmlId(IEnum en) {
		int result = -1;
		switch ( (AbstractRequestState)en ) {
		}
		
		return result;
	}

	@Override
	public Class returnedClass(){
		return AbstractRequestState.class;
	}

}