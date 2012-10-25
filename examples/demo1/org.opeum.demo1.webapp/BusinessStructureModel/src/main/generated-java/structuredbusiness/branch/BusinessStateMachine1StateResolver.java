package structuredbusiness.branch;

import org.opaeum.hibernate.domain.AbstractEnumResolver;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;

public class BusinessStateMachine1StateResolver extends AbstractEnumResolver implements EnumResolver {



	public IEnum fromOpaeumId(long i) {
		IEnum result = null;
		if ( i==2638769176053329929l ) {
			result = BusinessStateMachine1State.DRAFT;
		} else {
			if ( i==8036718979494311659l ) {
				result = BusinessStateMachine1State.STATE1;
			} else {
				if ( i==6554627263207717015l ) {
					result = BusinessStateMachine1State.APPROVED;
				} else {
				
				}
			}
		}
		return result;
	}
	
	public Class<?> returnedClass() {
		return structuredbusiness.branch.BusinessStateMachine1State.class;
	}
	
	public long toOpaeumId(IEnum en) {
		long result = -1;
		switch ( (BusinessStateMachine1State)en ) {
			case APPROVED:
				result = 6554627263207717015l;
			break;
		
			case STATE1:
				result = 8036718979494311659l;
			break;
		
			case DRAFT:
				result = 2638769176053329929l;
			break;
		
		}
		
		return result;
	}

}