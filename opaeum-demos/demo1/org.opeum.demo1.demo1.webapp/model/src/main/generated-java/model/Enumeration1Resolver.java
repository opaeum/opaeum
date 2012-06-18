package model;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class Enumeration1Resolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==664316455779765488l ) {
			result = Enumeration1.ENUMERATIONLITERAL1;
		} else {
		
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return model.Enumeration1.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (Enumeration1)en ) {
			case ENUMERATIONLITERAL1:
				result = 664316455779765488l;
			break;
		
		}
		
		return result;
	}

}