package model;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class Enumeration3Resolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		return result;
	}
	
	public Class<?> returnedClass() {
		return model.Enumeration3.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (Enumeration3)en ) {
		}
		
		return result;
	}

}