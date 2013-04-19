package ocltests;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class SailPositionResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==7973713438283700445l ) {
			result = SailPosition.FRONT;
		} else {
			if ( i==1321889112493011585l ) {
				result = SailPosition.REAR;
			} else {
			
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return ocltests.SailPosition.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (SailPosition)en ) {
			case REAR:
				result = 1321889112493011585l;
			break;
		
			case FRONT:
				result = 7973713438283700445l;
			break;
		
		}
		
		return result;
	}

}